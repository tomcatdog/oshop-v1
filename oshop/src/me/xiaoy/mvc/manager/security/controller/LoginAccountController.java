package me.xiaoy.mvc.manager.security.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import me.xiaoy.core.base.BaseController;
import me.xiaoy.core.common.page.PageList;
import me.xiaoy.core.constants.SessionKey;
import me.xiaoy.core.utils.DateTimeUtil;
import me.xiaoy.core.utils.StringUtil;
import me.xiaoy.mvc.manager.security.entity.LoginAccount;
import me.xiaoy.mvc.manager.security.entity.LoginAccountGradeEnum;
import me.xiaoy.mvc.manager.security.entity.LoginAccountStateEnum;
import me.xiaoy.mvc.manager.security.service.LoginAccountService;
import me.xiaoy.mvc.manager.system.log.service.LogService;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * URL:http://localhost:8080/oo/admin/security/login
 * /admin/security为类注解@RequestMapping对应的值
 * /login为方法注解@RequestMapping
 * 
 * 如果方法的注解@RequestMapping增加了method属性， 则不接受属性之外的提交
 * 如login方法指定了method为get， 则只接受前台通过get提交的url
 * index方法指定了method为post， 则只接受前台通过post提交的表单数据， 如果表单的method改为index方法不会执行 
 * 
 * 方法返回return "redirect:login"为重定向， 相当于response.sendRedirect()
 * 方法返回return "forward:login"为服务端转发， 相当于request.getRequestDispatcher().forward(request,response)
 */
@Controller
@RequestMapping(value = "/admin/security")
public class LoginAccountController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(LoginAccountController.class);

	@Resource
	private LogService logService;
	
	@Resource
	private LoginAccountService loginAccountService;
	
	/**
	 * 转到用户登陆页
	 * 
	 * @param merchant
	 * @return
	 */
	@RequestMapping(value = "login", method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("action", "login");
		modelView.setViewName("login");
		return modelView;
	}

	/**
	 * 用户登陆， 记录登陆用户名、密码、ip， 反复登陆失败视为危险行为， 进行阻止
	 * 
	 * @param request
	 * @param merchant
	 * @return
	 */
	@RequestMapping(value = "login", method=RequestMethod.POST)
	public String index(LoginAccount account) {
		logger.debug("loginname: {" + account.getLoginName() + "}, password: {"
				+ account.getPassword() + "}");
		String loginName = account.getLoginName();
		if(StringUtil.isEmpty(loginName)) {
			logService.save(account, ip, "非法登陆");
			return "redirect:login";
		}
		String password = account.getPassword();
		account = loginAccountService.getLoginAccountByLoginName(loginName);
		
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
		token.setRememberMe(false);
		//用户名、密码验证失败后步骤： 
		//1.取出登陆失败次数
		//2.将登陆失败次数加1 
		//3.判断是否达到最大允许失败次数, 如果达到则产生验证码 
		//4.将登陆失败次数更新到数据库中
		//5.
		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			saveRequestMsg("登陆认证失败");
			logService.save(account, ip, "登陆认证失败");
			return "forward:login";
		}
		if (currentUser.isAuthenticated()) {
			session.setAttribute(SessionKey.LOGIN_ACCOUNT, account);
			request.setAttribute("welcome", DateTimeUtil.getWelcome());
			logService.save(account, ip, "用户登陆成功");
		} else {
			saveRequestMsg("登陆权限认证失败");
			logService.save(account, ip, "登陆权限认证失败");
			return "forward:login";
		}
		return "redirect:index";
	}
	
	@RequestMapping(value="index")
	public String index() {
		request.setAttribute("welcome", DateTimeUtil.getWelcome());
		return "index";
	}
	
	@RequestMapping(value = "logout")
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "redirect:login";
	}
	
	@RequestMapping(value="account/list")
	public ModelAndView list(@ModelAttribute("account")LoginAccount account, PageList<LoginAccount> page) {
		logger.debug("查询管理员开始...");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("security/account/list");
		
		page.addDescending(PKNAME);
		PageList<LoginAccount> list = loginAccountService.query(page, currentUser, account);
		mv.addObject("page", list);
		
		mv.addObject("stateMaps", getStates());
		
		logger.debug("查询管理员结束， 数量： " + list.getAllSize());
		
		return mv;
	}
	
	@RequestMapping(value = "account/delete/{id}")
	public String delete(@PathVariable Long id) {
		logger.debug("进入delete方法， 参数： " + id);
		loginAccountService.delete(currentUser, ip, id);
		return "redirect:../list";
	}
	
	@RequestMapping(value = "account/delete")
	public String delete(Long[] ids) {
		logger.debug("进入delete方法， 参数： " +Arrays.toString(ids));
		loginAccountService.delete(currentUser, ip, ids);
		return "redirect:list";
	}
	
	@RequestMapping(value = "account/oper")
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
	
	@RequestMapping(value = "account/add", method=RequestMethod.GET)
	public ModelAndView goAdd(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("security/account/add");
		mv.addObject("action", "add");
		mv.addObject("stateMaps", getStates());
		mv.addObject("gradeMaps", getGrades());
		return mv;
	}
	
	@RequestMapping(value = "account/add", method=RequestMethod.POST)
	public String doAdd(@ModelAttribute("account")LoginAccount account){
		account.setPassword("88888888");
		account.setCreateTime(new Date());
		account.setLoginFailureCount(0);
		//TODO: setMerchant
		loginAccountService.save(account);
		return "redirect:list";
	}
	
	@RequestMapping(value = "account/edit/{id}", method=RequestMethod.GET)
	public ModelAndView goEdit(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("security/account/edit");
		mv.addObject("stateMaps", getStates());
		mv.addObject("gradeMaps", getGrades());
		
		LoginAccount account = loginAccountService.get(id);
		mv.addObject("account", account);
		
		return mv;
	}
	
	@RequestMapping(value = "account/edit", method=RequestMethod.POST)
	public String doEdit(@ModelAttribute("account")LoginAccount newAccount){
		Long id = newAccount.getId();
		if(id==null || id<0) {
			saveRequestMsg("客户端/服务端交互出错！");
			logger.error("传入参数id为空！");
		}
		LoginAccount account = loginAccountService.get(id);
		try {
			PropertyUtils.copyProperties(account, newAccount);
		} catch (IllegalAccessException e) {
			logger.error("对象复制出错， 程序试图调用私有方法", e);
		} catch (InvocationTargetException e) {
			logger.error("对象赋值出错， 执行反射失败", e);
		} catch (NoSuchMethodException e) {
			logger.error("对象赋值出错， 未找到方法", e);
		}
		
		loginAccountService.update(account);
		return "redirect:list";
	}
	
	@RequestMapping(value = "account/reset/{id}")
	public String reset(@PathVariable Long id) {
		logger.debug("开始进行密码重置， 参数： " + id);
		loginAccountService.reset(id);
		return "redirect:../list";
	}
	
	@RequestMapping(value = "account/reset")
	public String reset(Long[] ids) {
		logger.debug("开始进行密码重置， 参数： " +Arrays.toString(ids));
		loginAccountService.reset(ids);
		return "redirect:list";
	}
	
	private Map<String, Object> getStates() {
		Map<String, Object> m = new HashMap<String, Object>();
		LoginAccountStateEnum[] stateEnums = LoginAccountStateEnum.valueArray;
		for(LoginAccountStateEnum e : stateEnums) {
			m.put(e.getValue()+"", e.getLabel());
		}
		return m;
	}
	
	private Map<String, Object> getGrades() {
		Map<String, Object> m = new HashMap<String, Object>();
		LoginAccountGradeEnum[] gradeEnums = LoginAccountGradeEnum.valueArray;
		for(LoginAccountGradeEnum e : gradeEnums) {
			m.put(e.getValue()+"", e.getLabel());
		}
		return m;
	}
}