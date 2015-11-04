
package com.imooc.pattren.cglib;

public class Test {
   public static void main(String[] args) {
	 TrainInterceptor interceptor=new TrainInterceptor();
	 Train train=(Train) interceptor.getProxy(Train.class);
	 train.run();
}
}
