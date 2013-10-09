package me.xiaoy.mvc.manager.system.parm.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import me.xiaoy.core.base.BaseController;
import me.xiaoy.core.common.page.PageList;
import me.xiaoy.mvc.manager.system.parm.entity.SysParam;
import me.xiaoy.mvc.manager.system.parm.service.SysParamService;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin/system/param")
public class ParameterController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ParameterController.class); 
	
	@Autowired
	private SysParamService sysParamService;
	
	/**
	 * 查询参数列表
	 * 
	 * @param merchant
	 * @return
	 */
	@RequestMapping(value = "list")
	public ModelAndView list(@ModelAttribute("param")SysParam param, PageList<SysParam> page){
		logger.debug("查询参数开始...");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/param/list");
		//查询列表
		PageList<SysParam> list = sysParamService.query(page, currentUser, param);
		mv.addObject("page", list);
		logger.debug("查询参数结束， 数量： " + list.getAllSize());
		//获取参数组
		List<String> group = sysParamService.getGroup();
		if(group!=null && group.size()>0) {
			mv.addObject("group", group); 
		}
		return mv;
	}
	
	@RequestMapping(value = "add", method=RequestMethod.GET)
	public ModelAndView goAdd(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/param/add");
		
		return mv;
	}

	@RequestMapping(value = "add", method=RequestMethod.POST)
	public String doAdd(@ModelAttribute("param")SysParam param){
		logger.info("当前用户："+currentUser.getLoginName()+", 新增参数：" + param.getParamName());
		sysParamService.save(param);
		return "redirect:list";
	}
	
	@RequestMapping(value = "edit/{id}", method=RequestMethod.GET)
	public ModelAndView goEdit(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/param/edit");
		
		SysParam param = sysParamService.get(id);
		mv.addObject("p", param);
		mv.addObject("param", "param");
		
		return mv;
	}
	
	@RequestMapping(value = "edit", method=RequestMethod.POST)
	public String doEdit(@ModelAttribute("param")SysParam newParam){
		Long id = newParam.getId();
		if(id==null || id<0) {
			saveRequestMsg("客户端/服务端交互出错！");
			logger.error("传入参数id为空！");
		}
		SysParam param = sysParamService.get(id);
		try {
			PropertyUtils.copyProperties(param, newParam);
		} catch (IllegalAccessException e) {
			logger.error("对象复制出错， 程序试图调用私有方法", e);
		} catch (InvocationTargetException e) {
			logger.error("对象赋值出错， 执行反射失败", e);
		} catch (NoSuchMethodException e) {
			logger.error("对象赋值出错， 未找到方法", e);
		}
		
		sysParamService.update(param);
		return "redirect:list";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable Long id) {
		logger.debug("进入delete方法， 参数： " + id);
		sysParamService.delete(currentUser, ip, id);
		return "redirect:../list";
	}
	
	@RequestMapping(value = "delete")
	public String delete(Long[] ids) {
		logger.debug("进入delete方法， 参数： " +Arrays.toString(ids));
		sysParamService.delete(currentUser, ip, ids);
		return "redirect:list";
	}
	
}
