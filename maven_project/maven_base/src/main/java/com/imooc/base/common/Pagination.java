package com.imooc.base.common;



import java.util.ArrayList;
import java.util.List;

/**
 * 通用分页排序类
 */
public class Pagination<T> {
	private static final int DEFAULT_PAGE = 1;
	public static int DEFAULT_PAGE_SIZE = 20;
	private List<T> items;
	private int recordCount;
	private int pageSize = DEFAULT_PAGE_SIZE;
	private int currentPage = DEFAULT_PAGE;
	private String exceTime;
	private String exceSql;
	public String getExceTime() {
		return exceTime;
	}
	public void setExceTime(String exceTime) {
		this.exceTime = exceTime;
	}
	public String getExceSql() {
		return exceSql;
	}
	public void setExceSql(String exceSql) {
		this.exceSql = exceSql;
	}
	public Pagination(){
	}
	public Pagination(int pageSize, int page) {
		if (pageSize < 1) {
			throw new IllegalArgumentException("Count should be greater than zero!");
		} else if (currentPage < 1) {
			page = 1;
		} else {
			this.pageSize = pageSize;
			this.currentPage = page;
		}
	}
	
	public String getCount(){
		return this.getRecordCount()+"";
	}
	
	public void setPageSize(int countOnEachPage) {
		this.pageSize = countOnEachPage;
	}

	public List<T> getItems() {
		if(null==this.items)
			this.items=new ArrayList<T>();
		return items;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public void setRecordCount(int totalCount) {
		this.recordCount = totalCount;
	}
	

	public int getPageCount() {
		return (recordCount == 0) ? 1 : ((recordCount % pageSize == 0) ? (recordCount / pageSize)
				: (recordCount / pageSize) + 1);
	}

	public int getPreviousPage() {
		if(currentPage > 1) return currentPage - 1;
		else return DEFAULT_PAGE;
	}
	
	public int getNextPage() {
		if(currentPage < getPageCount()) return currentPage + 1;
		else return getPageCount();
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}