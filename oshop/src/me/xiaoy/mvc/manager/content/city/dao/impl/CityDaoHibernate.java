package me.xiaoy.mvc.manager.content.city.dao.impl;

import java.util.ArrayList;
import java.util.List;

import me.xiaoy.core.base.BaseDao;
import me.xiaoy.core.utils.StringUtil;
import me.xiaoy.mvc.manager.content.city.dao.CityDao;
import me.xiaoy.mvc.manager.content.city.entity.City;

public class CityDaoHibernate extends BaseDao<City> implements CityDao {

	public List<City> list(City city) {
		StringBuffer hql = new StringBuffer("FROM " + entityClass.getClass() + " where 1=1");
		List<Object> parms = new ArrayList<Object>();
		if(city != null) {
			if(StringUtil.isNotEmpty(city.getName())) {
				hql.append(" and name like ? ");
				parms.add("'%"+city.getName()+"%'");
			}
		}
		return super.query(hql.toString(), parms.toArray());
	}


}
