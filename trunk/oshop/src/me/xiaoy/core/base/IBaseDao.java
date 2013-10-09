package me.xiaoy.core.base;

import java.io.Serializable;
import java.util.List;

import me.xiaoy.core.common.page.PageList;
import me.xiaoy.core.exception.DaoException;

/**
 * 预留文件
 * @author qq
 *
 * @param <T>
 */
public interface IBaseDao<T extends Object> {

	public abstract Serializable save(T t);

	public abstract Long save(List<T> list);

	public abstract void update(T t);

	public abstract T get(Serializable id);

	public abstract void delete(T t);

	public abstract void delete(Serializable id);

	public abstract void delete(Long[] ids);
	
	public abstract List<T> query(String hql);

	public abstract List<T> query(String hql, Object[] parms);

	public abstract PageList<T> query(PageList<T> page, String select,
			String from, Object[] params);

	public abstract PageList<T> list(PageList<T> page);

	public abstract PageList<T> list(PageList<T> page, T t);

	public abstract int count(T t);

	//HibernateTemplate Support
	public abstract List<T> find(String queryString) throws DaoException;

	public abstract List<T> find(String queryString, Object value)
			throws DaoException;

	public abstract List<T> find(final String queryString, final Object[] values)
			throws DaoException;

}