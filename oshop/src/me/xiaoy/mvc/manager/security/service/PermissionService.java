package me.xiaoy.mvc.manager.security.service;

import java.util.List;

import me.xiaoy.mvc.manager.security.entity.Permission;

public interface PermissionService {

	Long save(List<Permission> list);

	public List<Permission> query();

}
