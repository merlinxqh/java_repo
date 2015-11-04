package com.imooc.design.template;

public class CoffeTemplate extends TemplateBase{

	@Override
	protected void brew() {
		System.out.println("用沸水冲泡咖啡");
	}

	@Override
	protected void addCondiments() {
		System.out.println("加入牛奶和糖");
	}

}
