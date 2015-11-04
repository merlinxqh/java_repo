package com.imooc.design.template;

/**
 * 泡茶 
 * @author Administrator
 *
 */
public class TeaTemplate extends TemplateBase{
	
	

	@Override
	protected void brew() {
		System.out.println("用八十度的水泡茶五分钟..");
	}

	@Override
	protected void addCondiments() {
		System.out.println("加入柠檬...");
	}

}
