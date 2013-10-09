package me.xiaoy.mvc.manager.goods.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock_ControlProperty")
/**
 * 商品库存信息属性表
 */
public class StockControlProperty implements Serializable {
	private static final long serialVersionUID = 1622879438743819786L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "scpId", unique = true, nullable = false)
	//主键
	private Long scpId;
	//  库存主键StockId
	@Column(name = "stockId")
	private Long stockId;
	// 文字展示
	@Column(name = "scpName")
	private String scpName;
	// 图片地址
	@Column(name = "picPath")
	private String picPath;
	@Column(name = "tempString")
	private String tempString;
	
	public Long getScpId() {
		return scpId;
	}

	public void setScpId(Long scpId) {
		this.scpId = scpId;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public String getScpName() {
		return scpName;
	}

	public void setScpName(String scpName) {
		this.scpName = scpName;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getTempString() {
		return tempString;
	}

	public void setTempString(String tempString) {
		this.tempString = tempString;
	}


}
