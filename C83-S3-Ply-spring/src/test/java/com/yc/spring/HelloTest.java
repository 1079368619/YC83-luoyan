package com.yc.spring;

import org.junit.After;
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
	
	@After
	public void after() {
		cpx.close();
	}
	
	
	@Test
	public void test() {
		//Hello h = new Hello();

		/**
		 * Spring 框架解决的问题
		 * Servlet
		 * 	 UserBiz ubiz = new UserBiz();
		 *   UserBiz ubiz = new SubUserBiz1();
		 *   UserBiz ubiz = new SubUserBiz2();
		 * 1. new ==> 创建对象  ==> 内存中占用存储对象的空间
		 * 		每次new都会创建一个新的对象 ==> 内存消耗大
		 * 		解决的方式: 使用对象池
		 * 		对象池. get对象  获取对象
		 * 2.   耦合性问题
		 * 		对象可以任意在运行期设置为指定的子类现实类
		 * 
		 * 	控制反转 ( Inversion of Control ): 
		 * 		对象创建有程序员决定
		 * 		对象的创建由容器决定
		 */
		
		// 从 Spring 框架(容器)中获取一个 Hello 对象
		// 创建Spring容器对象
		ClassPathXmlApplicationContext cpx = new ClassPathXmlApplicationContext("beans.xml");
		
		Hello h = (Hello) cpx.getBean("hello");
		Hello h1 = (Hello) cpx.getBean("hello");
		Hello h2 = (Hello) cpx.getBean("hello");

		// h1 和 h2 是同一个对象
		System.out.println(h1 == h2);// 单例模式
		
		// 执行 sayHello 方法
		h.sayHello();
		
		cpx.close();
	}
	
	@Test
	public void test1() {
		ClassPathXmlApplicationContext cpx = new ClassPathXmlApplicationContext("beans.xml");
		// UserDao udao1 = new MySQLUserDao();
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
	
	@Test
	public void test3() {
		// java.lang.NoSuchMethodException: com.yc.spring.bean.Person.<init>()
		// 未找到无参数的构造函数

		/**
		 * NoUniqueBeanDefinitionException: 
		 * No qualifying bean of type 'com.yc.spring.bean.Person' available: 
		 * 		不唯一的bean定义 expected single matching
		 * bean but found 2: p1,com.yc.spring.bean.Person#0 
		 * 		应该是1个但是出现2个
		 */

		Person p1 = (Person) cpx.getBean(Person.class);
		Assert.assertEquals("李逵", p1.getName());
		Assert.assertEquals(33, p1.getAge());
		Assert.assertEquals(null, p1.getKilleds());
	}
	
	@Test
	public void test4() {
		Person p1 = (Person) cpx.getBean("p2");
		Assert.assertEquals("吴用", p1.getName());
		Assert.assertEquals(38, p1.getAge());
		Assert.assertEquals("华荣", p1.getFriend().getName());
	}
	
	@Test
	public void test5() {
		Person p1 = (Person) cpx.getBean("p5");
		Assert.assertEquals("王英", p1.getName());
		Assert.assertEquals(40, p1.getAge());
	}
	
	@Test
	public void test6() {
		Person p1 = (Person) cpx.getBean("p6");
		Assert.assertEquals("扈三娘", p1.getName());
		Assert.assertEquals(20, p1.getAge());
	}
	
	/**
	 * bean 的作用域
	 * 默认情况下, bean的作用域是 单例模式
	 */
	@Test
	public void test7() {
		System.out.println("==========test7==========");
		Hello h0 = (Hello) cpx.getBean("hello");
		Hello h0_1 = (Hello) cpx.getBean("hello");
		Hello h0_2 = (Hello) cpx.getBean("hello");

		Hello h1 = (Hello) cpx.getBean("hello1");
		Hello h1_1 = (Hello) cpx.getBean("hello1");
		Hello h1_2 = (Hello) cpx.getBean("hello1");

		System.out.println(h0 == h1);// false
		System.out.println(h0_1 == h0_2);// true
		System.out.println(h1_1 == h1_2);// false
		System.out.println(h1 == h1_1);// false
		System.out.println(h1 == h1_2);// false

	}
	
	/**
	 * 懒加载
	 */
	@Test
	public void test8() {
		System.out.println("==========test8==========");

		Hello h0 = (Hello) cpx.getBean("hello2");
		h0.sayHello();
	}
	
	/**
	 * 生命周期方法
	 */
	@Test
	public void test9() {
		Hello h0 = (Hello) cpx.getBean("hello3");
		h0.sayHello();
	}
	
	/**
	 * 自动装载 
	 */
	@Test
	public void test10() {
		Person p7 = (Person) cpx.getBean("p7");
		System.out.println(p7.getFriend().getName());
	}
	
}
