package me.xiaoy.mvc.manager.security.dao;

import java.io.Serializable;
import java.util.List;

import me.xiaoy.core.common.page.PageList;
import me.xiaoy.mvc.manager.security.entity.LoginAccount;

public interface LoginAccountDao {

	void updateLoginFailureCount(LoginAccount account);

	LoginAccount get(LoginAccount account);
	
	LoginAccount get(Long id);

	Long save(List<LoginAccount> list);
	
	Serializable save(LoginAccount account);

	LoginAccount getLoginAccountByLoginName(String accountName);

	PageList<LoginAccount> query(PageList<LoginAccount> page, LoginAccount currentUser, LoginAccount account);

	void delete(Long id);

	void delete(Long[] ids);

	void reset(Long id);

	void reset(Long[] ids);

	void update(LoginAccount account);

}
