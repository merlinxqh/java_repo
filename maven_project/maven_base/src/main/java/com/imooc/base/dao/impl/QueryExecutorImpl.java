package com.imooc.base.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.imooc.base.common.ApplicationContextAware;
import com.imooc.base.common.Pagination;
import com.imooc.base.common.StringUtils;
import com.imooc.base.dao.QueryExecutor;
import com.imooc.base.mybatis.interceptor.ExecutorInterceptor;

@Component(value="queryExecutor")
public class QueryExecutorImpl extends SqlSessionDaoSupport implements QueryExecutor {
	
	@Autowired
	protected SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	
	
	@Override
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public static QueryExecutor getInstance(){
		return ApplicationContextAware.getApplicationContext().getBean("queryExecutor", QueryExecutor.class);
	}
	
	@Override
	public <T> Pagination<T> execQuery(String mapper,Pagination<T> pagination,
			Map<String, Object> param) {
		if(StringUtils.isEmpty(mapper)){
			throw new IllegalArgumentException("mapper could not to be empty");
		}
		long begin = System.currentTimeMillis();
		int count = executeCount(mapper, param);
		pagination.setRecordCount(count);
		List<T> list = getSqlSession().selectList(mapper, param, new RowBounds((pagination.getCurrentPage() - 1) * pagination.getPageSize(), pagination.getPageSize()));
		Long time = System.currentTimeMillis() - begin;
		BigDecimal second = new BigDecimal(time).divide(new BigDecimal("1000.000")).setScale(3);
		pagination.setExceTime(second.toString() + "秒");
		pagination.setExceSql(getSqlSession().getConfiguration().getMappedStatement(mapper).getBoundSql(param).getSql());
		pagination.setItems(list);
		return pagination;
	}

	@Override
	public <T> List<T> execQuery(String mapper, Map<String, Object> param,Class<T> type) {
		if(StringUtils.isEmpty(mapper)){
			throw new IllegalArgumentException("mapper could not to be empty");
		}
		return getSqlSession().selectList(mapper, param);
	}
	
	@Override
	public int execCount(String mapper, Map<String, Object> param){
		if(StringUtils.isEmpty(mapper)){
			throw new IllegalArgumentException("mapper could not to be empty");
		}
		return executeCount(mapper, param);
	}

	@Override
	public void executeInsert(String mapper, Object param){
		getSqlSession().insert(mapper, param);
	}

	@Override
	public void executeUpdate(String mapper, Object param){
		getSqlSession().update(mapper, param);
	}

	@Override
	public void executeDelete(String mapper, Object param){
		getSqlSession().delete(mapper, param);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T execOneEntity(String mapper, Map<String, Object> param,Class<T> type) {
		if(StringUtils.isEmpty(mapper)){
			throw new IllegalArgumentException("mapper could not to be empty");
		}
		return (T) getSqlSession().selectOne(mapper, param);
	}
	
	private int executeCount(String mapper,Map<String,Object> param){
		MappedStatement state = null;
		try{
			state = sqlSessionFactory.getConfiguration().getMappedStatement(mapper+"Count");
		}catch(IllegalArgumentException e){
			//如果没有找到相应的mapper，会抛异常。这里不作处理
		}
		if(state == null){
			return (Integer) getSqlSession().selectOne(mapper, new ExecutorInterceptor.CountParameter(param));
		}
		return (Integer) getSqlSession().selectOne(mapper + "Count", param);
	}
}
