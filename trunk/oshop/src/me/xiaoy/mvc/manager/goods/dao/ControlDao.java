package me.xiaoy.mvc.manager.goods.dao;

import java.io.Serializable;
import java.util.List;
import me.xiaoy.mvc.manager.goods.entity.StockControl;

public interface ControlDao {

	Serializable save(StockControl stockControl);

	void deleteStockControlByGoodsId(Long goodsId);
	
	void deleteStockControlInStockId(String StockId);

	List<StockControl> getStockControlByGoodsId(Long goodsId);
	
	void updateStockControl(StockControl stockControl);
}
