package com.imooc.spring.demo1;

public interface AccountDao {
   
	/**
	 * @param out
	 * @param money
	 */
	public void outMoney(String out,Double money);
	
	/**
	 * @param in
	 * @param money
	 */
	public void inMoney(String in,Double money);
}
