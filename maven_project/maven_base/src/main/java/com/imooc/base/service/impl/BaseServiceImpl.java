package com.imooc.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.base.dao.BaseDao;
import com.imooc.base.dao.QueryExecutor;
import com.imooc.base.model.CoreEntity;
import com.imooc.base.service.BaseService;

public abstract class BaseServiceImpl<T extends CoreEntity> implements
		BaseService<T> {
	@Autowired
	protected QueryExecutor queryExecutor;

	@Override
	public void addEntity(T entity) {
		getDao().addEntity(entity);
	}

	@Override
	public void addBatch(List<T> entities) {
		getDao().addBatch(entities);
	}

	@Override
	public void updateEntity(T entity) {
		getDao().updateEntity(entity);
	}

	@Override
	public void updateBatch(List<T> entities) {
		getDao().updateBatch(entities);
	}

	@Override
	public void deleteEntity(T entity) {
		getDao().deleteEntity(entity);
	}

	@Override
	public void deleteById(String id) {
		getDao().deleteById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getEntityById(String id) {
		return (T) getDao().getEntityById(id);
	}

	protected abstract BaseDao getDao();

	@Override
	public void deleteBatch(List<String> ids) {
		getDao().deleteBatch(ids);
	}
}
