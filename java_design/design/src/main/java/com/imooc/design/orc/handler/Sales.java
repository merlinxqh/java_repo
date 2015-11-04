package com.imooc.design.orc.handler;

/*
 * 底层销售人员
 */
public class Sales extends PriceHandler{

	@Override
	public void caluPriceHandler(float disaccount) {
		if(disaccount<=0.05){
			System.out.println("销售人员同意该折扣:"+disaccount);
		}else{//级别不够传递到上一级领导处理
			successor.caluPriceHandler(disaccount);
		}
	}

}
