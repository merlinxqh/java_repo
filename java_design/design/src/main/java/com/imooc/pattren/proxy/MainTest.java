package com.imooc.pattren.proxy;

public class MainTest {

	public static void main(String[] args) {
		Train car=new Train();
		//先记录日志在记录时间
		CarTimeProxy time=new CarTimeProxy(car);
		CarLogProxy log=new CarLogProxy(time);
		
		log.move();
	}

}
