package com.imooc.design.orc.handler;

/**
 * 责任链 设计模式 
 * 模拟 客户 询问 销售房子折扣 场景
 * @author Administrator
 *
 */
public abstract class PriceHandler {
	protected PriceHandler successor;
	public void setSuccessor(PriceHandler successor) {
		this.successor = successor;
	}

	public abstract void caluPriceHandler(float disaccount);
	
	//创建获取实例的工厂方法
	public static PriceHandler createPriceHandler(){
		PriceHandler sale=new Sales();
		PriceHandler manager=new Manager();
		sale.setSuccessor(manager);//设置 销售人员上级 
		PriceHandler fuzong=new Fuzong();
		manager.setSuccessor(fuzong);
		PriceHandler ceo=new Ceo();
		fuzong.setSuccessor(ceo);
		return sale;
	}

}
