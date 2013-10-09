package me.xiaoy.mvc.manager.security.service;

import java.io.Serializable;
import java.util.List;

import me.xiaoy.core.common.page.PageList;
import me.xiaoy.mvc.manager.security.entity.LoginAccount;

public interface LoginAccountService {

	/**
	 * 更新账号登录失败次数 
	 */
	public void updateLoginFailureCount(LoginAccount account);

	/**
	 * 获取更新失败次数
	 */
	public int getLoginFailureCount(LoginAccount account);

	public Long save(List<LoginAccount> list);
	
	public Serializable save(LoginAccount account);

	/**
	 * 通过登录名查找登录用户
	 * @param loginName 登录名
	 * @return
	 */
	public LoginAccount getLoginAccountByLoginName(String loginName);

	/**
	 * 分页查询帐号列表
	 * @param page 分页实体
	 * @param currentUser 当前登录用户 
	 * @param account 查询条件
	 * @return
	 */
	public PageList<LoginAccount> query(PageList<LoginAccount> page, LoginAccount currentUser, LoginAccount account);

	public void delete(LoginAccount currentUser, String ip, Long id);

	public void delete(LoginAccount currentUser, String ip, Long[] ids);

	public void reset(Long id);

	public void reset(Long[] ids);

	public LoginAccount get(Long id);

	public void update(LoginAccount account);

	

}