package me.xiaoy.mvc.manager.goods.dao;

import java.io.Serializable;
import java.util.List;

import me.xiaoy.mvc.manager.goods.entity.GoodsImagePath;
public interface ImagePathDao {
	Serializable save(GoodsImagePath goodsImagePath);
	
	List<GoodsImagePath> getGoodsImagePathByGoodsId(Long goodsId);
	
	void update(GoodsImagePath goodsImagePath);
	
	void deleteGoodsImagePathInimageId(String imageIds);
	
	void deleteGoodsImagePathByGoodsId(Long goodsId);
}
