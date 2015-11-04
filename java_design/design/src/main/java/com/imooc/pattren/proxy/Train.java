package com.imooc.pattren.proxy;

/**
 * 火车
 * @author Administrator
 *
 */
public class Train implements MoveInterface{

	@Override
	public void move() {
		System.out.println("火车开动了....");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("火车到站了....");
	}

}
