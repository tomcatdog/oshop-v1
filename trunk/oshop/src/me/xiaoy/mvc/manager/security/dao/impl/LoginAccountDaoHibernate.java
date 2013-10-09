package me.xiaoy.mvc.manager.security.dao.impl;

import java.util.ArrayList;
import java.util.List;

import me.xiaoy.core.base.BaseDao;
import me.xiaoy.core.common.page.PageList;
import me.xiaoy.core.exception.UniqueException;
import me.xiaoy.core.utils.StringUtil;
import me.xiaoy.mvc.manager.security.dao.LoginAccountDao;
import me.xiaoy.mvc.manager.security.entity.LoginAccount;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository(value="loginAccountDao")
public class LoginAccountDaoHibernate extends BaseDao<LoginAccount> implements LoginAccountDao {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginAccountDaoHibernate.class);

	public void updateLoginFailureCount(LoginAccount account) {
		String hql = "update LoginAccount as m set m.loginFailureCount=(m.loginFailureCount+1)";
		this.getSession().update(hql, null);
	}

	public LoginAccount get(LoginAccount account) {
		return (LoginAccount) this.getSession().get(LoginAccount.class, account.getId());
	}

	@Override
	public LoginAccount getLoginAccountByLoginName(String loginName) {
		LoginAccount account = null;
		String hql = "from " + entityClass.getName() + " where loginName = ?";
		List<String> params = new ArrayList<String>();
		params.add(loginName);
		List<LoginAccount> list = super.query(hql, params.toArray());
		if(list.size()>1) {
			String message = "结果集应该为1， 实际为"+list.size();
			throw new UniqueException(this.getClass(), message);
		} else if(list.size() == 0){
			String message = "不存在用户名为"+loginName+"的用户";
			logger.warn(message);
		} else {
			account = list.get(0);
		}
		return account;
	}

	@Override
	public PageList<LoginAccount> query(PageList<LoginAccount> page, LoginAccount currentUser, LoginAccount account) {
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
		if(account != null) {
			if(account.getLoginName() != null && StringUtil.isNotEmpty(account.getLoginName())) {
				hql.append(" and t.loginName like ?");
				params.add("%"+account.getLoginName()+"%");
			}
			if(account.getState() != null) {
				hql.append(" and t.state = ?");
				params.add(account.getState());
			}
		}
		return super.query(page, hql.toString(), params.toArray());
	}

	@Override
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	public void delete(Long[] ids) {
		super.delete(ids);
	}

	@Override
	public void reset(Long id) {
		//TODO 密码加密后此处需要对默认密码进行加密处理
		String hql = "update LoginAccount set password = '88888888' where id = ?";
		Query session = getSession().createQuery(hql);
		session.setParameter(0, id);
		session.executeUpdate();
	}

	@Override
	public void reset(Long[] ids) {
		String hql = "update LoginAccount set password = '88888888' where id in :ids";
		Query session = getSession().createQuery(hql);
		session.setParameterList("ids", ids);
		session.executeUpdate();
	}

	@Override
	public LoginAccount get(Long id) {
		return super.get(id);
	}
	
}
