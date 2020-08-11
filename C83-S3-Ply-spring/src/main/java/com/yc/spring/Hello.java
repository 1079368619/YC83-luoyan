package com.yc.spring;

public class Hello {

	public Hello() {
		/**/
		System.out.println("hello无参数的构造函数");
	}
	
	public Hello(int a) {
		/**/
		System.out.println("hello有参数的构造函数");
	}
	
	public void sayHello() {
		System.out.println("你好世界");
	}
	
	public void init() {
		System.out.println("hello被创建");
	}
	
	public void destory() {
		System.out.println("hello被销毁");
	}
	
}
