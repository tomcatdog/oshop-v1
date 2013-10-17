package me.xiaoy.mvc.manager.system.log.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import me.xiaoy.core.base.BaseModel;

@Entity
@Table(name = "t_log")
public class SysLog extends BaseModel {

	@Column(name = "account_id")
	private Long accountId;
	
	@Column(name="user_name")
	private String userName;

	@Column(name = "ip", length = 128)
	private String ip;

	@Column(name = "operation", length = 256)
	private String operation;
	
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
