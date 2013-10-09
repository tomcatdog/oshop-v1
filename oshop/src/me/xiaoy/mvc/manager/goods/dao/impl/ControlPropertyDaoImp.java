package me.xiaoy.mvc.manager.goods.dao.impl;

import java.io.Serializable;
import java.util.List;

import me.xiaoy.core.base.BaseDao;
import me.xiaoy.mvc.manager.goods.dao.ControlPropertyDao;
import me.xiaoy.mvc.manager.goods.entity.StockControlProperty;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "controlPropertyDao")
public class ControlPropertyDaoImp extends BaseDao<StockControlProperty> implements ControlPropertyDao {

	
	public Serializable save(StockControlProperty stockControlProperty) {
		return super.save(stockControlProperty);
	}

	
	public List<StockControlProperty> findStockControlPropertyInStockIds(String stockIds) {
		StringBuffer hql = new StringBuffer("from " + entityClass.getName() + " as t where 1=1 and t.stockId in ("+stockIds+")");
		return super.query(hql.toString());
	}

	
	public void deleteStockControlPropertyInStockId(String StockId) {
		String hql = "delete from " + entityClass.getName() + " where stockId in ("+StockId+")";
		Query session = getSession().createQuery(hql);
		session.executeUpdate();
	}


	public void updateStockControlProperty(StockControlProperty stockControlProperty) {
		super.update(stockControlProperty);
	}


	@Override
	public void deleteStockControlPropertyInScpId(String scpIds) {
		String hql = "delete from " + entityClass.getName() + " where scpId in ("+scpIds+")";
		Query session = getSession().createQuery(hql);
		session.executeUpdate();
	}

}
