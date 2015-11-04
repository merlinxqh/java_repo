package com.imooc.design.orc.handler;

public class Manager extends PriceHandler{
	@Override
	public void caluPriceHandler(float disaccount) {
		if(disaccount<=0.1){
			System.out.println("销售经历同意该折扣:"+disaccount);
		}else{//级别不够传递到上一级领导处理
			successor.caluPriceHandler(disaccount);
		}
	}
}
