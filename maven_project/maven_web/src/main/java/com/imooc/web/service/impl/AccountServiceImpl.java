/**
 * com.imooc.web.service.impl.AccountServiceImpl.java
 */
package com.imooc.web.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
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
	@Override
	public synchronized String changeMoney(double money,String name) {
		Map<String, Object> param=new HashMap<>();
		param.put("name", name);
		Account acc=queryExecutor.execOneEntity("com.imooc.web.dao.AccountDao.select", param, Account.class);
		System.out.println("当前线程名:"+Thread.currentThread().getName()+" 参数name: "+name +" money: "+money+"  对象: "+ReflectionToStringBuilder.toString(acc));
		if(acc.getMoney()-money<0){
			return "金额不足";
		}else{
			acc.setMoney(acc.getMoney()-money);
			this.accountDao.updateEntity(acc);
		}
		return "success";
	}	
}