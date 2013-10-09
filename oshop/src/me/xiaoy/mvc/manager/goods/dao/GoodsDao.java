package me.xiaoy.mvc.manager.goods.dao;

import java.io.Serializable;

import me.xiaoy.core.common.page.PageList;
import me.xiaoy.mvc.manager.goods.entity.GoodsType;

public interface GoodsDao {

	Serializable save(GoodsType goodsType);

	PageList<GoodsType> list(PageList<GoodsType> page, GoodsType goodsType);

	void delete(Long[] ids);

	void delete(Long id);

	GoodsType get(Long id);
	
	void update(GoodsType goodsType);

}
