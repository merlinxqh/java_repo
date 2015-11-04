package com.imooc.design.strategy;

import com.imooc.design.strategy.impl.LearnTwoImpl;

public class Women extends AbstractPerson{
	
	public Women(){
		super();
		super.setLearnInterface(new LearnTwoImpl());
	}

	@Override
	public void gender() {
		System.out.println("这是个女人");
	}

}
