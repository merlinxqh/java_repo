package com.imooc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解
 * @interface  关键字 定义注解
 *
 */
/*
 *  元注解:
 *  1. @Target 注解的作用域
 *         参数列表:CONSTRUCTOR  (构造方法声明)
 *                 FIELD   (字段声明)
 *                 LOCAL_VARIABLE  (局部变量声明)
 *                 METHOD   (方法声明)
 *                 PACKAGE    (包声明)
 *                 PARAMETER   (参数声明)
 *                 TYPE  (类&接口)
 *  2.@Retention  注解的声明周期
 *          参数列表:SOURCE  (只会在源码显示,编译时会丢弃)
 *                   CLASS   (编译时会记录到.class中,运行时忽略)
 *                   RUNTIME  (运行时存在,可以通过反射读取)
 *  3.@Inherited  标识型注解  表示  允许子注解 继承
 *  4.@Documented 生成javadoc时 会包含注解的信息
 *                 
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Description {
  /*
   * 1.成员变量的类型 是受限制的, 合法的 类型包括  原始类型  及 String , Class ,Annotation , Enumeration
   * 2.当注解只有一个成员变量的时候,成员名必须命名为 value(),在使用时可以忽略成员名和赋值号(=)
   * 3.注解类可以没有成员 ,没有成员的注解 称为 标识	注解
   */
  String desc();//成员变量以 无参数,无异常 方式声明
  
  String author();
  
  int age() default 18;//default 设置默认值
}
