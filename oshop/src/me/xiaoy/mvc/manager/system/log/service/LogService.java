package me.xiaoy.mvc.manager.system.log.service;

import me.xiaoy.core.common.page.PageList;
import me.xiaoy.mvc.manager.security.entity.LoginAccount;
import me.xiaoy.mvc.manager.system.log.entity.Log;

public interface LogService {

	/**
	 * 添加用户操作日志
	 * @param merchant
	 * @param ip
	 * @param operation
	 */
	public void save(LoginAccount account, String ip, String operation);

	public PageList<Log> query(PageList<Log> p, Log log);

	public void delete(Long[] ids);

	public void delete(Long id);

}
