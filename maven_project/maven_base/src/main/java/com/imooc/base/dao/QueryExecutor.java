package com.imooc.base.dao;


import java.util.List;
import java.util.Map;

import com.imooc.base.common.Pagination;

/**
 * 查询执行工具
 * @author FengMy
 * @since 2012-6-20下午03:45:24
 */
public interface QueryExecutor {

	/**
	 * 普通分页查询
	 * @param <T>
	 * @param pagination
	 * @param param
	 * @return
	 */
	<T> Pagination<T> execQuery(String mapper,Pagination<T> pagination,Map<String,Object> param);
	
	/**
	 * 查询所有数据(数据量大时，慎用)
	 * @param <T>
	 * @param mapper
	 * @param param
	 * @return
	 */
	<T> List<T> execQuery(String mapper,Map<String,Object> param,Class<T> type);
	
	/**
	 * 返回单个实体
	 * @param <T>
	 * @param mapper
	 * @param param
	 * @param type
	 * @return
	 * @author hlf add by 2013-1-21
	 */
	public <T> T execOneEntity(String mapper,Map<String,Object> param,Class<T> type);
	
	/**
	 * 查询数量
	 * @param mapper
	 * @param param
	 * @return
	 */
	public int execCount(String mapper, Map<String, Object> param);
	
	/**
	 * 执行Insert
	 * @param mapper
	 * @param param
	 */
	public void executeInsert(String mapper,Object param);
	
	/**
	 * 执行Insert
	 * @param mapper
	 * @param param
	 */
	public void executeUpdate(String mapper,Object param);
	
	/**
	 * 执行Insert
	 * @param mapper
	 * @param param
	 */
	public void executeDelete(String mapper,Object param);
}
