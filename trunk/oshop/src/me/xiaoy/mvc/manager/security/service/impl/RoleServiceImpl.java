package me.xiaoy.mvc.manager.security.service.impl;

import java.util.List;

import javax.annotation.Resource;

import me.xiaoy.core.common.page.PageList;
import me.xiaoy.mvc.manager.security.dao.RoleDao;
import me.xiaoy.mvc.manager.security.entity.LoginAccount;
import me.xiaoy.mvc.manager.security.entity.Role;
import me.xiaoy.mvc.manager.security.service.RoleService;
import me.xiaoy.mvc.manager.system.log.service.LogService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Resource
	RoleDao roleDao;

	@Resource
	private LogService logService;
	
	@Override
	public Role get(Long id) {
		return roleDao.get(id);
	}
	
	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

	@Override
	public void delete(Role role) {
		roleDao.delete(role);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public Long save(List<Role> roles) {
		return roleDao.save(roles);
	}

	@Override
	public PageList<Role> query(PageList<Role> page,
			LoginAccount currentUser, Role role) {
		return roleDao.query(page, currentUser, role);
	}

	@Override
	public void delete(LoginAccount currentUser, String ip, Long id) {
		try {
			roleDao.delete(id);
			logService.save(currentUser, ip, "删除角色ID：" + id);
		} catch (Exception e) {
			logger.error("删除角色失败， ID："+id, e);
		}
	}

}
