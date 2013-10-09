package me.xiaoy.mvc.manager.security.service.impl;

import java.util.List;

import javax.annotation.Resource;

import me.xiaoy.mvc.manager.security.dao.PermissionDao;
import me.xiaoy.mvc.manager.security.entity.Permission;
import me.xiaoy.mvc.manager.security.service.PermissionService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService{

	@Resource
	private PermissionDao permissionDao;
	
	@Override
	public Long save(List<Permission> list) {
		return permissionDao.save(list);
	}

	@Override
	public List<Permission> query() {
		return permissionDao.query();
	}

}
