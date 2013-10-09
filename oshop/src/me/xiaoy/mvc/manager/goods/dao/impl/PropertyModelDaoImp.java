package me.xiaoy.mvc.manager.goods.dao.impl;

import java.io.Serializable;
import java.util.List;
import me.xiaoy.core.base.BaseDao;
import me.xiaoy.mvc.manager.goods.dao.PropertyModelDao;
import me.xiaoy.mvc.manager.goods.entity.GoodsPropertyModel;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "propertyModelDao")
public class PropertyModelDaoImp extends BaseDao<GoodsPropertyModel> implements PropertyModelDao {
	
	public Serializable save(GoodsPropertyModel goodsPropertyModel) {
		return super.save(goodsPropertyModel);
	}

	public List<GoodsPropertyModel> getGoodsPropertyModelBygoodsTypeId(
			Long goodsTypeId) {
		StringBuffer hql = new StringBuffer("from " + GoodsPropertyModel.class.getName() + " as t where 1=1 and t.goodsTypeId="+goodsTypeId+"");
		return super.query(hql.toString());
	}

	public void deleteGoodsPropertyModelBygoodsTypeId(Long goodsTypeId) {
		String hql = "delete from " + entityClass.getName() + " where goodsTypeId = "+goodsTypeId+"";
		Query session = getSession().createQuery(hql);
		session.executeUpdate();
	}
	
}
