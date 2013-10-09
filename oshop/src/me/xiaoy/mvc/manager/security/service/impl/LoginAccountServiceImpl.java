package me.xiaoy.mvc.manager.security.service.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import me.xiaoy.core.common.page.PageList;
import me.xiaoy.core.utils.StringUtil;
import me.xiaoy.mvc.manager.security.dao.LoginAccountDao;
import me.xiaoy.mvc.manager.security.entity.LoginAccount;
import me.xiaoy.mvc.manager.security.service.LoginAccountService;
import me.xiaoy.mvc.manager.system.log.service.LogService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="loginAccountService")
@Transactional
public class LoginAccountServiceImpl implements LoginAccountService{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginAccountServiceImpl.class);
	
	@Resource
	private LoginAccountDao loginDao;
	
	@Resource
	private LogService logService;
	
	public void updateLoginFailureCount(LoginAccount account){
		loginDao.updateLoginFailureCount(account);
	}
	
	public int getLoginFailureCount(LoginAccount account) {
		int result = 0;
		LoginAccount accountDB = loginDao.get(account);
		if(accountDB!=null) {
			result = accountDB.getLoginFailureCount();
		}
		return result;
	}

	@Override
	public Long save(List<LoginAccount> list) {
		return loginDao.save(list);
	}

	@Override
	public LoginAccount getLoginAccountByLoginName(String accountName) {
		LoginAccount account = null;
		if(StringUtil.isNotEmpty(accountName)) {
			account = loginDao.getLoginAccountByLoginName(accountName);
		}
		return account;
	}

	@Override
	public PageList<LoginAccount> query(PageList<LoginAccount> page, LoginAccount currentUser, LoginAccount account) {
		return loginDao.query(page, currentUser, account);
	}

	@Override
	public void delete(LoginAccount currentUser, String ip, Long id) {
		try {
			loginDao.delete(id);
			logService.save(currentUser, ip, "删除管理员 ： " + id);
		} catch (Exception e) {
			logger.error("删除管理员失败： " + id, e);
		}
	}

	@Override
	public void delete(LoginAccount currentUser, String ip, Long[] ids) {
		try {
			loginDao.delete(ids);
			logService.save(currentUser, ip, "删除管理员 ： " + Arrays.toString(ids));
		} catch (Exception e) {
			logger.error("删除管理员失败： " + Arrays.toString(ids), e);
		}
	}

	@Override
	public void reset(Long id) {
		loginDao.reset(id);
	}

	@Override
	public void reset(Long[] ids) {
		loginDao.reset(ids);
	}

	@Override
	public Serializable save(LoginAccount account) {
		return loginDao.save(account);
	}

	@Override
	public LoginAccount get(Long id) {
		return loginDao.get(id);
	}

	@Override
	public void update(LoginAccount account) {
		loginDao.update(account);
	}

}
