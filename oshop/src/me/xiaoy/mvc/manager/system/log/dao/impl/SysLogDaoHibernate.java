package me.xiaoy.mvc.manager.system.log.dao.impl;

import java.util.ArrayList;
import java.util.List;

import me.xiaoy.core.base.BaseDao;
import me.xiaoy.core.common.page.PageList;
import me.xiaoy.core.utils.StringUtil;
import me.xiaoy.mvc.manager.system.log.dao.SysLogDao;
import me.xiaoy.mvc.manager.system.log.entity.SysLog;

import org.springframework.stereotype.Repository;

@Repository(value="logDao")
public class SysLogDaoHibernate extends BaseDao<SysLog> implements SysLogDao {

	public PageList<SysLog> list(PageList<SysLog> p, SysLog t) {
		StringBuffer hql = new StringBuffer("from " + entityClass.getName() + " as t where 1=1");
		List<Object> params = new ArrayList<Object>();
		if(t != null) {
			if(t.getAccountId() != null) {
				hql.append(" and t.accountId=? ");
				params.add(t.getAccountId());
			}
			if(t.getUserName()!=null && StringUtil.isNotEmpty(t.getUserName())) {
				hql.append(" and t.userName like ? ");
				params.add("%"+t.getUserName()+"%");
			}
			if(t.getIp()!=null && StringUtil.isNotEmpty(t.getIp())) {
				hql.append(" and t.ip like ? ");
				params.add("%"+t.getIp()+"%");
			}
			if(t.getOperation()!=null && StringUtil.isNotEmpty(t.getOperation())) {
				hql.append(" and t.operation like ? ");
				params.add("%"+t.getOperation()+"%");
			}
			if(t.getCreateTime()!=null) {
				hql.append(" and t.createTime >= ?");
				params.add(t.getCreateTime());
			}
			if(t.getCreateTime2()!=null) {
				hql.append(" and t.createTime <= ?");
				params.add(t.getCreateTime2());
			}
		}
		p.addDescending("createTime");
		return super.query(p, hql.toString(), params.toArray());
	}


	public void delete(Long[] ids) {
		super.delete(ids);
	}


	public void delete(Long id) {
		super.delete(id);
	}
}
