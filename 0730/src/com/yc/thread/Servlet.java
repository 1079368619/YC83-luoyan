package com.yc.thread;

import java.io.IOException;

/**
 * Servlet继承关系
 * Servlet==》 GenriceServlet ==> HttpServlet ==> 自定义的Servlet
 * @author 罗艳
 *
 */
public interface Servlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
		throws IOException;
	
	//
	
}
