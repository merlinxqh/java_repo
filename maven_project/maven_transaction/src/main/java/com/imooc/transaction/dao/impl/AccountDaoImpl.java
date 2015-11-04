/**
 * com.imooc.transaction.dao.impl.AccountDaoImpl.java
 */
package com.imooc.transaction.dao.impl;

import org.springframework.stereotype.Repository;

import com.imooc.transaction.base.dao.impl.BaseDaoImpl;
import com.imooc.transaction.dao.AccountDao;

/**
 * @author FengMy
 * 
 * @since 2015-09-04
 */
//@Component(value="accountDao")
@Repository
public class AccountDaoImpl extends BaseDaoImpl implements AccountDao {
	@Override
	protected String getNameSpace() {
		return "com.imooc.transaction.dao.AccountDao";
	}
}
