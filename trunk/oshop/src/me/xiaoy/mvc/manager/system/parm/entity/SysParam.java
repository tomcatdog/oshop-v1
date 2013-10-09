package me.xiaoy.mvc.manager.system.parm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import me.xiaoy.core.base.BaseModel;

@Entity
@Table(name = "t_param")
public class SysParam extends BaseModel {

	/**
	 * 参数组名
	 */
	@Column(name="param_group")
	private String group;

	/**
	 * 参数名
	 */
	@Column(name = "param_name", nullable = false, length = 50)
	private String paramName;
	
	/**
	 * 参数值
	 */
	@Column(name = "param_value", nullable = false, length = 250)
	private String paramValue;

	/**
	 * 页面上是否可修改： 1 可修改   0 禁止修改
	 */
	@Column(name = "modifiable", nullable = false, length = 1)
	private Integer modifiable = null;
	
	/**
	 * 启动是否加载   1为加载， 0为启动不加载。如果启动时没有加载， 则每次需要时读取数据库
	 */
	@Column(name = "init")
	private Integer init = null;
	
	/**
	 * 备注
	 */
	@Column(name = "remark", length = 255)
	private String remark;

	public SysParam() {
		super();
	}

	public SysParam(String group, String paramName, String paramValue,
			Integer modifiable, Integer init, String remark, Date createTime) {
		super();
		this.group = group;
		this.paramName = paramName;
		this.paramValue = paramValue;
		this.modifiable = modifiable;
		this.init = init;
		this.remark = remark;
		super.createTime = createTime;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public Integer getModifiable() {
		return modifiable;
	}

	public void setModifiable(Integer modifiable) {
		this.modifiable = modifiable;
	}

	public Integer getInit() {
		return init;
	}

	public void setInit(Integer init) {
		this.init = init;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}