package com.imooc.base.dao;

import java.util.List;

public interface SqlExecutor {
	void executeSqls(List<String> sqls,boolean exception);
}
