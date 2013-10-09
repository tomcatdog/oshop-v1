package me.xiaoy.mvc.manager.security.dao.impl;

import java.util.List;

import me.xiaoy.core.base.BaseDao;
import me.xiaoy.mvc.manager.security.dao.PermissionDao;
import me.xiaoy.mvc.manager.security.entity.Permission;

import org.springframework.stereotype.Repository;

@Repository(value="permissionDao")
public class PermissionDaoHibernate extends BaseDao<Permission> implements PermissionDao {

	@Override
	public List<Permission> query() {
		return super.query("from Permission order by id");
	}


}
