package com.yc.spring;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.spring.bean.Person;
import com.yc.spring.dao.UserDao;

public class HelloTest {

	private ClassPathXmlApplicationContext cpx;
	
	@Before
	public void before() {
		cpx = new ClassPathXmlApplicationContext("beans.xml");
	}
	
	@Test
	public void test() {
		
		Hello h = (Hello) cpx.getBean("hello");
		Hello h1 = (Hello) cpx.getBean("hello");
		Hello h2 = (Hello) cpx.getBean("hello");

		System.out.println(h1 == h2);
		
		h.sayHello();
		
		cpx.close();
	}
	
	@Test
	public void test1() {
		ClassPathXmlApplicationContext cpx = new ClassPathXmlApplicationContext("beans.xml");
		
		UserDao udao1 = (UserDao) cpx.getBean("mdao");
		UserDao udao2 = (UserDao) cpx.getBean("odao");
		
		udao1.selectUserId("zhangsan");
		udao2.selectUserId("zhangsan");
		cpx.close();

	}
	
	@Test
	public void test2() {
		Person p1 = (Person) cpx.getBean("p1");
		Assert.assertEquals("武松", p1.getName());
		Assert.assertEquals(35, p1.getAge());
		Assert.assertEquals(5, p1.getKilleds().size());
		Assert.assertEquals("蒋门神", p1.getKilleds().get(2));

		Assert.assertEquals(185, p1.getHeight());
		Assert.assertEquals("行者武松", p1.getAlisa());

	}
	
}
