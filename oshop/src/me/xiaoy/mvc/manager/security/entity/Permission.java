package me.xiaoy.mvc.manager.security.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import me.xiaoy.core.base.BaseIdModel;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="sec_permission")
public class Permission extends BaseIdModel{

	@Column(name="per_name")
	private String perName;
	
	@Column(name="per_desc")
	private String perDesc;
	
	@Column
	private String url;
	
	@Column
	private String operation;
	
	@Column(name="order_num")
	private int orderNum;
	
	/**
	 * 1. @ManyToMany 增加mappedBy="permissionList"属性后， 双向关系维护端为role， 被维护端为permission
	 *    在进行保存操作的时候如果存在role.setPermissionList(list); 则会向关联表中插入数据
	 *    同时如果在新增permission时，如果存在permission.setRoleList(list);则不会想关联表插入数据
	 * 2. @ManyToMany双向均设置@JoinTable后， 关系两端均可维护中间表
	 */
	@ManyToMany(cascade = CascadeType.REFRESH, mappedBy="permissionList")
	private List<Role> roleList;
	
	@ManyToMany(cascade = CascadeType.REFRESH, mappedBy="permissionListForAccount")
	private List<LoginAccount> accountList;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id")
	private Permission parentPermission;
	
	@OneToMany(targetEntity = Permission.class, cascade = { CascadeType.ALL }, mappedBy = "parentPermission")  
    @Fetch(FetchMode.SUBSELECT)  
    @OrderBy("order") 
	private Set<Permission> permissions;
	
	public Permission() {
		super();
	}
	
	public Permission(Set<Permission> permissions) {
		super();
		this.permissions = permissions;
	}

	public Permission(String perName, String perDesc, String url,
			String operation, int orderNum, List<Role> roleList,
			List<LoginAccount> accountList, Permission parentPermission,
			Set<Permission> permissions) {
		super();
		this.perName = perName;
		this.perDesc = perDesc;
		this.url = url;
		this.operation = operation;
		this.orderNum = orderNum;
		this.roleList = roleList;
		this.accountList = accountList;
		this.parentPermission = parentPermission;
		this.permissions = permissions;
	}

	public String getPerName() {
		return perName;
	}

	public void setPerName(String perName) {
		this.perName = perName;
	}

	public String getPerDesc() {
		return perDesc;
	}

	public void setPerDesc(String perDesc) {
		this.perDesc = perDesc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<LoginAccount> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<LoginAccount> accountList) {
		this.accountList = accountList;
	}

	public Permission getParentPermission() {
		return parentPermission;
	}

	public void setParentPermission(Permission parentPermission) {
		this.parentPermission = parentPermission;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
}
