package me.xiaoy.mvc.manager.security.entity;

import java.util.Map;

import me.xiaoy.core.base.BaseEnum;

public class LoginAccountGradeEnum extends BaseEnum {

	private static final long serialVersionUID = 1697650924722685005L;
	
	protected LoginAccountGradeEnum(String name, int value, String label) {
		super(name, value, label);
	}
	
	/**管理级别-1:店铺管理员**/
    public static final LoginAccountGradeEnum GRADE_1 = new LoginAccountGradeEnum("1", 1, "店铺管理员");
    /**管理级别-2:信息发布员**/
    public static final LoginAccountGradeEnum GRADE_2 = new LoginAccountGradeEnum("2", 2, "信息发布员");
    /**管理级别-3:结算管理员**/
    public static final LoginAccountGradeEnum GRADE_3 = new LoginAccountGradeEnum("3", 3, "结算管理员");
	
	public static final LoginAccountGradeEnum[] valueArray = new LoginAccountGradeEnum[] {GRADE_1, GRADE_2, GRADE_3};
	
	public static LoginAccountGradeEnum valueOf(String name) {
        return values.get(name);
    }

	private static Map<String, LoginAccountGradeEnum> values;

    static {
        values = toMap(valueArray);
    }
    
    public static LoginAccountGradeEnum[] values() {
        return valueArray;
    }
    
}