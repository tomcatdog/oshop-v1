package me.xiaoy.mvc.manager.security.service;

import java.util.List;

import me.xiaoy.core.common.page.PageList;
import me.xiaoy.mvc.manager.security.entity.LoginAccount;
import me.xiaoy.mvc.manager.security.entity.Role;

public interface RoleService {
	
	public Role get(Long id);
	
	public void save(Role role);
	
	public void delete(Role role);
	
	public void delete(LoginAccount currentUser, String ip, Long id);
	
	public void update(Role role);

	public Long save(List<Role> roles);

	public PageList<Role> query(PageList<Role> page, LoginAccount currentUser, Role role);

	


}
