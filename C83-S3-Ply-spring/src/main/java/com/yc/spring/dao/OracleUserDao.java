package com.yc.spring.dao;

public class OracleUserDao implements UserDao {

	@Override
	public int selectUserId(String name) {
		System.out.println("oracle user dao ");
		return 0;
	}

}
