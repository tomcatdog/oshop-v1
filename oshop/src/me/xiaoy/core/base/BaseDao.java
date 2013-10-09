package me.xiaoy.core.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import me.xiaoy.core.common.page.PageList;
import me.xiaoy.core.common.page.PageList.OrderType;
import me.xiaoy.core.exception.DaoException;
import me.xiaoy.core.utils.ParserUtil;
import me.xiaoy.core.utils.StringUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseDao<T extends Object> implements IBaseDao<T> {
	
	protected static final int BATH_SIZE = 15;
	
	protected Class<T> entityClass;
	
//	private String pkName = null;
	    
	public BaseDao( Class<T> t ) {
		this.entityClass = t;		
	}
	
	@SuppressWarnings("unchecked")
	protected BaseDao() {
		Type type = null;
		if( getClass().getGenericSuperclass() instanceof ParameterizedType)
			type =((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		
		if( type != null && type instanceof Class){
			this.entityClass = (Class<T>) type;
		}
//		Field[] fields = this.entityClass.getDeclaredFields();  
//        for(Field f : fields) {  
//            if(f.isAnnotationPresent(Id.class)) {  
//                this.pkName = f.getName();  
//            }  
//        }
	}

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
//		return sessionFactory.openSession();
	}
	
	@Override
	public Serializable save(T t) {
		Serializable id = "";
		try {
			id = getSession().save(t);
		} catch (Exception e) {
			throw new DaoException(this.getClass(), e.getMessage());
		}
		return id;
	}
	
	@Override
	public Long save(List<T> list) {
		Long result = 0L;
		Session session = getSession();
		for(int i=0;i<list.size();i++) {
			result = (Long) save(list.get(i));
			if(i%20==0) {
				session.flush();
			}
		}
		return result;
	}
	
	@Override
	public void update(T t) {
		try {
			getSession().update(t);
		} catch (Exception e) {
			throw new DaoException(this.getClass(), e.getMessage());
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		return (T) getSession().get(entityClass, id);
	}
	
	@Override
	public void delete(T t) {
		try {
			getSession().delete(t);
		} catch (Exception e) {
			throw new DaoException(this.getClass(), e.getMessage());
		}
	}
	
	@Override
	public void delete(Serializable id) {
		try {
			getSession().delete(get(id));
		} catch (Exception e) {
			throw new DaoException(this.getClass(), e.getMessage());
		}
	}
	
	@Override
	public void delete(Long[] ids) {
		String hql = "delete from " + entityClass.getName() + " where id in :ids";
		Query session = getSession().createQuery(hql);
		session.setParameterList("ids", ids);
		session.executeUpdate();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> query(String hql) {
		List<T> list = null;
		try {
			list = (List<T>)getSession().createQuery(hql).list();
		} catch(Exception e) {
			throw new DaoException(this.getClass(), e.getMessage());
		}
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> query(String hql, Object[] parms) {
		List<T> list = null;
		try {
			Query query = getSession().createQuery(hql);
            if(parms != null) {
                for(int i = 0; i < parms.length; i++)
                	query.setParameter(i, parms[i]);
            }
            list = (List<T>)query.list();
		} catch(Exception e) {
			throw new DaoException(this.getClass(), e.getMessage());
		}
		return list;
	}
	
	/**
	 * order by 
	 */
	protected String addOrderBy(PageList<?> page){
		//add order
		StringBuffer orderby = new StringBuffer("");
		List<PageList<?>.OrderBy> orderByList = page.getOrderByList();
		if( orderByList != null && !orderByList.isEmpty() ){
			for( PageList<?>.OrderBy each:page.getOrderByList() ){
				if( each == null )
					continue;
				orderby.append("".equals(orderby.toString())?" ORDER BY ":" ,");
				orderby.append(each.getProperty() 
					+ (OrderType.ASCENDING.equals( each.getType() )?" ASC ":" DESC ") );
			}
		}
		
		return orderby.toString();
	}
	
	/**
	 * add Query parameters
	 */
	protected Query addQueryParams( Query query ,Object[] params ){
		for( int i=0;params != null && i<params.length;i++ )
//			if(params[i] instanceof Date) {
//				query.setTimestamp(i, (Date)params[i]);
//			} else {
				query.setParameter(i, params[i] );
//			}
		
		return query;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<T> query(PageList<T> page, String select, String from, Object[] params) {
		try {
			String countSql = "select count(*) " + from ;
			page.setAllSize( rowCount(countSql,params ) ); 
			if( page.getNumber() > page.getAllPage() )
    			page.setNumber(Math.min(page.getNumber(), page.getAllPage()));
			if( page.getAllSize() > 0 && page.getSize() > 0 ){
    			String hql = (StringUtil.isEmpty(select)?"":select) + from;
    			hql += addOrderBy( page );//add order
    			
    			Query query = getSession().createQuery( hql )
	    				.setFirstResult(page.getStart()-1)
	    				.setMaxResults(page.getSize());
    			
    			addQueryParams( query,params );
    			
    			page.setList( query.list());
			}
		} catch(Exception e) {
			e.printStackTrace();
//			throw new DaoException(this.getClass(), e.getMessage());
		}
		return page;
	}
	
	@Override
	public PageList<T> list(PageList<T> page) {
		String hql = " FROM " + entityClass.getName() + " AS m ";
		return query( page , hql );
	}
	
	protected PageList<T> query(PageList<T> page, String from){
		return query( page, from, null );
	}
	
	protected PageList<T> query(PageList<T> page , String hql , Object[] params ){
		return query( page,null,hql,params );
	}
	
	@Override
	public PageList<T> list(PageList<T> page, T t ){
		if( t == null )
			return list(page);
		throw new DaoException(this.getClass(), "实体("+entityClass+")带条件的查询没有实现！");
	}

	@Override
	public int count(T t) {
		return (Integer) getSession().createQuery("select count(1) from " + entityClass.getClass()).uniqueResult();
	}
	
	protected int rowCount() {
		String hql = " SELECT COUNT( m ) from " + entityClass.getName() + " AS m ";
		return rowCount( hql );
	}
	
	protected int rowCount( String hql ) {
		return rowCount( hql, null);
	}
	
	/**
	 * 总记录数
	 */
	protected int rowCount( Session session,String countHql, Object[] params ) {
		Query query = session.createQuery( countHql );
		addQueryParams( query,params );
		Object count = query.uniqueResult();
		return ParserUtil.parseInt( count );
	}
	
	/**
	 * 总记录数
	 */
	@SuppressWarnings("rawtypes")
	protected int rowCount( String hql , Object[] params ) {
		Object count = this.find( hql ,params);
		return ( count != null && count instanceof List)?
			 ParserUtil.parseInt(((List)count).get(0)):0;
	}
	
	//HibernateTemplate Support
	@Override
	public List<T> find(String queryString) throws DaoException {
		return find(queryString, (Object[]) null);
	}

	@Override
	public List<T> find(String queryString, Object value) throws DaoException {
		return find(queryString, new Object[] {value});
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> find(final String queryString, final Object[] values) throws DaoException {
		Query queryObject = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject.list();
	}
	
}
