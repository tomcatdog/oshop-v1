package me.xiaoy.mvc.manager.security.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import me.xiaoy.core.base.BaseIdModel;

@Entity
@Table(name="sec_role")
public class Role extends BaseIdModel{
	
	
	@Column(name = "role_name")
	private String roleName;
	
	@Column(name = "role_desc")
	private String roleDesc;
	
	/**
	 * 一个角色可以被多个用户拥有，一个用户可以拥有多个角色， 所以为多对多
	 * *ToMany 默认为懒加载， 即fetch = FetchType.LAZY
	 */
	@ManyToMany(cascade = CascadeType.REFRESH, mappedBy="roleList")
	private List<LoginAccount> accountList;
	
	/**
	 * 角色-权限表
	 * 角色是维护端， 权限是被维护端
	 */
	@ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
        name = "sec_role_permission",
        joinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="permission_id", referencedColumnName="id")}
    )
	private List<Permission> permissionList;
	
	

	public Role() {}
	
	public Role(String roleName, String roleDesc,
			List<LoginAccount> accountList, List<Permission> permissionList) {
		super();
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.accountList = accountList;
		this.permissionList = permissionList;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	public List<LoginAccount> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<LoginAccount> accountList) {
		this.accountList = accountList;
	}

}