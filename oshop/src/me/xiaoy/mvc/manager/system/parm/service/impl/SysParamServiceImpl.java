package me.xiaoy.mvc.manager.system.parm.service.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import me.xiaoy.core.common.page.PageList;
import me.xiaoy.mvc.manager.security.entity.LoginAccount;
import me.xiaoy.mvc.manager.security.service.impl.LoginAccountServiceImpl;
import me.xiaoy.mvc.manager.system.log.service.LogService;
import me.xiaoy.mvc.manager.system.parm.dao.SysParamDao;
import me.xiaoy.mvc.manager.system.parm.entity.SysParam;
import me.xiaoy.mvc.manager.system.parm.service.SysParamService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="sysParamService")
@Transactional
public class SysParamServiceImpl implements SysParamService{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginAccountServiceImpl.class);
	
	@Resource
	private LogService logService;
	
	@Resource
	private SysParamDao sysParamDao;

	@Override
	public Long save(List<SysParam> list) {
		return sysParamDao.save(list);
	}

	@Override
	public PageList<SysParam> query(PageList<SysParam> page, LoginAccount currentUser, SysParam param) {
		return sysParamDao.query(page, currentUser, param);
	}

	@Override
	public Serializable save(SysParam param) {
		return sysParamDao.save(param);
	}

	@Override
	public SysParam get(Long id) {
		return sysParamDao.get(id);
	}

	@Override
	public void update(SysParam param) {
		sysParamDao.update(param);
	}

	@Override
	public List<SysParam> getInitParams() {
		return sysParamDao.getInitParams();
	}

	@Override
	public String getParam(String key) {
		return sysParamDao.getParam(key);
	}

	@Override
	public void delete(LoginAccount currentUser, String ip, Long id) {
		try {
			sysParamDao.delete(id);
			logService.save(currentUser, ip, "删除参数 ： " + id);
		} catch (Exception e) {
			logger.error("删除参数失败： " + id, e);
		}
	}

	@Override
	public void delete(LoginAccount currentUser, String ip, Long[] ids) {
		try {
			sysParamDao.delete(ids);
			logService.save(currentUser, ip, "删除参数： " + Arrays.toString(ids));
		} catch (Exception e) {
			logger.error("删除参数失败： " + Arrays.toString(ids), e);
		}
	}

	@Override
	public List<String> getGroup() {
		return sysParamDao.getGroup();
	}

}
