package com.imooc.design.template;


/**
 * 抽象基类
 * 
 * @author Administrator
 *
 */
public abstract class TemplateBase {
   
	
	/**
	 * 模拟冲泡饮料
	 * 用final修饰 是为了不让子类 修改
	 */
	public final void perpareBeverageTemplate(){
		//第一步,将水煮沸
		boildWater();
		
		//第二步,泡制饮料
		brew();
		
		//第三部,将饮料导入杯中
		pourInCup();
		
		//第四步,加入不同调味剂
		addCondiments();
	}
	
	//将水煮沸
	private void boildWater() {
		System.out.println("1.将水煮沸");
		
	}
	//泡制饮料实现方式不同,所以用抽象方法 让子类自己去实现
	protected abstract void brew();
	
	private void pourInCup() {
		System.out.println("3.将饮料导入杯中");
	}
    
	//加入调味剂 类似第二步
	protected abstract void addCondiments();
	
}
