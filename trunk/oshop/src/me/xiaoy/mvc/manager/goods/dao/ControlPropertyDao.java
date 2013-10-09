package me.xiaoy.mvc.manager.goods.dao;

import java.io.Serializable;
import java.util.List;

import me.xiaoy.mvc.manager.goods.entity.StockControlProperty;

public interface ControlPropertyDao {

	Serializable save(StockControlProperty stockControlProperty);
	
	List<StockControlProperty> findStockControlPropertyInStockIds(String stockIds);
	
	void deleteStockControlPropertyInStockId(String StockId);
	
	void updateStockControlProperty(StockControlProperty stockControlProperty);
	
	void deleteStockControlPropertyInScpId(String scpIds);

}
