package me.xiaoy.mvc.manager.goods.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock_PriceList")
public class StockPriceList implements Serializable {
	private static final long serialVersionUID = 714545509312495812L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	// 主键
	private Long id;
	// 外健(商品Id)
	@Column(name = "goodsId")
	private Long goodsId;
	// 套餐组合类型
	@Column(name = "packageType")
	private String packageType;
	// 套餐组合类型中文名称
	@Column(name = "packageTypeName")
	private String packageTypeName;
	// 促销价格
	@Column(name = "salesPrice")
	private BigDecimal salesPrice;
	// 价格
	@Column(name = "price")
	private BigDecimal price;
	// 商品数量
	@Column(name = "num")
	private int num;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public BigDecimal getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getPackageTypeName() {
		return packageTypeName;
	}

	public void setPackageTypeName(String packageTypeName) {
		this.packageTypeName = packageTypeName;
	}

}
