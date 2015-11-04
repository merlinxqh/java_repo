package com.imooc.base.query;


/**
 * 查询元素
 */
public class Selector {
	/**
	 * 列
	 */
	private String column;
	/**
	 * 字段
	 */
	private String field;
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	@Override
	public String toString() {
		return column + " : "+field;
	}
}
