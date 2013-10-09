package me.xiaoy.mvc.manager.goods.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.xiaoy.core.base.BaseDao;
import me.xiaoy.core.common.page.PageList;
import me.xiaoy.core.utils.StringUtil;
import me.xiaoy.mvc.manager.goods.dao.InfoDao;
import me.xiaoy.mvc.manager.goods.entity.GoodsInfo;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository(value="infoDao")
public class InfoDaoImp extends BaseDao<GoodsInfo> implements InfoDao {

	public Serializable save(GoodsInfo goodsInfo) {
		return super.save(goodsInfo);
	}
	
	public PageList<GoodsInfo> list(PageList<GoodsInfo> page,GoodsInfo goodsInfo) {
		StringBuffer hql = new StringBuffer("from " + GoodsInfo.class.getName() + " as t where 1=1");
		List<Object> params = new ArrayList<Object>();
		if(goodsInfo != null) {
			if(goodsInfo.getGoodsName()!=null && StringUtil.isNotEmpty(goodsInfo.getGoodsName())) {
				hql.append(" and t.goodsName like ? ");
				params.add("%"+goodsInfo.getGoodsName()+"%");
			}
		}
		return super.query(page, hql.toString(), params.toArray());
	}

	@Override
	public GoodsInfo get(Long id) {
		return super.get(id);
	}
	
	public void update(GoodsInfo goodsInfo){
		super.update(goodsInfo);
	}

	@Override
	public void delete(Long id) {
		super.delete(id);
	}

	public void delete(Long[] ids) {
		String hql = "delete from " + entityClass.getName() + " where goodsId in :ids";
		Query session = getSession().createQuery(hql);
		session.setParameterList("ids", ids);
		session.executeUpdate();
	}
}
