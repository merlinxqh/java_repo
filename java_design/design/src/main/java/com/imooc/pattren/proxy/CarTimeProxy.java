package com.imooc.pattren.proxy;

/**
 * 通过聚合 的方式 实现静态代理   代理之间可以实现相互传递
 * 汽车行驶  时间代理对象
 * @author Administrator
 *
 */
public class CarTimeProxy implements MoveInterface{
  private MoveInterface moveInterface;
  
  public CarTimeProxy(MoveInterface moveInterface){
	  super();
	  this.moveInterface=moveInterface;
  }
  
  public void move(){
	  long starttime=System.currentTimeMillis();
	  this.moveInterface.move();
	  long endtime=System.currentTimeMillis();
	  System.out.println("记录汽车行驶时间:"+(endtime-starttime));
  }
}
