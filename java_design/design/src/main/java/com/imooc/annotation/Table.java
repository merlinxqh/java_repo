package com.imooc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})//作用域  类or接口
@Retention(RetentionPolicy.RUNTIME)//声明周期 
public @interface Table {
   String value();
}
