package com.yc.thread;

public class PostServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		System.out.println("=========================");
		response.getWriter().append("post, name=" + name);
		
		/**
		 * 扩展，请完成post请求中实体中的参数解析
		 */
		
	}


}
