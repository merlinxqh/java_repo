package com.imooc.reflect;

public class ClassDemo1 {
  @SuppressWarnings("rawtypes")
public static void main(String[] args) {
	  //Foo的实例如何表示
	  Foo foo=new Foo();
	  
	  //Foo这个类也是一个对象 Class类的实例对象 如何表示呢
	  //任何一个类都是 Class类的实例对象,这个实例对象有三种表示方式
	  //1.第一种表示方式 -------->实际上是在告诉我们任何一个类都有一个静态成员变量class
	  Class c1=Foo.class;
	  //2.第二种表达方式   已知某一个类的对象通过getClass方法
	  Class c2=foo.getClass();
	  
	  /**
	   * java官网的描述是 c1,c2是Foo类的 类类型(class type)
	   * 万事万物皆对象
	   * 类也是一个对象,是Class类的实例对象
	   * 这个对象我们称之为该类的类类型
	   * 
	   */
	  //不管是c1还是c2都代表了Foo类的类类型,一个类只可能有一个Class类的实例对象
	  System.out.println(c1==c2);//true
	  
	  //第三种表达方式
	  Class c3=null;
	  try {
		c3=Class.forName("com.imooc.reflect.Foo");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	  
	  System.out.println(c2==c3);//true
	  
	  //我们完全可以通过类的类类型创建该类的对象---->也就是 通过c1 or c2 or c3创建Foo的实例对象
	  try {
		Foo ff=(Foo) c1.newInstance();
		ff.print();
	} catch (InstantiationException e) {
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	}
	  
}
}
class Foo{
	void print(){
		System.out.println("Foo print....");
	}
}
