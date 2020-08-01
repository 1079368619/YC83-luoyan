package com.yc.thread;

public class ToIndexServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		/**
		 * 
		 */
		response.sendRedirect("/photo/index.html");
		
		
	}
}
