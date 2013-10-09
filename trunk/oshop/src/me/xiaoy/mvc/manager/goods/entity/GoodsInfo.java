package me.xiaoy.mvc.manager.goods.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "goods_Info")
/**
 * 商品信息表
 */
public class GoodsInfo implements Serializable {

	private static final long serialVersionUID = 214315775214143984L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "goodsId", unique = true, nullable = false)
	private Long goodsId;
	@Column(name = "goodsName")
	private String goodsName;
	@Column(name = "property")
	private Long property;
	@Column(name = "saleType")
	private int saleType;
	@Column(name = "goodsTypeId")
	private Long goodsTypeId;

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Long getProperty() {
		return property;
	}

	public void setProperty(Long property) {
		this.property = property;
	}

	public int getSaleType() {
		return saleType;
	}

	public void setSaleType(int saleType) {
		this.saleType = saleType;
	}

	public Long getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(Long goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

}
