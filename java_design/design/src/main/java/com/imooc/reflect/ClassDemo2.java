package com.imooc.reflect;

public class ClassDemo2 {
	
	public static void main(String[] args) {
		//new 创建对象 是静态加载类 ,在编译时刻就需要加载所有的可能使用到的类
		
		//动态加载类  在运行时刻加载
		try {
			Class clz=Class.forName("");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   
}
