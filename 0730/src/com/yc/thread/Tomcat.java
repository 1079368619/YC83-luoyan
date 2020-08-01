package com.yc.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Tomcat {
	//Servlet����
	private HashMap<String, Servlet> servletMap;

	public void startup() throws IOException {
		
		//��������ʼ��Servlet����==��MAp����==��URL:Servlet����
		servletMap = new HashMap<>();
		servletMap.put("/photo/hello", new HelloServlet());
		//��ToIndexServlet��Ϊ��ַ��Ĭ��ҳ��
		servletMap.put("/", new ToIndexServlet());
		servletMap.put("/index", new ToIndexServlet());
		servletMap.put("/toindex", new ToIndexServlet());
		//ע��cookie servlet
		servletMap.put("/cookie", new CookieServlet());
		servletMap.put("/login.jsp", new LoginPageServlet());
		servletMap.put("/photo/post.do", new LoginPageServlet());
		
		//�����������8080�˿�
		//ѭ������socket����
		//ʹ���̴߳�����������͵�����
		ServerSocket tomcat= new ServerSocket(8080);
		System.out.println("tomcat������������ɣ������˿ڣ�8080");
		boolean running = true;
		while(running) {
			Socket socket = tomcat.accept();
			
			new Thread() {
				public void run() {
					try {
						System.out.println("���յ�����");
						InputStream in = socket.getInputStream();
						OutputStream out = socket.getOutputStream();
						byte[] buffer = new byte[1024];
						int count;
						count = in.read(buffer);
						if(count > 0) {
							//��ӡ������
							String requestText = new String(buffer, 0 , count);
							System.out.println(requestText);
							
							//����������
							HttpServletRequest request = buildRequest(requestText);
							HttpServletResponse response = new HttpServletResponse(out);
							
							//ʹ����Դ��ַ���ֶ�̬����;�̬����
							//ʹ����Դ��ַ��Servlet�����л�ȡServlet����
							String uri = request.getRequestURI();
							Servlet servlet = servletMap.get(uri);
							if(servlet != null) {
								//ʹ��Servlet����̬����
								servlet.service(request, response);
							}else {
								processStaticRequest(request, out);
							}
						}
						socket.close();
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		//Unreachable code ���벻�ɴ�
		tomcat.close();
	}
	
	public void shutdown() {
		
	}
	
	/**
	 * �����������
	 * @param requestText
	 * @return
	 */
	private HttpServletRequest buildRequest(String requestText) {
		return new HttpServletRequest(requestText);
	}
	
	public static void main(String[] args) throws IOException {
		new Tomcat().startup();
	}

	public void processStaticRequest(HttpServletRequest request, OutputStream out) throws IOException {
		//���û���ҵ���Ӧ��Servlet������ô������Ϊ��̬����		
		String webpath = request.getRequestURI();
		String contentType;
		//�����
		int statusCode = 200;
		//��������ļ�·��
		String path = "G:/Դ����ѵ/2020��/7�·�/0727/"+webpath;
		File file = new File(path);
		if(!file.exists()) {
			statusCode = 404;
			path = "G:/Դ����ѵ/2020��/7�·�/0727/photo/404.html";
		}
		if(webpath.endsWith(".js")) {
			contentType = "application/javascript; charset=utf-8";
		}else if(webpath.endsWith(".css")) {
			contentType = "text/css; charset=utf-8";
		}else {
			//Ǳ����ͼƬ���Է���HTML������������Զ�ʶ��
			contentType = "text/html; charset=utf-8";
		}
		//��Ӧͷ��
		out.write(("HTTP/1.1 "+ statusCode +" OK\n").getBytes());
		//��Ӧͷ��
		out.write(("Context-Type: "+ contentType +"\n").getBytes());
		//����CRLF
		out.write("\n".getBytes());
		//ʵ��
		//out.write("<h1>Hello World</h1>".getBytes());
		
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[1024];
		int count;
		while( (count = fis.read(buffer)) > 0) {
			out.write(buffer, 0 , count);
		}
		/**
		 * ���⣺
		 * 	1.ֻ�ܻظ�һ��                   OK
		 * 	2.�ظ���������Զ����
		 * 		1)�������������е���Դ��  /photo/new.html
		 * 		2)��ȡ�ļ����������ʵ����
		 */
		fis.close();
		//����̬����
		//�ж���Դ�Ƿ���ڣ���������ڷ���404
	}
}
