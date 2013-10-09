package me.xiaoy.mvc.manager.goods.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "goods_ImagePath")
/**
 * 图片上传信息表
 */
public class GoodsImagePath implements Serializable {
	private static final long serialVersionUID = -6607385097301249530L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_Id", unique = true, nullable = false)
	// 主键id
	private Long imageId;
	// 商品表id
	@Column(name = "goodsId")
	private Long goodsId;
	// 图片名
	@Column(name = "imageName")
	private String imageName;
	// 图片路径
	@Column(name = "imagePath")
	private String imagePath;
	// 是否首图展示(1为展示)
	@Column(name = "homedisplay")
	private int homedisplay;

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getHomedisplay() {
		return homedisplay;
	}

	public void setHomedisplay(int homedisplay) {
		this.homedisplay = homedisplay;
	}

}
