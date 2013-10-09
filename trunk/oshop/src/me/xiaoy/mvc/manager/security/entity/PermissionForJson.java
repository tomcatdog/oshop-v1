package me.xiaoy.mvc.manager.security.entity;

import me.xiaoy.core.base.BaseIdModel;

public class PermissionForJson extends BaseIdModel{

	private String perName;
	
	private String perDesc;
	
	private String url;

	private String operation;
	
	private int orderNum;
	
	private Long parentId;

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

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
