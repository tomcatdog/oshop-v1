package me.xiaoy.mvc.manager.goods.dao.impl;
import java.io.Serializable;
import java.util.List;
import me.xiaoy.core.base.BaseDao;
import me.xiaoy.mvc.manager.goods.dao.ControlDao;
import me.xiaoy.mvc.manager.goods.entity.StockControl;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "controlDao")
public class ControlDaoImp extends BaseDao<StockControl> implements ControlDao {

	public Serializable save(StockControl stockControl) {
		return super.save(stockControl);
	}

	@Override
	public void deleteStockControlByGoodsId(Long goodsId) {
		String hql = "delete from " + entityClass.getName() + " where goodsId = "+goodsId+"";
		Query session = getSession().createQuery(hql);
		session.executeUpdate();
	}

	@Override
	public List<StockControl> getStockControlByGoodsId(Long goodsId) {
		StringBuffer hql = new StringBuffer("from " + StockControl.class.getName() + " as t where 1=1 and t.goodsId="+goodsId+"");
		return super.query(hql.toString());
	}

	@Override
	public void updateStockControl(StockControl stockControl) {
		super.update(stockControl);
	}

	@Override
	public void deleteStockControlInStockId(String StockId) {
		String hql = "delete from " + entityClass.getName() + " where stockId in ("+StockId+")";
		Query session = getSession().createQuery(hql);
		session.executeUpdate();
	}

}
