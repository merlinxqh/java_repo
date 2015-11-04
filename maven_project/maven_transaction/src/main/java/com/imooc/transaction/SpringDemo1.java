package com.imooc.transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.imooc.transaction.base.common.ApplicationContextAware;
import com.imooc.transaction.base.dao.QueryExecutor;
import com.imooc.transaction.model.Account;
import com.imooc.transaction.service.AccountService;

/**
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext.xml")
public class SpringDemo1 {
	
	@Resource(name="accountService")
	private AccountService accountService;
	
	@Resource(name="queryExecutor")
	private QueryExecutor queryExecutor;
   
	@Test
	public void demo1(){
		Account account=new Account();
		account.setUUID();
		account.setName("ccc");
		account.setMoney(1000d);
		this.accountService.addEntity(account);
	}
	
	
	@Test
	public void demo2(){
		Map<String, Object> param=new HashMap<>();
		param.put("name", "ccc");
//		Pagination<Account> pagination=new Pagination<Account>();
//		pagination=queryExecutor.execQuery("com.imooc.transaction.dao.AccountDao.select", pagination, param);
//		System.out.println(pagination.getItems().size());
		List<Account> reslist=queryExecutor.execQuery("com.imooc.transaction.dao.AccountDao.select", param, Account.class);
		for(Account ac:reslist){
			System.out.println(ReflectionToStringBuilder.toString(ac));
		}
	}
	
	@Test
	public void demo3(){
		SqlSessionFactoryBean sqlbean=ApplicationContextAware.getApplicationContext().getBean("sqlSessionFactory", SqlSessionFactoryBean.class);
		System.out.println(sqlbean);
	}
}
