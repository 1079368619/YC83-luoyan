package com.yc.favorite.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.yc.favorite.bean.Favorite;
import com.yc.favorite.biz.FavoriteBiz;
import com.yc.favorite.dao.TagMapper;
import com.yc.favorite.util.MyBatisHelper;

@WebServlet("/AddFavorite.do")
public class AddFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		SqlSession session = MyBatisHelper.openSession();
		
		String flabel = request.getParameter("flabel");
		String furl = request.getParameter("furl");
		String ftags = request.getParameter("ftags");
		String fdesc = request.getParameter("fdesc");
		
		if(flabel == null || flabel.trim().isEmpty()) {
			response.getWriter().append("请输入名称！！！");
		}else if(furl == null || furl.trim().isEmpty()) {
			response.getWriter().append("请输入地址！！！");
		}else {
			FavoriteBiz fb = new FavoriteBiz();
			Favorite f = new Favorite();
			fb.addFavorite(f);
			response.getWriter().append("网站收藏成功");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
