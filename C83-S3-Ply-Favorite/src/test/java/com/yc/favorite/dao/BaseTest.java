package com.yc.favorite.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yc.favorite.bean.Favorite;
import com.yc.favorite.bean.Tag;
import com.yc.favorite.bean.TagFavorite;
import com.yc.favorite.biz.FavoriteBiz;
import com.yc.favorite.util.MyBatisHelper;

public class BaseTest {

	@Test
	public void test1() {
		SqlSession session = MyBatisHelper.openSession();
		TagMapper tm = session.getMapper(TagMapper.class);
		FavoriteMapper fm = session.getMapper(FavoriteMapper.class);
		TagFavoriteMapper tfm = session.getMapper(TagFavoriteMapper.class);
		
		Tag t = new Tag();
		Favorite f = new Favorite();
		TagFavorite tf = new TagFavorite();
		
		t.setTname("生活");
		
		f.setFlabel("淘宝");
		f.setFurl("taobao.com");
		f.setFdesc("败家网站");
		f.setFtags("购物,生活");
		
		tf.setTid(1);
		tf.setFid(1);
		
		tm.insert(t);
		fm.insert(f);
		tfm.insert(tf);
		session.commit();
		session.close();
	}
	
	@Test
	public void test2() {
		FavoriteBiz fb = new FavoriteBiz();
		Favorite f = new Favorite();
		f.setFlabel("淘宝");
		f.setFurl("taobao.com");
		f.setFdesc("败家网站");
		f.setFtags("购物,生活");
		fb.addFavorite(f);
	}
		
	@Test
	public void test3() {
		FavoriteBiz fb = new FavoriteBiz();
		Favorite f = new Favorite();
		f.setFlabel("网易");
		f.setFurl("123.com");
		f.setFdesc("好网站");
		f.setFtags("门户,军事,生活");
		fb.addFavorite(f);
	}
	
	@Test
	public void test4() {
		SqlSession session = MyBatisHelper.openSession();
		FavoriteMapper fm = session.getMapper(FavoriteMapper.class);
		fm.selectByTid(null);
		fm.selectByTid(1);
		fm.selectByTid(0);
	}
	
	@Test
	public void test5() {
		FavoriteBiz fb = new FavoriteBiz();
		Favorite f = new Favorite();
		f.setFlabel("百度");
		f.setFurl("baidu.com");
		f.setFdesc("搜索网站");
		f.setFtags("");
		fb.addFavorite(f);
	}
}
