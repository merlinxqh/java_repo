/**
 * com.imooc.web.service.impl.AccountServiceImpl.java
 */
package com.imooc.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.base.dao.BaseDao;
import com.imooc.base.service.impl.BaseServiceImpl;
import com.imooc.web.dao.AccountDao;
import com.imooc.web.model.Account;
import com.imooc.web.service.AccountService;

/**
 * 组织service
 * @author admin
 * 
 * @since 2012-9-7
 */
@Service
@Transactional
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {
	@Autowired
	private AccountDao accountDao;
	@Override
	protected BaseDao getDao() {
		return accountDao;
	}	
}