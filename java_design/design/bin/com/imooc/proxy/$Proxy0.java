package com.imooc.proxy;
public class $Proxy0 implements com.imooc.pattren.proxy.MoveInterface{
private com.imooc.pattren.proxy.MoveInterface moveInterface;
public $Proxy0(com.imooc.pattren.proxy.MoveInterface moveInterface){
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