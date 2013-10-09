package me.xiaoy.mvc.manager.security.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import me.xiaoy.core.base.BaseController;
import me.xiaoy.mvc.manager.security.entity.Permission;
import me.xiaoy.mvc.manager.security.entity.PermissionForJson;
import me.xiaoy.mvc.manager.security.service.PermissionService;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/admin/security/permission")
public class PermissionController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);
	
	@Resource
	private PermissionService permissionService;
	
	@RequestMapping(value="list/{id}")
	@ResponseBody
	public List<PermissionForJson> list(@PathVariable Long id) {
		//查询所有权限
		List<Permission> permissionList = permissionService.query();
		//查询传入id对应角色已有权限
//		List<Permission> hasPermissionList = permissionService.query(id);
		List<PermissionForJson> result = new ArrayList<PermissionForJson>();
		for(Permission p : permissionList) {
			PermissionForJson pjson = new PermissionForJson();
			try {
				PropertyUtils.copyProperties(pjson, p);
			} catch (IllegalAccessException e) {
				logger.error("对象复制出错， 程序试图调用私有方法", e);
			} catch (InvocationTargetException e) {
				logger.error("对象赋值出错， 执行反射失败", e);
			} catch (NoSuchMethodException e) {
				logger.error("对象赋值出错， 未找到方法", e);
			}
			if(p.getParentPermission() != null) {
				pjson.setParentId(p.getParentPermission().getId());
			}
			result.add(pjson);
		}
		return result;
	}
	
}
