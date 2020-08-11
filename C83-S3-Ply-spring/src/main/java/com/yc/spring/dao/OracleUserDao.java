package com.yc.spring.dao;

import org.springframework.stereotype.Repository;

@Repository("odao")
public class OracleUserDao implements UserDao {

	@Override
	public int selectUserId(String name) {
		System.out.println("oracle user dao ");
		return 0;
	}

}
