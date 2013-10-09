package me.xiaoy.core.common;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.SortDefinition;

public class SingleSortDefinition extends MutableSortDefinition {

	private static final long serialVersionUID = 6662158123386453576L;
	
	public SingleSortDefinition() {	}
	
	public SingleSortDefinition(SortDefinition source) {
		super.setProperty(source.getProperty());
		super.setIgnoreCase(source.isIgnoreCase());
		super.setAscending(source.isAscending());
	}
	
	public SingleSortDefinition(String property, boolean ignoreCase, boolean ascending) {
		super.setProperty(property);
		super.setIgnoreCase(ignoreCase);
		super.setAscending(ascending);
	}
	
	public SingleSortDefinition(String property) {
		super.setProperty(property);
	}
	
	public SingleSortDefinition(boolean toggleAscendingOnSameProperty) {
		this.setToggleAscendingOnProperty(toggleAscendingOnSameProperty);
	}
	

}
