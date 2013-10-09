package me.xiaoy.mvc.manager.goods.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.xiaoy.core.base.BaseDao;
import me.xiaoy.core.common.page.PageList;
import me.xiaoy.core.utils.StringUtil;
import me.xiaoy.mvc.manager.goods.dao.GoodsDao;
import me.xiaoy.mvc.manager.goods.entity.GoodsType;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository(value="goodsDao")
public class GoodsDaoImp extends BaseDao<GoodsType> implements GoodsDao {

	public Serializable save(GoodsType goodsType) {
		return super.save(goodsType);
	}

	public PageList<GoodsType> list(PageList<GoodsType> page,GoodsType goodsType) {
		StringBuffer hql = new StringBuffer("from " + GoodsType.class.getName() + " as t where 1=1");
		List<Object> params = new ArrayList<Object>();
		if(goodsType != null) {
			if(goodsType.getTypeName()!=null && StringUtil.isNotEmpty(goodsType.getTypeName())) {
				hql.append(" and t.typeName like ? ");
				params.add("%"+goodsType.getTypeName()+"%");
			}
		}
		return super.query(page, hql.toString(), params.toArray());
	}

	public void delete(Long[] ids) {
		String hql = "delete from " + entityClass.getName() + " where goodsTypeId in :ids";
		Query session = getSession().createQuery(hql);
		session.setParameterList("ids", ids);
		session.executeUpdate();
	}

	public void delete(Long id) {
		super.delete(id);
		
	}

	public GoodsType get(Long id) {
		return super.get(id);
	}
	
	public void update(GoodsType goodsType){
		super.update(goodsType);
	}
}
