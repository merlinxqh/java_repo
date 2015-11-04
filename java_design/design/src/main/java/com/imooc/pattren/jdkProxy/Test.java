package com.imooc.pattren.jdkProxy;

import java.lang.reflect.Proxy;

import com.imooc.pattren.proxy.Car;
import com.imooc.pattren.proxy.MoveInterface;

public class Test {
  public static void main(String[] args) {
	  Car car=new Car();
	  TimerHandler h=new TimerHandler(car);
	  Class<?> cls=car.getClass();
	  //参数: 1.类加载器  2.实现接口  3.InvocationHandler  事务处理器
	  
	  //获取到动态代理对象
	  MoveInterface move=(MoveInterface)Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), h);
	  move.move();
  }
  
  /**
   * jdk动态代理实现思路
   * 
   *  1,声明一段源码 (动态产生代理)
   *  2,编译源码(JDK compiler API) ,产生新的类 (代理类)\
   *  3,将这个类load到内存中,产生一个新的对象(代理对象)
   *  4,return 代理对象
 * @throws Exception 
   */
}
