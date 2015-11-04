package com.imooc.base.mybatis;


import org.apache.ibatis.logging.LogFactory;

/**
 * mybatis日志工具
 * 
 */

public class MyBatisLogHolder {
	/**
	 * 使用log4j
	 */
	public void useLog4JLogger(){
		LogFactory.useLog4JLogging();
	}
}
