package com.yc.thread;

import java.io.IOException;

/**
 * Servlet�̳й�ϵ
 * Servlet==�� GenriceServlet ==> HttpServlet ==> �Զ����Servlet
 * @author ����
 *
 */
public interface Servlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
		throws IOException;
	
	//
	
}
