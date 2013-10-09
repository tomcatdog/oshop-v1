package me.xiaoy.core.base;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.xiaoy.core.binder.DateTimeEditor;
import me.xiaoy.core.constants.SessionKey;
import me.xiaoy.core.utils.JsonUtil;
import me.xiaoy.core.utils.SiteUtil;
import me.xiaoy.core.utils.StringUtil;
import me.xiaoy.mvc.manager.security.entity.LoginAccount;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class); 
	
	protected static final String MESSAGE_TITLE = "message";
	protected static final String MESSAGE_TITLE_Session = "sessionMessage";
	
	public static final String PKNAME = "id";
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	protected String base;
	protected String ip;
	
	protected LoginAccount currentUser;
	
	@ModelAttribute
	protected void initController(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.request = request;
		this.response = response;
		session = request.getSession();
		
		String path = request.getContextPath();
		base = request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + path;
		
		ip = SiteUtil.getClientIp(request);
		
		currentUser = getCurrentLoginAccount();
		if(currentUser == null) {
			logger.warn("当前用户为空， 将定位至登录页");
			//TODO 登陆用户为空的时候说明未登录或者session过期， 重定向到登陆页面, 如果是首次登陆则跳转到首页
			String queryString = request.getQueryString()==null?"":"?"+request.getQueryString();
			String currentUrl = base + "/admin/security/login" + queryString;
			System.out.println("TODO: session失效后跳转到登陆页面 (带参数， 登陆后直接跳转至当前页面) - " + currentUrl);
		}

		request.setAttribute("parameters", request.getParameterMap());
		request.setAttribute("url", request.getRequestURL().toString());
	}
	
	@InitBinder  
    public void initBinder(WebDataBinder binder) {  
        binder.setFieldDefaultPrefix("page.");
        
        binder.registerCustomEditor(Date.class, new DateTimeEditor());
    }

	public LoginAccount getCurrentLoginAccount() {
        return (LoginAccount) session.getAttribute(SessionKey.LOGIN_ACCOUNT);
    }
	
	public void saveRequestMsg(String msg) {
		if(StringUtil.isNotEmpty(msg)) {
			request.setAttribute(MESSAGE_TITLE, msg);
		}
	}
	public void saveSessionMsg(String msg) {
		if(StringUtil.isNotEmpty(msg)) {
			session.setAttribute(MESSAGE_TITLE_Session, msg);
		}
	}
	
	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}
	
	public void outJson(boolean success,Object result){
		JSONObject jsonObject=objectConvertToJson(success, result);
		outJson(jsonObject);
	}
	private JSONObject  objectConvertToJson(boolean success,Object result){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("success",success);
		map.put("result",result);
		return JSONObject.fromObject(map);
	}
	/**
	 * 将json数据传给前台
	 * @param jsonObject
	 * @param response
	 */
	private void outJson(JSONObject jsonObject){
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void MakeJson(boolean flag, Object result, boolean jsonType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", Boolean.valueOf(flag));
		map.put("result", result);
		String json = toJson(map);
		writeJson(json, jsonType);
	}

	private void writeJson(String json, boolean jsonType) {
		if (jsonType)
			response.setContentType("text/json; charset=UTF-8");
		else
			response.setContentType("text/html; charset=UTF-8");
		setResponseBrowserProxyNoCache(response);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void setResponseBrowserProxyNoCache(
			HttpServletResponse response) {
		long nowMillis = System.currentTimeMillis();
		response.setDateHeader("Expires", nowMillis);
		response.setDateHeader("Last-Modified", nowMillis);
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0, false");
		response.setHeader("Pragma", "no-cache");
	}

	private String toJson(Object obj) {
		assert (obj != null);

		return JsonUtil.toJson(obj);
	}

}
