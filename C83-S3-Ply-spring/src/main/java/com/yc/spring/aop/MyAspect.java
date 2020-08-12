package com.yc.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

	@Pointcut(("execyion(* com.yc.spring.dao.MySQLUserDao.*(..))"))
	public void aspect1() {
		//
	}
	
	@Before("aspect1()")
	public void before() {
		System.out.println("======增强=====");
	}
}
