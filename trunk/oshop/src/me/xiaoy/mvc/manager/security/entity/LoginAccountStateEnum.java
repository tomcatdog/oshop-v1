package me.xiaoy.mvc.manager.security.entity;

import java.util.Map;

import me.xiaoy.core.base.BaseEnum;

public class LoginAccountStateEnum extends BaseEnum {

	private static final long serialVersionUID = 1697650924722685005L;
	
	protected LoginAccountStateEnum(String name, int value, String label) {
		super(name, value, label);
	}
	
	 /**管理员账号状态-0:禁用**/
    public static final LoginAccountStateEnum STATE_0 = new LoginAccountStateEnum("0", 0, "禁用");
    /**管理员账号状态-1:正常**/
    public static final LoginAccountStateEnum STATE_1 = new LoginAccountStateEnum("1", 1, "正常");
    /**管理员账号状态-2:锁定**/
    public static final LoginAccountStateEnum STATE_2 = new LoginAccountStateEnum("2", 2, "锁定");
    /**管理员账号状态-3:密码过期**/
    public static final LoginAccountStateEnum STATE_3 = new LoginAccountStateEnum("3", 3, "密码过期");
    /**管理员账号状态-4:账号废弃**/
    public static final LoginAccountStateEnum STATE_4 = new LoginAccountStateEnum("4", 4, "账号废弃");
	
	public static final LoginAccountStateEnum[] valueArray = new LoginAccountStateEnum[] {STATE_0, STATE_1, STATE_2, STATE_3, STATE_4};
	
	public static LoginAccountStateEnum valueOf(String name) {
        return values.get(name);
    }

	private static Map<String, LoginAccountStateEnum> values;

    static {
        values = toMap(valueArray);
    }
    
    public static LoginAccountStateEnum[] values() {
        return valueArray;
    }
    
   
}
