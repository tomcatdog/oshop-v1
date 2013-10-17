package me.xiaoy.mvc.manager.system.log.controller;

import java.util.Arrays;

import me.xiaoy.core.base.BaseController;
import me.xiaoy.core.common.page.PageList;
import me.xiaoy.core.utils.StringUtil;
import me.xiaoy.mvc.manager.system.log.entity.SysLog;
import me.xiaoy.mvc.manager.system.log.service.SysLogService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin/system/log")
public class SysLogController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(SysLogController.class); 
	
	@Autowired
	private SysLogService logService;
	
	/**
	 * 查询日志列表
	 * 
	 * @param merchant
	 * @return
	 */
	@RequestMapping(value = "list")
	public ModelAndView list(@ModelAttribute("log")SysLog log, PageList<SysLog> page){
		logger.debug("查询日志开始...");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/log/list");
		PageList<SysLog> list = logService.query(page, log);
		mv.addObject("page", list);
		logger.debug("查询日志结束， 数量： " + list.getAllSize());
		return mv;
	}
	
	@RequestMapping(value = "delete")
	public String delete(Long[] ids) {
		logger.debug("进入delete方法， 参数： " +Arrays.toString(ids));
		logService.delete(ids);
		return "redirect:list";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable Long id) {
		logger.debug("进入delete方法， 参数： " + id);
		logService.delete(id);
		return "redirect:../list";
	}
	
	@RequestMapping(value = "oper")
	public String oper(Long[] ids, String action){
		String url = "forward:"+action;
		if(StringUtil.isEmpty(action)) {
			logger.warn("未选择要进行的操作");
			url = "redirect:list";
		} else {
			logger.debug("准备进行的操作: " + action);
		}
		return url;
	}

}
