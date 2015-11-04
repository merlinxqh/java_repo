package com.imooc.base.query;


import java.util.List;

/**
 * 查询结果
 */
public class QueryResult<T> {
	private List<T> result;
	private int totalCount;
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
