package com.yc.damai.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/verify.do")
public class verifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String vcode=request.getParameter("vcode");
		String scode=(String)request.getSession().getAttribute("vcode");
		if(vcode!=null&&vcode.trim().isEmpty()==false) {
			if(vcode.equalsIgnoreCase(scode)) {
				response.getWriter().append("验证码正确！ ");
			}else {
				response.getWriter().append("验证码错误！ ");
			}
		}else {
			response.getWriter().append("请输入验证码！ ");
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
