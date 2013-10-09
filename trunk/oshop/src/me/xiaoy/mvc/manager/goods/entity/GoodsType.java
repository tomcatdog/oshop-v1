package me.xiaoy.mvc.manager.goods.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "goods_Type")
/**
 * 分类商品表
 */
public class GoodsType implements Serializable {

	private static final long serialVersionUID = 5107826151033336751L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "goodsTypeId", unique = true, nullable = false)
	private Long goodsTypeId;
	@Column(name = "typeName")
	private String typeName;
	@Column(name = "fatherNode")
	private Long fatherNode;
	@Column(name = "nodeGrade")
	private int nodeGrade;

	public Long getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(Long goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getFatherNode() {
		return fatherNode;
	}

	public void setFatherNode(Long fatherNode) {
		this.fatherNode = fatherNode;
	}

	public int getNodeGrade() {
		return nodeGrade;
	}

	public void setNodeGrade(int nodeGrade) {
		this.nodeGrade = nodeGrade;
	}

}
