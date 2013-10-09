package me.xiaoy.core.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;

public class BaseEnum extends ValuedEnum {

	private static final long serialVersionUID = 1L;
	
	protected String label;

	protected BaseEnum(String name, int value) {
		super(name, value);
	}
	
	protected BaseEnum(String name, int value, String label) {
        super(name, value);
        this.label = label;
    }
	
	public String getLabel() {
        return this.label;
    }

    public String getLabelLocal() {
        //TODO: 返回国际化标签
    	return "";
    }
    
    protected static <T extends BaseEnum> Map<String, T> toMap(T[] enums) {
        Map<String, T> valueMap = new HashMap<String, T>();
        for (T ele : enums) {
            valueMap.put(ele.getName(), ele);
        }
        return valueMap;
    }
    
}
