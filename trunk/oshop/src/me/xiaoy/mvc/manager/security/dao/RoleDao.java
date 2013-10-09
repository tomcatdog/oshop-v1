package me.xiaoy.mvc.manager.security.dao;

import java.io.Serializable;
import java.util.List;

import me.xiaoy.core.common.page.PageList;
import me.xiaoy.mvc.manager.security.entity.LoginAccount;
import me.xiaoy.mvc.manager.security.entity.Role;

public interface RoleDao {

	Serializable save(Role role);

	void update(Role role);

	void delete(Role role);

	Long save(List<Role> roles);

	PageList<Role> query(PageList<Role> page,
			LoginAccount currentUser, Role role);

	void delete(Long id);

	Role get(Long id);

}
