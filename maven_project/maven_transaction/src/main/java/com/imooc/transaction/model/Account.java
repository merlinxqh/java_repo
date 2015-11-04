package com.imooc.transaction.model;

import com.imooc.transaction.base.model.CoreEntity;

/**
 * 转账实体
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Account extends CoreEntity{

	private String name;

	private Double money;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

}
