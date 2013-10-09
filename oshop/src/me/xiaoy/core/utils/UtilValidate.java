package me.xiaoy.core.utils;

public class UtilValidate {

	public static boolean isEmpty(String value){
		if(null==value||"".equals(value)){
			return true;
		}
		return false;
	}
	public static boolean isNotEmpty(String value){
		return !isEmpty(value);
	}
}
