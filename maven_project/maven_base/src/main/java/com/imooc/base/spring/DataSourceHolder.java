package com.imooc.base.spring;


import java.util.HashMap;
import java.util.Map;

import com.imooc.base.common.StringUtils;

/**
 * 
 * 数据源存放类
 */
public class DataSourceHolder {
	private static final ThreadLocal<Map<String, Object>> currentDataSource = new ThreadLocal<Map<String, Object>>();
	private static final String DATA_SOURCE = "DATA_SOURCE";
	public static void setDataSource(String dataSource) {
		if(!StringUtils.isEmpty(dataSource)){
			Map<String, Object> holder = currentDataSource.get();
			if(holder == null) {
				holder = new HashMap<String, Object>();
				currentDataSource.set(holder);
			}
			holder.put(DATA_SOURCE, dataSource);
		}
	}

	public static String getDataSource() {
		Map<String, Object> holder = currentDataSource.get();
		if(holder == null) return null;
		return (String) holder.get(DATA_SOURCE);
	}

	public static void clear() {
		currentDataSource.remove();
	}
}
