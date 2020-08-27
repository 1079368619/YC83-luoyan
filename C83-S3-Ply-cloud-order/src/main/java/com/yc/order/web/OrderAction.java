package com.yc.order.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderAction {

	@Resource
	private RestTemplate restTemplate;
	
	@Resource
	IUserAction iua;
	
	@GetMapping("user")
	public String order(HttpServletRequest req) {
		return String.format("server:user ; ip:%s ; port:%s", 
							req.getLocalAddr(),
							req.getLocalPort());
	}
	
	/**
	 * 	127.0.0.1:8002 ==> 订单地址
	 * 	浏览器测试地址: http://127.0.0.1:8002/user
	 * 				http://order/user  
	 */
	@GetMapping("order")
	public String user() {
		//String url="http://127.0.0.1:8002/order";
		String url = "http://user/user";// 系统内部的远程调用地址
		String res = restTemplate.getForObject(url, String.class);
		// Map ==> 实体类
		// map.get("id") ==> obj.getId();
		return res;
	}
	
	@GetMapping("user1")
	public String user1() {
		// 声明式远程服务调用
		return iua.user();
	}
}
