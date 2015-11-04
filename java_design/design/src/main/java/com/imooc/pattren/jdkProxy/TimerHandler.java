package com.imooc.pattren.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.imooc.pattren.proxy.MoveInterface;
/**
 * jdk动态代理
 * @author Administrator
 *
 */
public class TimerHandler implements InvocationHandler{
	
	private MoveInterface target;

	public TimerHandler(MoveInterface target) {
		super();
		this.target = target;
	}


	/**
	 * target 被代理目标
	 * method  被代理对象的方法
	 * args 参数
	 * 返回 Object对象
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		long starttime=System.currentTimeMillis();
		method.invoke(target);
		long endtime=System.currentTimeMillis();
		System.out.println("记录汽车行驶时间:"+(endtime-starttime));
		return null;
	}

}
