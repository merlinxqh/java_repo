package com.imooc.design.orc.handler;

public class Ceo extends PriceHandler{

	@Override
	public void caluPriceHandler(float disaccount) {
		if(disaccount<=0.5){
			System.out.println("CEO同意该折扣:"+disaccount);
		}else{
			System.out.println("CEO拒绝了该折扣:"+disaccount);
		}
		
	}

}
