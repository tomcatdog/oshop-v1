package me.xiaoy.mvc.manager.goods.dao;

import java.io.Serializable;
import java.util.List;
import me.xiaoy.mvc.manager.goods.entity.GoodsPropertyModel;

public interface PropertyModelDao {

	Serializable save(GoodsPropertyModel goodsPropertyModel);
	
	List<GoodsPropertyModel> getGoodsPropertyModelBygoodsTypeId(Long goodsTypeId);
  
	void deleteGoodsPropertyModelBygoodsTypeId(Long goodsTypeId);
}
