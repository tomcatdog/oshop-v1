package me.xiaoy.core.constants;

public class SecurityConstant {
	
	//用户状态 0禁用  1正常 2锁定 3密码过期 4账号废弃
	/**禁用**/
	public static int LOGIN_ACCOUNT_DISABLED = 0;
	/**正常**/
	public static int LOGIN_ACCOUNT_NORMAL = 1;
	/**锁定**/
	public static int LOGIN_ACCOUNT_LOCKED = 2;
	/**过期**/
	public static int LOGIN_ACCOUNT_EXPIRED = 3;
	/**废弃**/
	public static int LOGIN_ACCOUNT_DISCARD = 4;
	
	
	/**登陆失败次数限制， 超出则一定时间内不允许再次登陆**/
	public static final int MAX_LOGIN_COUNT = 5;
	public static final Long MAX_LOGIN_Time = 1800000L;
	public static final String MAX_LOGIN_TIME_NAME = "半小时";

}
