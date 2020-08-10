package com.yc.spring.dao;

public class MySQLUserDao implements UserDao {

	@Override
	public int selectUserId(String name) {
		System.out.println("mysql user dao ");
		return 0;
	}

}
