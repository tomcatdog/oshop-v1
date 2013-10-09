package me.xiaoy.mvc.manager.system.parm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import me.xiaoy.core.base.BaseDao;
import me.xiaoy.core.common.page.PageList;
import me.xiaoy.core.utils.StringUtil;
import me.xiaoy.mvc.manager.security.entity.LoginAccount;
import me.xiaoy.mvc.manager.system.parm.dao.SysParamDao;
import me.xiaoy.mvc.manager.system.parm.entity.SysParam;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository(value="sysParamDao")
public class SysParamDaoHibernate extends BaseDao<SysParam> implements SysParamDao{

	@Override
	public PageList<SysParam> query(PageList<SysParam> page, LoginAccount currentUser, SysParam param) {
		StringBuffer hql = new StringBuffer("from " + entityClass.getName() + " as t where 1=1");
		List<Object> params = new ArrayList<Object>();
		if(currentUser == null) {
			//开发期间注释
//			return null;
		} else {
			//超级管理员为平台使用用户， 拥有所有权限， 查询不做限制， 否则只能查询所属用户的内容
			if(!currentUser.isSuperAdmin()) {
//				hql.append(" m.merchantId = ? ");
//				params.add(account.getMerchantId());
			}
		}
		if(param != null) {
			if(StringUtil.isNotEmpty(param.getParamName())) {
				hql.append(" and t.paramName like ?");
				params.add("%"+param.getParamName()+"%");
			}
			if(param.getModifiable()!=null) {
				hql.append(" and t.modifiable = ?");
				params.add(param.getModifiable());
			}
			if(param.getInit() != null) {
				hql.append(" and t.init = ?");
				params.add(param.getInit());
			}
			if(StringUtil.isNotEmpty(param.getGroup())) {
				hql.append(" and (lower(t.group) like ? or lower(t.group) like ?)");
				params.add("%"+param.getGroup().toLowerCase()+"%");
				params.add("%"+param.getGroup().toLowerCase()+"%");
			}
		}
		return super.query(page, hql.toString(), params.toArray());
	}

	@Override
	public SysParam get(Long id) {
		return super.get(id);
	}

	@Override
	public List<SysParam> getInitParams() {
		String hql = "from SysParam where init=1";
		return super.query(hql);
	}

	@Override
	public String getParam(String key) {
		String result = "";
		List<Object> params = new ArrayList<Object>();
		String hql = "from SysParam where paramName=?";
		params.add(key);
		List<SysParam> list = super.query(hql, params.toArray());
		if(list.size()>0) {
			result = String.valueOf(list.get(0));
		}
		return result;
	}

	@Override
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	public void delete(Long[] ids) {
		super.delete(ids);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getGroup() {
		String hql = "select group from SysParam group by group";
		Query query = super.getSession().createQuery(hql);
		return query.list();
	}

}
