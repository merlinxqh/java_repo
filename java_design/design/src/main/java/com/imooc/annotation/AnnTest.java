package com.imooc.annotation;

/**
 * 使用注解
 * @author Administrator
 *
 */
@Description(desc="i am a class annotation...",author="class")
public class AnnTest {
   /*
    * 使用自定义注解
    *   语法:  @<注解名>(<成员名1>=<成员值1>,....)
    */
	
	@Description(desc="i am a method annotation...", author = "huige", age=20)
	public String annTest(){
		return "ann Test";
	}
}
