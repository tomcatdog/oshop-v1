package me.xiaoy.mvc.manager.goods.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "goods_Property")
/**
 * 商品属性表
 */
public class GoodsProperty implements Serializable {

	private static final long serialVersionUID = -3034578400249681076L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "goodsPropertyId", unique = true, nullable = false)
	private Long goodsPropertyId;
	@Column(name = "propertyModelId")
	private Long propertyModelId;
	@Column(name = "goodsId")
	private Long goodsId;
	@Column(name = "goodsPropertyIdValue")
	private String goodsPropertyIdValue;

	public Long getGoodsPropertyId() {
		return goodsPropertyId;
	}

	public void setGoodsPropertyId(Long goodsPropertyId) {
		this.goodsPropertyId = goodsPropertyId;
	}

	public Long getPropertyModelId() {
		return propertyModelId;
	}

	public void setPropertyModelId(Long propertyModelId) {
		this.propertyModelId = propertyModelId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsPropertyIdValue() {
		return goodsPropertyIdValue;
	}

	public void setGoodsPropertyIdValue(String goodsPropertyIdValue) {
		this.goodsPropertyIdValue = goodsPropertyIdValue;
	}

}
