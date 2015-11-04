package com.imooc.transaction.base.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.imooc.transaction.base.dao.SqlExecutor;

@Component(value="sqlExecutor")
public class SqlExecutorImpl implements
		SqlExecutor {
	private static final Log LOG = LogFactory.getLog(SqlExecutorImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public void executeSqls(List<String> sqls,boolean exception) {
		for(String sql : sqls){
			try{
				jdbcTemplate.execute(sql);
				LOG.debug(sql);
			}catch(DataAccessException e){
				if(exception){
					throw e;
				}else{
					LOG.debug(sql);
				}
			}		
		}
	}
}
