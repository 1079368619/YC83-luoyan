package com.yc.thread;

import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		
		PrintWriter out = response.getWriter();
		//�����ҳ��
		out.print("<h1>hello "+name+"</h1>");
		
		System.out.println("hello world!");
	}
}
