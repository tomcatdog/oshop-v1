package me.xiaoy.mvc.manager.security.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import me.xiaoy.core.base.BaseModel;

@Entity
@Table(name="sec_login_account")
public class LoginAccount extends BaseModel {
	
	@Column(name="login_name", unique=true, nullable=false)
	private String loginName;
	
	@Column(name="login_pwd")
	private String password;
	
	/**管理级别， 1 店铺管理员  2 店铺信息发布 3 店铺结算**/
	@Column(name="grade")
	private Integer grade;
	
	@Transient
//	private String gradeName;
	
	@Column(name="sec_email")
	private String secEmail;
	
	@Column(name="sec_mobile")
	private String secMobile;
	
	/*0禁用  1正常 2锁定 3密码过期 4账号废弃*/
	@Column
	private Integer state;
	
	@Transient
	public String getStateName() {
		return LoginAccountStateEnum.valueOf(String.valueOf(this.state)).getLabel();
	}
	
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name="failure_count")
	private Integer loginFailureCount;
	
	@Column(name="last_login_time")
	private Date lastLoginTime;
	
	/**
	 * 一个用户只允许拥有一个角色， 而一个角色允许被多个用户同时拥有， 所以为多对一
	 * optional=true, 允许role为空
	 * 
	 */
	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(
		name = "sec_account_role_permission", 
		joinColumns = {@JoinColumn(name="account_id", referencedColumnName="id")}, 
		inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName="id", nullable=true)}
	)  
	private List<Role> roleList;
	
	/**
	 * hibernate默认会将中间表的permission_id字段设置为非空， 将account_id设置为主键， 需要手工修改数据库
	 */
	@ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
        name = "sec_account_role_permission",
        joinColumns = {@JoinColumn(name="account_id", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="permission_id", referencedColumnName="id", nullable=true)}
    )
	private List<Permission> permissionListForAccount;
	
	/**
	 * 多对一， 对应商户实体
	 */
//	@Column(name="merchant_id")
//	private Long merchantId;
	
	public LoginAccount() {
		super();
	}

	public LoginAccount(String loginName, String password,
			Integer grade, String secEmail, String secMobile, Integer state,
			Integer loginFailureCount, List<Role> roleList,
			List<Permission> permissionListForAccount, Date createTime) {
		super();
		this.loginName = loginName;
		this.password = password;
		this.grade = grade;
		this.secEmail = secEmail;
		this.secMobile = secMobile;
		this.state = state;
		this.loginFailureCount = loginFailureCount;
		this.roleList = roleList;
		this.permissionListForAccount = permissionListForAccount;
		this.createTime = createTime;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getSecEmail() {
		return secEmail;
	}

	public void setSecEmail(String secEmail) {
		this.secEmail = secEmail;
	}

	public String getSecMobile() {
		return secMobile;
	}

	public void setSecMobile(String secMobile) {
		this.secMobile = secMobile;
	}
	
	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<Permission> getPermissionListForAccount() {
		return permissionListForAccount;
	}

	public void setPermissionListForAccount(
			List<Permission> permissionListForAccount) {
		this.permissionListForAccount = permissionListForAccount;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	public boolean isSuperAdmin() {
		// TODO 判断是否为超级管理员
		return false;
	}

}