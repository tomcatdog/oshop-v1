package me.xiaoy.mvc.manager.security.dao;

import java.util.List;

import me.xiaoy.mvc.manager.security.entity.Permission;

public interface PermissionDao {

	Long save(List<Permission> list);

	public List<Permission> query();

}
