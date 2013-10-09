package me.xiaoy.mvc.manager.goods.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "goods_PropertyModel")
/**
 * 商品类型属性模板表
 */
public class GoodsPropertyModel implements Serializable {

	private static final long serialVersionUID = 8410055904614714422L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "modelId", unique = true, nullable = false)
	private Long modelId;
	@Column(name = "goodsTypeId")
	private Long goodsTypeId;
	@Column(name = "property")
	private String property;

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public Long getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(Long goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
