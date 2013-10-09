package me.xiaoy.mvc.manager.merchant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import me.xiaoy.core.base.BaseModel;

@Entity
@Table(name="m_merchant")
public class Merchant extends BaseModel {
	
	@Column
	private String name;
	@Column
	private int grade;
	@Column
	private String address;
	
	//TODO 实体类完成后， 将此处修改为实体类
	@Column(name="contract_id")
	private Long contractId;
	
	@Column(name="city_id")
	private Long cityId;
	
	@Column(name="area_id")
	private Long areaId;
	
	@Column(name="trade_id")
	private Long tradeId;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public Long getTradeId() {
		return tradeId;
	}
	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}
	
}
