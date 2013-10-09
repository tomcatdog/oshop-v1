package me.xiaoy.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// Object merchant = HttpUtil.getSessionAttribute(request, "merchant");
		logger.debug("--判断是否登陆， 未登录或session过期就转向登录页");
		// response.sendRedirect(URIConstant.MERCHANT_MANAGER +
		// URIConstant.MERCHANT_LOGIN);
		return super.preHandle(request, response, handler);
	}

}
