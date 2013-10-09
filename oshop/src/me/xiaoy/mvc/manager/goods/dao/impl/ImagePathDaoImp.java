package me.xiaoy.mvc.manager.goods.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import me.xiaoy.core.base.BaseDao;
import me.xiaoy.mvc.manager.goods.dao.ImagePathDao;
import me.xiaoy.mvc.manager.goods.entity.GoodsImagePath;
@Repository(value="imagePathDao")
public class ImagePathDaoImp extends BaseDao<GoodsImagePath> implements ImagePathDao {

	
	public Serializable save(GoodsImagePath goodsImagePath) {
		return super.save(goodsImagePath);
	}

	
	public List<GoodsImagePath> getGoodsImagePathByGoodsId(Long goodsId) {
		StringBuffer hql = new StringBuffer("from " + GoodsImagePath.class.getName() + " as t where 1=1 and t.goodsId="+goodsId+"");
		return super.query(hql.toString());
	}
	
	public void update(GoodsImagePath goodsImagePath){
		super.update(goodsImagePath);
	}


	@Override
	public void deleteGoodsImagePathInimageId(String imageIds) {
		String hql = "delete from " + entityClass.getName() + " where imageId in ("+imageIds+")";
		Query session = getSession().createQuery(hql);
		session.executeUpdate();
	}


	@Override
	public void deleteGoodsImagePathByGoodsId(Long goodsId) {
		String hql = "delete from " + entityClass.getName() + " where goodsId = "+goodsId+"";
		Query session = getSession().createQuery(hql);
		session.executeUpdate();
	}

}
