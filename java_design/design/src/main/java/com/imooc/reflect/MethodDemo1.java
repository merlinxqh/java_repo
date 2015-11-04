package com.imooc.reflect;

import java.lang.reflect.Method;

public class MethodDemo1 {
	
	public static void main(String[] args) {
		
		A a1=new A();
		Class c=a1.getClass();
		
		/**
		 * 获取类的一个方法  是 根据方法名 + 参数列表 来定位
		 * getMethod()是获取public的方法
		 * getDeclaredMethod()获取 该类自己所有定义的方法
		 */
//		c.getMethod(name, parameterTypes)
		try {
//			Method md=c.getDeclaredMethod("print", int.class,int.class);
			Method md=c.getDeclaredMethod("print", new Class[]{int.class,int.class});
			//方法如果没有返回值,obj就是null ,如果有返回值 返回的就是对应的对象
			Object obj=md.invoke(a1, new Object[]{10,22});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} 
class A{
	public void print(int a,int b){
		System.out.println(a+b);
	}
	public void print(String a,String b){
		System.out.println(a.toUpperCase()+","+b.toLowerCase());
	}
}
