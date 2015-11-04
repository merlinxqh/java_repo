package com.imooc.pattren;
/**
 * 单例模式Singleton
 * 应用场合:  有些对象有一个就足够了,如古代皇帝,老婆
 * 作用:保证整个应用程序中,某个实例有且只有一个   (读取配置文件,工具类,线程池,缓存,日志对象等)
 * 类型: 懒汉模式,饿汉模式
 * @author Administrator
 *
 */
public class Singleton {
   //第一步,将构造方法私有化,不允许外部直接创建对象
	private Singleton(){
		
	}
	
	/*饿汉模式          在类加载的时候已经实例化好 对象*/
	
	//第二步,创建类的唯一实例
	private static Singleton instance=new Singleton();
	
	//第三部,提供一个获取实例的方法
	public static Singleton getInstance(){
		return instance;
	}
	
	
	/*懒汉模式              在第一次调用的时候才实例化对象*/
	private static Singleton instance2;
	
	public static Singleton getInstance2(){
		if(instance2 == null){
			instance2=new Singleton();
		}
		return instance2;
	}
}
