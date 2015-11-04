package com.imooc.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtil {
   
	/**
	 * 打印类的信息,包括类的成员函数
	 * @param obj
	 */
	public static void printMethodMessage(Object obj){
		//要获取类的信息,首先要获取该类的类类型
		Class c=obj.getClass();///传递的事哪个子类的对象,c就是该子类的类类型
		//获取类的名称
		String clzname=c.getName();
		/**
		 * Method  是方法的对象
		 * 一个成员方法就是一个Method对象
		 * getMethods()获取的是所有public函数,包括父类的方法
		 * c.getDeclaredMethods()获取的是所有该类自己声明的方法,不限访问权限
		 */
		Method[] ms=c.getMethods();//c.getDeclaredMethods();
		for(int i=0;i<ms.length;i++){
			Class returnType=ms[i].getReturnType();//得到方法的返回值类型的类类型
			System.out.print(returnType.getName()+" ");
			//得到方法的名称
			System.out.print(ms[i].getName()+"(");
			//获取参数类型-->得到参数列表的类型的类类型
			Class[] paramTypes=ms[i].getParameterTypes();
			for(Class c1:paramTypes){
				System.out.print(c1.getName()+",");
			}
			System.out.println(")");
		}
	}
    
	/**
	 * 获取对象的成员变量的信息
	 * @param obj
	 */
	public static void printFieldMessage(Object obj) {
		Class c=obj.getClass();
		/**
		 * 成员变量也是一个对象
		 * java.lang.reflect.Field
		 * Field封装了关于成员变量的操作
		 * getFields()是获取类的所有public的成员变量的信息 
		 * getDeclaredFields()获取的是该类自己声明的成员变量信息
		 */
		Field[] fs=c.getFields();//c.getDeclaredFields()
		for(Field fd:fs){
			//得到成员变量的类型的类类型
			Class fieldType=fd.getType();
			String typeName=fieldType.getName();
			//得到成员变量的名称
			String fieldName=fd.getName();
			System.out.println(typeName +" "+fieldName);
		}
	}
	
	/**
	 * 获取对象的  构造函数 的信息
	 * @param obj
	 */
	public static void printConMessage(Object obj){
		Class c=obj.getClass();
		/*
		 * 构造函数也是对象
		 * java.lang.reflect.Constructor中封装了构造函数的信息
		 * getConstructors()获取所有的publi的构造函数
		 * getDeclaredConstructors()获取所有自己声明的构造函数
		 */
//		Constructor[] cs=c.getConstructors();
		Constructor[] cs=c.getDeclaredConstructors();
		for(Constructor ct:cs){
            System.out.print(ct.getName()+"(");
			//获取构造函数的参数列表
			Class[] paramTypes=ct.getParameterTypes();//
			for(Class cp:paramTypes){
				System.out.print(cp.getSimpleName()+",");
			}
			System.out.println(")");
		}
	}
} 
