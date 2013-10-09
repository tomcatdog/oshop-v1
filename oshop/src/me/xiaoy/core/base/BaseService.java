package me.xiaoy.core.base;

import java.io.Serializable;
import java.util.List;

import me.xiaoy.core.common.page.PageList;


public class BaseService<T extends Object> implements IBaseService<T> {
	
	protected IBaseDao<T> dao;
	
	public IBaseDao<T> getDao() {
		return dao;
	}

	public void setDao(IBaseDao<T> dao) {
		this.dao = dao;
	}

	/* 
	 * 保存单个实例
	 */
	@Override
	public Serializable save(T t) {
		return dao.save(t);
	}
	
	/* 
	 * 保存列表
	 */
	@Override
	public Long save(List<T> list) {
		return dao.save(list);
	}
	
	/* 
	 * 更新单个实例
	 */
	@Override
	public void update(T t) {
		dao.update(t);
	}
	
	/* 
	 * 获取单个实例
	 */
	@Override
	public T get(Serializable id) {
		return dao.get(id);
	}
	
	/* 
	 * 删除实例
	 */
	@Override
	public void delete(T t) {
		dao.delete(t);
	}
	
	/* 
	 * 删除id对应的记录
	 */
	@Override
	public void delete(Serializable id) {
		dao.delete(id);
	}
	
	/* 
	 * 查询列表
	 */
	@Override
	public List<T> query(String hql) {
		return dao.query(hql);
	}
	
	/* 
	 * 查询列表
	 */
	@Override
	public List<T> query(String hql, Object[] parms) {
		return dao.query(hql, parms);
	}
	
	/* 
	 * 查询列表
	 */
	@Override
	public PageList<T> query(PageList<T> page, String select, String from, Object[] params) {
		return dao.query(page, select, from, params);
	}
	
	/* 
	 * 查询列表
	 */
	@Override
	public PageList<T> list(PageList<T> page) {
		return dao.list(page);
	}
	
	/* 
	 * 查询列表
	 */
	@Override
	public PageList<T> list(PageList<T> page, T t ){
		return dao.list(page, t);
	}

	/* 
	 * 统计记录数量
	 */
	@Override
	public int count(T t) {
		return dao.count(t);
	}
	
}
