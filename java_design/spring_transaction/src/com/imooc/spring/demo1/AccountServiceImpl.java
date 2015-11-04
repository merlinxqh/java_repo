package com.imooc.spring.demo1;

public class AccountServiceImpl implements AccountService{
	
	private AccountDao accountDao;
	
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public void transfer(String out, String in, Double money) {
		this.accountDao.outMoney(out, money);
		this.accountDao.inMoney(in, money);
	}

}
