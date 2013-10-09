package me.xiaoy.mvc.manager.content.city.service.impl;

import java.util.List;

import javax.annotation.Resource;

import me.xiaoy.mvc.manager.content.city.dao.CityDao;
import me.xiaoy.mvc.manager.content.city.entity.City;
import me.xiaoy.mvc.manager.content.city.service.CityService;

public class CityServiceImpl implements CityService {

	@Resource
	private CityDao cityDao;

	public List<City> list(City city) {
		return cityDao.list(city);
	}
	
	
	
}
