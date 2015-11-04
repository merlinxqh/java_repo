package com.imooc.design.strategy;

import com.imooc.design.strategy.impl.LearnOneImpl;

public class Man extends AbstractPerson{
	
	public Man() {
		super();
		super.setLearnInterface(new LearnOneImpl());
	}

	@Override
	public void gender() {
		System.out.println("这是个男人");
	}

}
