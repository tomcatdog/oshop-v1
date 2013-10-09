package me.xiaoy.mvc.manager.goods.dao.impl;

import java.io.Serializable;
import java.util.List;
import me.xiaoy.core.base.BaseDao;
import me.xiaoy.mvc.manager.goods.dao.PropertyDao;
import me.xiaoy.mvc.manager.goods.entity.GoodsProperty;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "propertyDao")
public class PropertyDaoImp extends BaseDao<GoodsProperty> implements PropertyDao {

	
	public Serializable save(GoodsProperty goodsProperty) {
		return super.save(goodsProperty);
	}

	
	public List<GoodsProperty> getgoodsPropertyByGoodsId(Long id) {
		StringBuffer hql = new StringBuffer("from " + GoodsProperty.class.getName() + " as t where 1=1 and t.goodsId="+id);
		return super.query(hql.toString());
	}

	
	public void deleteGoodsPropertyByGoodsId(Long goodsId) {
		String hql = "delete from " + entityClass.getName() + " where goodsId = "+goodsId+"";
		Query session = getSession().createQuery(hql);
		session.executeUpdate();
		
	}


	@Override
	public void deleteGoodsPropertyByGoodsId(Long[] ids) {
		String hql = "delete from " + entityClass.getName() + " where goodsId in :ids";
		Query session = getSession().createQuery(hql);
		session.setParameterList("ids", ids);
		session.executeUpdate();
	}

}
