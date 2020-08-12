package com.yc.spring;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.yc.spring.bean.Person;
import com.yc.spring.dao.MySQLUserDao;
import com.yc.spring.dao.OracleUserDao;

public class BeanConfig {

	@Bean(name="hello")
	public Hello getHello() {
		return new Hello();
	}
	
	/**
	  @Bean(name="odao") 
	  public OracleUserDao getOracleUserDao() { 
	  		return new OracleUserDao(); 
	  }
	  
	  @Bean(name="mdao") 
	  public MySQLUserDao getMySQLUserDao() { 
	  		return new MySQLUserDao(); 
	  }
	 */
	
	@Bean(name="p1")
	public Person getPerson() {
		Person ret = new Person();
		ret.setName("武松");
		ret.setAge(35);
		ret.setKilleds(new ArrayList<String>());
		ret.getKilleds().add("西门庆");
		ret.getKilleds().add("西门庆");
		ret.getKilleds().add("蒋门神");
		ret.getKilleds().add("西门庆");
		ret.getKilleds().add("西门庆");
		ret.getKilleds().add("西门庆");

		return ret;
	}
	
	@Bean(name="p5")
	public Person getPerson5() {
		Person p = new Person();
		p.setName("王英");
		return p;
	}
	
	@Bean("hello1")
	@Scope(value="prototype")
	public Hello getHello1() {
		return new Hello();
	}
	
	@Bean("hello2")
	@Lazy
	public Hello getHello2() {
		return new Hello();
	}
}
