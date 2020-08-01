package com.yc.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpServletRequest {

	private String method;
	private String requestUri;
	private String protocol;
	//���ͷ���ֵ�Ե�map����
	private Map<String, String> headerMap = new HashMap<>();
	//��Ų����ļ���
	private Map<String, String> paramsMap = new HashMap<>();
	
	public HttpServletRequest(String requestText) {
		//��ɶ������ĵĽ���
		String[] lines = requestText.split("\\n");
		String[] items = lines[0].split("\\s");
		method = items[0];
		requestUri = items[1];
		protocol = items[2];
		
		int index = items[1].indexOf("?");
		if(index != -1) {
			//��������
			requestUri = items[1].substring(0, index);
			String paramString = items[1].substring(index + 1);
			String[] params = paramString.split("&");
			for(int i=1;i<params.length;i++) {
				String[] nv = params[i].split("=");
				if(nv.length == 1) {
					paramsMap.put(nv[0], "");
				}else if(nv.length > 1){
					paramsMap.put(nv[0], nv[1]);
				}
			}
		}
		for(int i=1;i<lines.length;i++) {
			lines[i] = lines[i].trim();
			if(lines[i].isEmpty()) {
				break;
			}
			items = lines[i].split(":");
			headerMap.put(items[0], items[1].trim());
		}
	}

	/**
	 * ��ȡ����ķ�����
	 * @return
	 */
	public String getMethod() {
		return method;
	}
	
	/**
	 * ��ȡ������Դ·��
	 * @return
	 */
	public String getRequestURI() {
		return requestUri;
	}
	
	/**
	 * ��ȡЭ��汾
	 * @return
	 */
	public String getProtocol() {
		return protocol;
	}
	
	/**
	 * ��ȡͷ��ֵ  ��ֵ��
	 * @return
	 */
	public String getHeader(String name) {
		return headerMap.get(name);
	}
	
	/**
	 * ��ȡ�������
	 * @return
	 */
	public String getParameter(String name) {
		return paramsMap.get(name);
	}
	
	/**
	 * ��ȡ����cookie����
	 * @return
	 */
	public Cookie[] getCookies() {
		String cookieString = headerMap.get("Cookie");
		if(cookieString == null) {
			return null;
		}else{
			List<Cookie> cookieList = new ArrayList<>();
			String[] sCookies = cookieString.split(";\\s*");
			for(int i=0;i<sCookies.length;i++) {
				String[] nv = sCookies[i].split("=");
				cookieList.add(new Cookie(nv[0], nv[1]));
			}
			return cookieList.toArray(new Cookie[0]);
		}
		
	}
}
