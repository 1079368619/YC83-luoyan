package com.yc.C83S3Plyblog.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.yc.C83S3Plyblog.bean.Article;
import com.yc.C83S3Plyblog.bean.User;
import com.yc.C83S3Plyblog.biz.ArticleBiz;
import com.yc.C83S3Plyblog.dao.ArticleMapper;
import com.yc.C83S3Plyblog.util.Utils;

@RestController
public class ArticleAction {

	@Resource
	private ArticleBiz abiz;
	
	@Resource
	private ArticleMapper amapper;
	
	/*
	 * @GetMapping("toAddArticle") 
	 * public String addArticle() { 
	 * 		return "addArticle";
	 * }
	 */
	
	@GetMapping("article")
	public ModelAndView article(int id,ModelAndView mav) { 
		mav.addObject("article", amapper.selectById(id));
		mav.setViewName("article");
		return mav;
	}
	
	
	@GetMapping("toAddArticle")
	public ModelAndView toAddArticle(ModelAndView mav) { 
		mav.setViewName("addArticle");
		return mav;
	}
	
	@PostMapping("addArticle.do")
	public ModelAndView addArticle(@Valid Article a, Errors errors, ModelAndView mav,@SessionAttribute("loginedUser") User user) {
		// ModelAndView = Model + View
		if(errors.hasErrors()) {
			mav.addObject("errors",Utils.asMap(errors));
			mav.addObject("article", a);
			mav.setViewName("addArticle");
		}else {
			// 作者的名字写入文章实体 ==> loginUser
			a.setAuthor(user.getName());
			abiz.create(a);
			// a.id ==> 有值 ==> 数据库的自增列 ==> MyBatis ==> @Options
			mav.setViewName("redirect:article?id=" + a.getId());
			// 展示新添加的文章
		}
		return mav;
	}
}
