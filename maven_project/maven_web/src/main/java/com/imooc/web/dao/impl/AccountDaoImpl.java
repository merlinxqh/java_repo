/**
 * com.imooc.web.dao.impl.AccountDaoImpl.java
 */
package com.imooc.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.imooc.base.dao.impl.BaseDaoImpl;
import com.imooc.web.dao.AccountDao;

/**
 * @author admin
 * 
 * @since 2015-09-05
 */
@Repository
public class AccountDaoImpl extends BaseDaoImpl implements AccountDao {
	@Override
	protected String getNameSpace() {
		return "com.imooc.web.dao.AccountDao";
	}
}
