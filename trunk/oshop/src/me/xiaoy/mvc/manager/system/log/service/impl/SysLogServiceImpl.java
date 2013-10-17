package me.xiaoy.mvc.manager.system.log.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import me.xiaoy.core.common.page.PageList;
import me.xiaoy.mvc.manager.security.entity.LoginAccount;
import me.xiaoy.mvc.manager.system.log.dao.SysLogDao;
import me.xiaoy.mvc.manager.system.log.entity.SysLog;
import me.xiaoy.mvc.manager.system.log.service.SysLogService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("logService")
@Transactional
public class SysLogServiceImpl implements SysLogService {

	@Resource
	private SysLogDao logDao;
	
	public void save(LoginAccount account, String ip, String operation) {
		SysLog log = new SysLog();
		log.setIp(ip);
		if(account != null) {
			log.setAccountId(account.getId());
			log.setUserName(account.getLoginName());
		}
		log.setOperation(operation);
		log.setCreateTime(new Date());
		logDao.save(log);
	}

	public PageList<SysLog> query(PageList<SysLog> p, SysLog log) {
		return logDao.list(p, log);
	}

	public void delete(Long[] ids) {
		logDao.delete(ids);
	}

	public void delete(Long id) {
		logDao.delete(id);
	}

}
