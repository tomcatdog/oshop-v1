package me.xiaoy.mvc.manager.goods.dao;

import java.io.Serializable;
import java.util.List;

import me.xiaoy.mvc.manager.goods.entity.GoodsProperty;

public interface PropertyDao {

	Serializable save(GoodsProperty goodsProperty);
	
	List<GoodsProperty> getgoodsPropertyByGoodsId(Long id);
	
	void deleteGoodsPropertyByGoodsId(Long goodsId);
	
	void deleteGoodsPropertyByGoodsId(Long[] ids);
	
}
