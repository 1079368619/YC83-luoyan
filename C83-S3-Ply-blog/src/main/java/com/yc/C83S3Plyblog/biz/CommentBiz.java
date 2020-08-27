package com.yc.C83S3Plyblog.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.C83S3Plyblog.bean.Article;
import com.yc.C83S3Plyblog.bean.Comment;
import com.yc.C83S3Plyblog.dao.ArticleMapper;
import com.yc.C83S3Plyblog.dao.CommentMapper;

@Service
public class CommentBiz {

	@Resource
	private CommentMapper cmapper;
	
	public Comment create(Comment comm) {
		cmapper.insert(comm);
		return comm;
	}
	
}
