package me.xiaoy.mvc.manager.resource.dao;

import java.util.List;

import me.xiaoy.mvc.manager.resource.entity.Resource;

public interface ResourceDao {

	void insert(Resource resource);
	void deleteById(String id);
	void deleteByIds(String[]ids);
	void deleteByParentid(String parentid);
	List<Resource> getListByParentid(String parentid);
	List<Resource> getListByParentidRestype(String parentid,String restype);
	
}
