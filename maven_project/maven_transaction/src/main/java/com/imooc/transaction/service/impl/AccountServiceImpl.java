/**
 * com.imooc.transaction.service.impl.AccountServiceImpl.java
 */
package com.imooc.transaction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.transaction.base.dao.BaseDao;
import com.imooc.transaction.base.service.impl.BaseServiceImpl;
import com.imooc.transaction.dao.AccountDao;
import com.imooc.transaction.model.Account;
import com.imooc.transaction.service.AccountService;

/**
 * 
 * @author Administrator
 *
 */
//@Component(value="accountService")
@Service
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {
	@Autowired
	private AccountDao accountDao;
	@Override
	protected BaseDao getDao() {
		return accountDao;
	}	
}