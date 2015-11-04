package com.imooc.transaction.base.dao;

import java.util.List;

import com.imooc.transaction.base.model.CoreEntity;

public interface BaseDao {
	/**
	 * 持久化新实体
	 * 
	 * @param entity
	 */
	<T> int addEntity(T entity);

	/**
	 * 持久化新实体
	 * 
	 * @param entity
	 */
	<T> int addBatch(List<T> entities);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	<T> int updateEntity(T entity);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	<T> int updateBatch(List<T> entities);

	/**
	 * 删除实体
	 * 
	 * @param entity
	 */
	<T> int deleteEntity(T entity);

	/**
	 * 删除实体
	 * 
	 * @param entity
	 */
	<T> int deleteBatch(List<String> ids);

	/**
	 * 按id删除实体
	 * 
	 * @param id
	 */
	int deleteById(String id);

	/**
	 * 根据id获取实体
	 * 
	 * @param id
	 * @return
	 */
	<T extends CoreEntity> T getEntityById(String id);

}
