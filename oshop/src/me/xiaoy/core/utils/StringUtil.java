package me.xiaoy.core.utils;

import java.util.Iterator;
import java.util.Map;

public class StringUtil {

	/**
	 * 将map中的值转化为字符串， 暂时不支持嵌套MAP
	 * 
	 * @param map
	 * @return
	 */
	public static String map2String(Map<String, Object> map) {
		StringBuffer s = new StringBuffer();
		if (!map.isEmpty()) {
			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				if (key.length() > 0) {
					Object o = map.get(key);
					s.append(key);
					s.append(": ");
					s.append(o.toString());
					s.append(", ");
				}
			}
			s.deleteCharAt(s.lastIndexOf(","));
		}
		return s.toString().trim();
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return 为空则返回true， 否则返回false
	 */
	public static boolean isEmpty(String str) {
		boolean result = true;
		if (str != null) {
			if (str.length() > 0) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * 判断字符串是否不为空
	 * 
	 * @param str
	 * @return 不为空返回true， 为空返回false
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

}
