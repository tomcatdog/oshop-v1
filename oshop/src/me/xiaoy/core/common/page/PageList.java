package me.xiaoy.core.common.page;

import java.util.ArrayList;
import java.util.List;

public class PageList<T> extends AbstractPage {	
	
	/** 每页默认List大小 */
	public static final int DEFAULT_LIST_SIZE = 15;
	
	/** 
	 * 页内容
	 */
	private List<T> list = new ArrayList<T>();
		
	/**
	 * 排序
	 */
	private List<OrderBy> orderByList;
				
	public PageList() {
		super( DEFAULT_LIST_SIZE );
	}
		
	public PageList(List<T> list,int allSize, int size, int number) {
		super(allSize, size, number);
		this.list = list;
	}

	public PageList(int allSize, int size, int number) {
		this(null,allSize, size, number);
	}

	public PageList(int size, int number) {
		this(0,size, number);
	}
	
	public PageList(int size ) {
		this(0,size, 1);
	}
	
	/**
	 * 增加排序
	 */
	protected void addOrder( String property , OrderType type ){
		if( property == null || "".equals(property))
			return;
		
		if( orderByList == null )
			orderByList = new ArrayList<OrderBy>();
		
		orderByList.add( new OrderBy(property,type) );
	}
	
	/**
	 * 添加升序
	 */
	public void addAscending( String property ){
		addOrder( property ,OrderType.ASCENDING );
	}
	
	/**
	 * 添加降序
	 */
	public void addDescending( String property ){
		addOrder( property ,OrderType.DESCENDING );
	}

	public int getDefaultSize() {
		return DEFAULT_LIST_SIZE;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	public List<OrderBy> getOrderByList() {
		return orderByList;
	}
	
	/** 
	 * 排序类型
	 */
	public enum OrderType{
		ASCENDING
		,DESCENDING
	}
	
	/**
	 * 排序域
	 */
	public class OrderBy{
		private String property;
		private OrderType type;
				
		public OrderBy(String property, OrderType type) {
			super();
			this.property = property;
			this.type = type;
		}

		public String getProperty() {
			return property;
		}
				
		public OrderType getType() {
			return type;
		}		
	}
		
}
