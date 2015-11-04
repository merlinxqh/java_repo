package com.imooc.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) {
		for(int i=0;i<10;i++){
			if(i==3){
				continue;
			}
			System.out.println(i);
		}
	}
    
    /**
     * 根据传入的对象 返回sql语句
     * @param f
     * @return
     */
    public String query(Filter f){
    	//1.现获取 类的类类型 Class
    	Class c=f.getClass();
    	
    	//2.获取table 名
    	String tableName="";
    	if(c.isAnnotationPresent(Table.class)){//有Table这个注解
    		Table t=(Table) c.getAnnotation(Table.class);
    		tableName=t.value();
    	}
    	
    	//3.遍历所有字段
    	Field[] fs=c.getDeclaredFields();//获取 类上所有字段
    	for(Field fe:fs){
    		if(!fe.isAnnotationPresent(Column.class)){//有Column这个注解
    			continue;
    		}else{
    			Column column=fe.getAnnotation(Column.class);
    			String columnName=column.value();//获得表字段名
    			
    			String fieldName=fe.getName();//取得属性名
    			//用get方法 判断 属性值 是否为空
    			String methodName="get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
    			try {
    				Method get=c.getMethod(methodName);
					Object obj=get.invoke(f);
				} catch (Exception e) {
					e.printStackTrace();
				} 
                
    			
    		}
    	}
    	return null;
    }
}


