package com.yc.springmvc.web;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yc.damai.bean.DmUser;

@RestController
@RequestMapping("user")
@SessionAttributes(names="loginedUser", types = Date.class)
public class TestAction {

	
	@RequestMapping("?/add")
	public String add() {
		return "add";
	}
	
	@RequestMapping("*/del")
	public String del() {
		return "del";
	}
	
	@RequestMapping("**/mod")
	public String mod() {
		return "mod";
	}
	
	@RequestMapping("**/head")
	public String head(@RequestHeader() String accept,
			@RequestHeader(value="Connecton",required=true) String conn) {
		return accept + "<br>" + conn;
	}
	
	@RequestMapping("**/cookie")
	public String cookie(@CookieValue String user,
			@CookieValue int age,@RequestHeader String cookie) {
		return user + "<br>" + age + "<br>" +cookie;
	}
	
	@RequestMapping("**/servlet")
	public String servlet(HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			InputStream in,
			OutputStream out) {
		return ""+request + "<br>" + response 
				+ "<br>" +session + "<br>" + in + "<br>" + out;
	}
	
	@RequestMapping("{user}/{pwd}/login")
	public String login(@PathVariable("user") String user,@PathVariable String pwd) {
		return user + "<br>" + pwd;
	}
	
	@RequestMapping("{user}/{pwd}/login.do")
	public String logindo(@PathVariable("user") String user,
			@PathVariable String pwd,
			Model model,
			HttpSession session) {
		DmUser du = new DmUser();
		du.setEname(user);
		du.setPassword(pwd);
		
		session.setAttribute("loginedUser", du);
		session.setAttribute("now", new Date());
		//session.setAttribute("age", 100);
		
		/**
		model.addAttribute("loginedUser", du);
		model.addAttribute("now", new Date());
		model.addAttribute("age", 100);*/
		return du.toString();
	}
	
	@RequestMapping("testlogin")
	public String testlogin(@SessionAttribute("loginedUser") DmUser du,
			@SessionAttribute("now") Date date,
			@SessionAttribute(value="age",required=false) Integer age) {
		return du + "<br>" + date + "<br>" + age;
	}
}
