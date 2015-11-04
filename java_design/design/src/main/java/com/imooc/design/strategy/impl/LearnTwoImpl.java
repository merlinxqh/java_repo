package com.imooc.design.strategy.impl;

import com.imooc.design.strategy.LearnInterface;

public class LearnTwoImpl implements LearnInterface{

	@Override
	public void learn() {
		System.out.println("我喜欢学习C++");
	}

}
