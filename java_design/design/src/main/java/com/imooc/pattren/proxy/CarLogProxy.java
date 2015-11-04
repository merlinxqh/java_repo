package com.imooc.pattren.proxy;

public class CarLogProxy implements MoveInterface{
	
	private MoveInterface move;
	
	public CarLogProxy(MoveInterface move){
		super();
		this.move=move;
	}

	@Override
	public void move() {
		System.out.println("记录日志:汽车开始行驶....");
		move.move();
		System.out.println("记录日志:汽车行驶结束....");
	}

}
