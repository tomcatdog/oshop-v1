package me.xiaoy.mvc.manager.goods.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock_Control")
/**
 * 商品库存信息表
 */
public class StockControl implements Serializable {

	private static final long serialVersionUID = -6696479183773611817L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stockId", unique = true, nullable = false)
	// 库存主键id
	private Long stockId;
	// 商品表id
	@Column(name = "goodsId")
	private Long goodsId;
	// 商品销售属性名
	@Column(name = "salesOfProperty")
	private String salesOfProperty;
	// 属性顺序
	@Column(name = "orderProperty")
	private int orderProperty;
	// 是否以文字方式展示属性类型 默认是0表示:文字展示  ,1表示图片
	@Column(name = "controlPropertyType")
	private int controlPropertyType;

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getSalesOfProperty() {
		return salesOfProperty;
	}

	public void setSalesOfProperty(String salesOfProperty) {
		this.salesOfProperty = salesOfProperty;
	}

	public int getOrderProperty() {
		return orderProperty;
	}

	public void setOrderProperty(int orderProperty) {
		this.orderProperty = orderProperty;
	}

	public int getControlPropertyType() {
		return controlPropertyType;
	}

	public void setControlPropertyType(int controlPropertyType) {
		this.controlPropertyType = controlPropertyType;
	}

}
