package me.xiaoy.mvc.manager.goods.dao;

import java.io.Serializable;
import java.util.List;
import me.xiaoy.mvc.manager.goods.entity.StockPriceList;

public interface PriceListDao {
	Serializable save(StockPriceList stockPriceList);

	List<StockPriceList> getStockPriceListByGoodsId(Long goodsId);
	
	void update(StockPriceList stockPriceList);
}
