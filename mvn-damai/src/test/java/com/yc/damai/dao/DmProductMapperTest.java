package com.yc.damai.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.yc.damai.po.DmProduct;

public class DmProductMapperTest {

	@Test
	public void test1() throws IOException {
		//
		String resource = "mybatis.xml";
		//
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//
		SqlSession session = sqlSessionFactory.openSession();
		
		//
		List<DmProduct> list = session.selectList("com.yc.damai.dao.ProductMapper.selectAll");
		
		Assert.assertEquals(true, list.size()>0);
		/**
		for(DmProduct dp : list) {
			System.out.println(dp);
		}
		*/
	}

}
