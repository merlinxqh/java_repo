package com.imooc.annotation;

import java.lang.reflect.Method;

/**
 * 解析注解
 * @author Administrator
 *
 */
public class ParseAnnotation {
   public static void main(String[] args) {
	 //1.使用类加载器加载类
	   try {
		Class c=Class.forName("com.imooc.annotation.AnnTest");
		//2.找到类上的注解
		boolean isExist=c.isAnnotationPresent(Description.class);//判断这个类上 是否包含@Description这个注解,返回boolean
		if(isExist){
			//3.拿到注解实例
			Description desc=(Description) c.getAnnotation(Description.class);
			System.out.println(desc.desc());
		}
		
		//4找到方法上的注解
		Method[] ms=c.getMethods();
		for(Method m:ms){
			boolean isMexist=m.isAnnotationPresent(Description.class);
			if(isMexist){
				Description d=m.getAnnotation(Description.class);
				System.out.println(d.desc());
			}
		}
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	   
	   
}
}
