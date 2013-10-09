package me.xiaoy.core.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieUtil {

	private static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);

	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param name
	 *            Cookie的名称，不能为null
	 * @param value
	 *            Cookie的值，默认值空字符串
	 * 
	 *            存活时间为默认30天， 默认路径为/
	 */
	public static void addCookie(HttpServletResponse response, String name,
			String value) {
		addCookie(response, name, value, 60 * 60 * 24 * 30);
	}

	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param name
	 *            Cookie的名称，不能为null
	 * @param value
	 *            Cookie的值，默认值空字符串
	 * @param maxAge
	 *            默认路径为/
	 */
	public static void addCookie(HttpServletResponse response, String name,
			String value, Integer maxAge) {
		addCookie(response, name, value, maxAge, null);
	}

	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param name
	 *            Cookie的名称，不能为null
	 * @param value
	 *            Cookie的值，默认值空字符串
	 * @param maxAge
	 * @param path
	 *            默认值'/'
	 */
	public static void addCookie(HttpServletResponse response, String name,
			String value, Integer maxAge, String path) {
		if (value == null) {
			value = "";
		}
		if (path == null) {
			path = "/";
		}

		Cookie cookie = new Cookie(name, value);
		cookie.setPath(path);
		if (maxAge != null) {
			cookie.setMaxAge(maxAge);
		}

		response.addCookie(cookie);
	}

	/**
	 * 通过cookie名获取cookie
	 * 
	 * @param request
	 * @param cookieName
	 * @return 指定的cookie
	 */
	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			logger.warn("获取名称为 " + cookieName + "的cookie失败！");
			return null;
		}
		for (Cookie c : cookies) {
			if (c.getName().equals(cookieName)) {
				return c;
			}
		}

		return null;
	}

	/**
	 * 通过cookie名获取对应的cookie值
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request,
			String cookieName) {
		String result = "";
		Cookie cookie = getCookie(request, cookieName);
		if (cookie != null) {
			result = cookie.getValue();
		}
		return result;
	}

	/**
	 * 通过cookie名删除cookie
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static boolean deleteCookie(HttpServletRequest request,
			HttpServletResponse response, String cookieName) {
		boolean result = false;
		if (StringUtil.isNotEmpty(cookieName)) {
			Cookie c = getCookie(request, cookieName);
			if (c == null) {
				logger.warn("获取名称为 " + cookieName + "的cookie失败！");
			} else {
				c.setMaxAge(0);
				c.setPath("/");
				response.addCookie(c);
				result = true;
			}
		}
		return result;
	}

}
