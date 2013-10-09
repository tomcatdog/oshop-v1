package me.xiaoy.mvc.manager.resource.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.xiaoy.mvc.manager.resource.dao.impl.ResourceDaoHibernate;
import me.xiaoy.mvc.manager.resource.entity.Resource;
import me.xiaoy.mvc.manager.resource.service.ResourceService;
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService{

	private ResourceDaoHibernate resourceDaoHibernate;
	@Override
	public void insert(Resource resource) {
		resourceDaoHibernate.insert(resource);
		
	}

	@Override
	public void deleteById(String id) {
		resourceDaoHibernate.deleteById(id);
		
	}

	@Override
	public void deleteByIds(String[] ids) {
		resourceDaoHibernate.deleteByIds(ids);
		
	}

	@Override
	public void deleteByParentid(String parentid) {
		resourceDaoHibernate.deleteByParentid(parentid);
		
	}

	@Override
	public List<Resource> getListByParentid(String parentid) {
		return resourceDaoHibernate.getListByParentid(parentid);
	}

	@Override
	public List<Resource> getListByParentidRestype(String parentid,
			String restype) {
		return resourceDaoHibernate.getListByParentidRestype(parentid, restype);
	}

	
}
