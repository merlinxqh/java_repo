package com.imooc.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MethodDemo2 {
   public static void main(String[] args) {
	 ArrayList list1=new ArrayList();
	 ArrayList<String> list2=new ArrayList<String>();
	 list2.add("guoguo");
	 Class c1=list1.getClass();
	 Class c2=list2.getClass();
	 
	 System.out.println(c1 == c2);//ture
	 /**
	  * c1 == c2结果返回true说明编译之后集合的泛型是 去 泛型化的
	  * Java中集合的泛型,是为了防止错误输入的, 只在编译阶段有效
	  * 绕过编译就无效了
	  * 验证:我们通过反射操作来绕过编译
	  */
	 try {
		Method m=c2.getMethod("add", Object.class);//获取add方法
		m.invoke(list2, 200);//通过反射 往String类型 的list添加 int数据
		System.out.println(list2.size());
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
