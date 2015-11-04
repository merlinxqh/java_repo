package com.imooc.design.orc.handler;

public class Director extends PriceHandler{
	@Override
	public void caluPriceHandler(float disaccount) {
		if(disaccount<=0.2){
			System.out.println("区总同意该折扣:"+disaccount);
		}else{//级别不够传递到上一级领导处理
			successor.caluPriceHandler(disaccount);
		}
	}
}
