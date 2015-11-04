package com.imooc.pattren.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class TrainInterceptor implements MethodInterceptor{
	private Enhancer enhancer=new Enhancer();
	
	@SuppressWarnings("rawtypes")
	public Object getProxy(Class clazz){
		//设置创建子类的类
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return enhancer.create();
	}
    
	/**
	 * 参数:
	 *    obj  目标类的实例
	 *    method 目标方法的反射对象
	 *    args   方法的参数
	 *    proxy  代理类的实例
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("代理方法执行前.........");
		//代理类调用父类方法
		proxy.invokeSuper(obj, args);
		System.out.println("代理方法执行后.........");
		return null;
	}

}
