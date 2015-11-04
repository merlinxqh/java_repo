package com.imooc.base.service.impl;

import java.util.Date;

import com.imooc.base.model.DataEntity;

public abstract class DataEntityService<T extends DataEntity> extends BaseServiceImpl<T> {
	
	@Override
	public void addEntity(T entity) {
		if(entity!=null){
			if(entity.getCreateTime()==null){
				entity.setCreateTime(new Date());
			}
			if(entity.getLastUpdateTime()==null){
				entity.setLastUpdateTime(new Date());
			}
		}
		super.addEntity(entity);
	}
	
	@Override
	public void updateEntity(T entity) {
		if(entity!=null){
			entity.setLastUpdateTime(new Date());
		}
		super.updateEntity(entity);
	}
}
