package com.yc.springmvc.action;

import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import com.yc.damai.bean.DmProduct;

@RestController
public class ProductAction {

	public Map<String, Object> query(DmProduct dp,Map<String, Object> m){
		return m;
	}
}
