package me.xiaoy.mvc.manager.goods.dao;

import java.io.Serializable;
import me.xiaoy.core.common.page.PageList;
import me.xiaoy.mvc.manager.goods.entity.GoodsInfo;

public interface InfoDao {

	Serializable save(GoodsInfo goodsInfo);
	
	PageList<GoodsInfo> list(PageList<GoodsInfo> page, GoodsInfo goodsInfo);
	
	GoodsInfo get(Long id);
	
	void update(GoodsInfo goodsInfo);
	
	void delete(Long id);
	
	void delete(Long[] ids);

}
