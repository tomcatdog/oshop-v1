package me.xiaoy.core.base;

import java.io.Serializable;
import java.util.List;

import me.xiaoy.core.common.page.PageList;

/**
 * 预留文件
 * @author qq
 *
 * @param <T>
 */
public interface IBaseService<T extends Object> {

	/* 
	 * 保存单个实例
	 */
	public abstract Serializable save(T t);

	/* 
	 * 保存列表
	 */
	public abstract Long save(List<T> list);

	/* 
	 * 更新单个实例
	 */
	public abstract void update(T t);

	/* 
	 * 获取单个实例
	 */
	public abstract T get(Serializable id);

	/* 
	 * 删除实例
	 */
	public abstract void delete(T t);

	/* 
	 * 删除id对应的记录
	 */
	public abstract void delete(Serializable id);

	/* 
	 * 查询列表
	 */
	public abstract List<T> query(String hql);

	/* 
	 * 查询列表
	 */
	public abstract List<T> query(String hql, Object[] parms);

	/* 
	 * 查询列表
	 */
	public abstract PageList<T> query(PageList<T> page, String select,
			String from, Object[] params);

	/* 
	 * 查询列表
	 */
	public abstract PageList<T> list(PageList<T> page);

	/* 
	 * 查询列表
	 */
	public abstract PageList<T> list(PageList<T> page, T t);

	/* 
	 * 统计记录数量
	 */
	public abstract int count(T t);

}