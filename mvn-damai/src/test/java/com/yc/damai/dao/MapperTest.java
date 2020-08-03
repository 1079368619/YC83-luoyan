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

import com.yc.damai.po.DmCategory;
import com.yc.damai.po.DmProduct;

public class MapperTest {

	private SqlSession session;
	
	//动态块
	{
		try {
			// mybatis配置文件
			String resource = "mybatis.xml";
			// 读入配置文件
			InputStream inputStream = Resources.getResourceAsStream(resource);
			// 构建会话工厂
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			// 开启会话
			session = sqlSessionFactory.openSession();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	//测试方法必须加 test 注解
	@Test
	public void test1() throws IOException {
		//session.selectList("xml接口的命名空间+.+查询的sql的id");
		List<DmProduct> list = session.selectList("com.yc.damai.dao.ProductMapper.selectAll");
		
		/**
		 * 使用断言进行结构的判断
		 * true 表示的期望值
		 * list.size()>0 是实际值
		 */
		Assert.assertEquals(true, list.size()>0);
		/**
		for(DmProduct dp : list) {
			System.out.println(dp);
		}
		*/
	}

	@Test
	public void test2() throws IOException {
		DmCategory dc =new DmCategory();
		dc.setCname("测试分类");
		dc.setPid(1);
		//session.insert("com.yc.damai.dao.ProductMapper.insert", dc);
		DmCategoryMapper mapper = session.getMapper(DmCategoryMapper.class);
		mapper.insert(dc);
		//不commit，会话会在关闭自动回滚
		session.commit();
		session.close();
	}
	
	@Test
	public void test3() throws IOException {
		DmCategory dc =new DmCategory();
		dc.setId(45);
		dc.setCname("修改后的测试分类");
		dc.setPid(1);
		//session.update("com.yc.damai.dao.ProductMapper.update", dc);
		DmCategoryMapper mapper = session.getMapper(DmCategoryMapper.class);
		mapper.update(dc);
		//不commit，会话会在关闭自动回滚
		session.commit();
		session.close();
	}
	
	@Test
	public void test4() throws IOException {
		/**DmCategory dc =new DmCategory();
		dc.setId(44);
		dc.setCname("修改后的测试分类");
		dc.setPid(1);*/
		//session.delete("com.yc.damai.dao.ProductMapper.delete", dc);
		DmCategoryMapper mapper = session.getMapper(DmCategoryMapper.class);
		mapper.delete(45);
		//不commit，会话会在关闭自动回滚
		session.commit();
		session.close();
	}
	
	//作业: 请完成商品表信息增删改查(根据id查)
	@Test
	public void test5() throws IOException {
		DmProduct dp =new DmProduct();
		dp.setId(76);
		session.selectOne("com.yc.damai.dao.ProductMapper.select", dp);		
		//不commit，会话会在关闭自动回滚
		session.commit();
	}
	
	@Test
	public void test6() throws IOException {
		DmProduct dp =new DmProduct();
		dp.setPname("屈臣氏露得清维A醇保湿修护焕亮晚霜淡化细纹眼霜护肤套装包邮");
		dp.setMarketPrice(458.0);
		dp.setShopPrice(408.0);
		dp.setPdesc("屈臣氏露得清维A醇保湿修护焕亮晚霜淡化细纹眼霜护肤套装包邮");
		dp.setIsHot(1);
		dp.setCid(7);
		session.insert("com.yc.damai.dao.ProductMapper.insert1", dp);
		//不commit，会话会在关闭自动回滚
		session.commit();
	}
	
	@Test
	public void test7() throws IOException {
		DmProduct dp =new DmProduct();
		dp.setMarketPrice(598.0);
		dp.setShopPrice(508.0);
		dp.setIsHot(0);
		dp.setCid(6);
		dp.setId(80);
		session.update("com.yc.damai.dao.ProductMapper.update1", dp);
		//不commit，会话会在关闭自动回滚
		session.commit();
	}
	
	@Test
	public void test8() throws IOException {
		DmProduct dp =new DmProduct();
		dp.setId(80);
		session.delete("com.yc.damai.dao.ProductMapper.delete1", dp);
		//不commit，会话会在关闭自动回滚
		session.commit();
	}
}
