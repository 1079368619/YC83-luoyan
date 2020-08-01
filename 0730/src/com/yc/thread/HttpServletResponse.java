package com.yc.thread;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpServletResponse {

	//ͨ��Socket��ȡ�������
	private OutputStream out;
	private int status;
	private String msg;
	//��Դ�� �ַ���������� ��������ݱ��浽һ���ַ�����
	private CharArrayWriter caw = new CharArrayWriter();
	//������
	private PrintWriter pw = new PrintWriter(caw);
	//���ͷ���ֵ�Ե�map����
	private Map<String, String> headerMap = new HashMap<>();
	//
	private List<Cookie> cookieList = new ArrayList<>();

	public HttpServletResponse(OutputStream out) {
		this.out = out;
	}

	/**
	 * ���ý����ͽ������Ϣ
	 * @param status
	 * @param msg
	 */
	public void setStatus(int status, String msg) {
		if(this.status == 0) {
			this.status = status;
			this.msg = msg;
		}
	}

	/**
	 * ����Ӧ�������͸������
	 * @throws IOException
	 */
	public void flushBuffer() throws IOException {
		// ��Ӧͷ��
		out.write(("HTTP/1.1 " + status + " OK\n").getBytes());
		// ��Ӧͷ��
		out.write(("Context-Type: text/html; charset=utf-8\n").getBytes());
		//
		for(Entry<String, String> entry : headerMap.entrySet()) {
			out.write((entry.getKey() + ": " + entry.getValue() + "\n").getBytes());
		}
		//
		for(Iterator<Cookie> iterator = cookieList.iterator();iterator.hasNext();) {
			Cookie cookie = iterator.next();
			out.write(cookie.toString().getBytes());
		}
		// ����CRLF
		out.write("\n".getBytes());
		// ʵ��
		out.write(caw.toString().getBytes());
	}

	/**
	 * ��ȡ��Ӧ�����(��ӡ��)  ��ʱ����servlet���������
	 * @return
	 */
	public PrintWriter getWriter() {
		return pw;
	}

	/**
	 * 
	 * @param uri
	 */
	public void sendRedirect(String uri) {
		//
		setStatus(301, "Redirect");
		//
		headerMap.put("Location", uri);
	}

	public void addCookie(Cookie cookie) {
		cookieList.add(cookie);
	}

	
}
