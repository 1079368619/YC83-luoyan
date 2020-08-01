package com.yc.thread;

import java.io.IOException;

/**
 * HttpServlet ����������󷽷�method����doXXX����
 * 
 * service() �����жϺ�ת��
 * doXXX()   ����������Ӧ��method
 * @author ����
 *
 */
public class HttpServlet implements Servlet{

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException{
		if("GET".equals(request.getMethod())) {
			doGet(request, response);
		}else if("POST".equals(request.getMethod())) {
			doPost(request, response);
		}else if("PUT".equals(request.getMethod())) {
			doPut(request, response);	
		}else if("DELETE".equals(request.getMethod())) {
			doDelete(request, response);
		}else {
			//�����в���������doXXX() ����
		}
		
		response.setStatus(200, "OK");
		response.flushBuffer();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	public void doPut(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	public void doDelete(HttpServletRequest request, HttpServletResponse response) {
		
	}
	

}
