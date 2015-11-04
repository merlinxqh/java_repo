package com.imooc.pattren.proxy;

public class Car implements MoveInterface{

	@Override
	public void move() {
		System.out.println("汽车开动了....");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("汽车到地方了....");
	}
   
}
