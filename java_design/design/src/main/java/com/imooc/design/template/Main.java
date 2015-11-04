package com.imooc.design.template;

public class Main {
  public static void main(String[] args) {
	TemplateBase tea=new TeaTemplate();
	
	TemplateBase coffe=new CoffeTemplate();
	
	tea.perpareBeverageTemplate();
	
	coffe.perpareBeverageTemplate();
  }
}
