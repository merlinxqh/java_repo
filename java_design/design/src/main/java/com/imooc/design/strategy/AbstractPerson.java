package com.imooc.design.strategy;

/**
 * 抽象类  可以有 抽象方法(只有方法没有方法体)
 *        也可以由一般方法
 * @author Administrator
 *
 */
public abstract class AbstractPerson {
	
	private LearnInterface learnInterface;
   
	public abstract void gender();
	
   
	
	public void say(){
		System.out.println("人说话的共同特性");
	}

	public void learnKind(){
		learnInterface.learn();
	}


	public void setLearnInterface(LearnInterface learnInterface) {
		this.learnInterface = learnInterface;
	}
	
	
}
