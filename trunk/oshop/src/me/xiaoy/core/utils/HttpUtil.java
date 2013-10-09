package me.xiaoy.core.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpUtil {

	public static Object getSessionAttribute(HttpServletRequest request,
			String name) {
		Object result = null;
		if (request != null) {
			HttpSession session = request.getSession(false);
			result = (session != null ? session.getAttribute(name) : null);
		}
		return result;
	}

}
