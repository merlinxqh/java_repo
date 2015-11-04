package com.imooc.transaction.base.dao;

import java.util.List;

public interface SqlExecutor {
	void executeSqls(List<String> sqls,boolean exception);
}
