package me.xiaoy.mvc.manager.security.controller;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import me.xiaoy.core.base.BaseController;
import me.xiaoy.core.common.page.PageList;
import me.xiaoy.mvc.manager.security.entity.Role;
import me.xiaoy.mvc.manager.security.service.RoleService;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin/security/role")
public class RoleController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Resource
	private RoleService roleService;
	
	@RequestMapping(value="list")
	public ModelAndView list(@ModelAttribute("role")Role role, PageList<Role> page) {
		logger.debug("查询角色开始...");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("security/role/list");
		
		page.addDescending(PKNAME);
		PageList<Role> list = roleService.query(page, currentUser, role);
		mv.addObject("page", list);
		
		
		logger.debug("查询角色结束， 数量： " + list.getAllSize());
		
		return mv;
	}
	
	@RequestMapping(value = "add", method=RequestMethod.GET)
	public ModelAndView goAdd(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("security/role/add");
		//TODO: 将权限列表传入前台
		return mv;
	}

	@RequestMapping(value = "add", method=RequestMethod.POST)
	public String doAdd(@ModelAttribute("role")Role role){
		logger.info("当前用户："+currentUser.getLoginName()+", 新增角色：" + role.getRoleName());
		roleService.save(role);
		return "redirect:list";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable Long id) {
		logger.debug("进入delete方法， 参数： " + id);
		roleService.delete(currentUser, ip, id);
		return "redirect:../list";
	}
	
	@RequestMapping(value = "edit/{id}", method=RequestMethod.GET)
	public ModelAndView goEdit(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("security/role/edit");
		
		Role role = roleService.get(id);
		mv.addObject("role", role);
		
		return mv;
	}
	
	@RequestMapping(value = "edit", method=RequestMethod.POST)
	public String doEdit(@ModelAttribute("role")Role newRole){
		Long id = newRole.getId();
		if(id==null || id<0) {
			saveRequestMsg("客户端/服务端交互出错！");
			logger.error("传入参数id为空！");
		}
		Role role = roleService.get(id);
		try {
			PropertyUtils.copyProperties(role, newRole);
		} catch (IllegalAccessException e) {
			logger.error("对象复制出错， 程序试图调用私有方法", e);
		} catch (InvocationTargetException e) {
			logger.error("对象赋值出错， 执行反射失败", e);
		} catch (NoSuchMethodException e) {
			logger.error("对象赋值出错， 未找到方法", e);
		}
		
		roleService.update(role);
		return "redirect:list";
	}
	
	@RequestMapping(value="permission/{id}")
	public ModelAndView permission(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("security/role/permission");
		mv.addObject("id", id);
		return mv;
	}
	
}
