package me.xiaoy.mvc.manager.resource.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import me.xiaoy.core.base.BaseDao;
import me.xiaoy.mvc.manager.resource.dao.ResourceDao;
import me.xiaoy.mvc.manager.resource.entity.Resource;

public class ResourceDaoHibernate extends BaseDao<Resource> implements ResourceDao {

	@Override
	public void insert(Resource resource) {
		this.getSession().save(resource);
	}

	@Override
	public void deleteById(String id) {
		super.delete(id);
	}

	@Override
	public List<Resource> getListByParentid(String parentid) {
		String hql = "from " + entityClass.getName() + " where parentid = ?";
		List<String> paramList=new ArrayList<String>();
		paramList.add(parentid);
		List<Resource> list=super.query(hql,paramList.toArray());
		if(null==list){
			return new ArrayList<Resource>();
		}
		return list;
	}

	@Override
	public List<Resource> getListByParentidRestype(String parentid,
			String restype) {
		String hql = "from " + entityClass.getName() + " where parentid = ? and restype = ?";
		List<String> paramList=new ArrayList<String>();
		paramList.add(parentid);
		paramList.add(restype);
		List<Resource> list=super.query(hql,paramList.toArray());
		if(null==list){
			return new ArrayList<Resource>();
		}
		return list;
	}

	@Override
	public void deleteByIds(String[] ids) {
		String hql = "delete from " + entityClass.getName() + " where id in :ids";
		Query session = getSession().createQuery(hql);
		session.setParameterList("ids", ids);
		session.executeUpdate();
		
	}

	@Override
	public void deleteByParentid(String parentid) {
		String hql="delete from "+entityClass.getName()+" where parentid = ?";
		Query session = getSession().createQuery(hql);
		session.setParameter(1,parentid);
		session.executeUpdate();
				
	}

}
