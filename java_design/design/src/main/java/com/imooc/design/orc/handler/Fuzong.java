package com.imooc.design.orc.handler;

public class Fuzong extends PriceHandler{

	@Override
	public void caluPriceHandler(float disaccount) {
		if(disaccount<=0.3){
			System.out.println("销售副总同意该折扣:"+disaccount);
		}else{//传递到CEO处理
			successor.caluPriceHandler(disaccount);
		}
	}

}
