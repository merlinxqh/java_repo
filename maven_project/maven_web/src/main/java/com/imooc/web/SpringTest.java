package com.imooc.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.imooc.web.model.Account;
import com.imooc.web.service.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringTest {
   
	@Autowired
	private AccountService accountService;
	
	@Test
	public void demo1(){
		Account account=new Account();
		account.setUUID();
		account.setName("gggg");
		
		account.setMoney(1100d);
		this.accountService.addEntity(account);
	}
}
