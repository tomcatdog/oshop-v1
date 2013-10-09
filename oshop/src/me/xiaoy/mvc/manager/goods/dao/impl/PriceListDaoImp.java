package me.xiaoy.mvc.manager.goods.dao.impl;
import java.io.Serializable;
import java.util.List;
import me.xiaoy.core.base.BaseDao;
import me.xiaoy.mvc.manager.goods.dao.PriceListDao;
import me.xiaoy.mvc.manager.goods.entity.StockPriceList;
import org.springframework.stereotype.Repository;

@Repository(value = "priceListDao")
public class PriceListDaoImp extends BaseDao<StockPriceList> implements PriceListDao {

	public Serializable save(StockPriceList stockPriceList) {
		return super.save(stockPriceList);
	}

	public List<StockPriceList> getStockPriceListByGoodsId(Long goodsId) {
		StringBuffer hql = new StringBuffer("from " + entityClass.getName() + " as t where 1=1 and t.goodsId="+goodsId+"");
		return super.query(hql.toString());
	}

	public void update(StockPriceList stockPriceList){
		super.update(stockPriceList);
	}

}
