package com.yc.C83S3Plyblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.yc.C83S3Plyblog.bean.Comment;

public interface CommentMapper {

	@Select("select * from comment where id=#{id}")
	public Comment selectById(int id);
	
	@Insert("insert into comment values(null,#{articleid},#{content},#{createby},now())")
	public int insert(Comment comment);
	
	@Select("select * from comment where articleid=#{articleid}")
	public List<Comment> selectByArticle(int articleid);
}
