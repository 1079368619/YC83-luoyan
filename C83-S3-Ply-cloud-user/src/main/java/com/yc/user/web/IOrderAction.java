package com.yc.user.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="order")// 网元名称 服务名
public interface IOrderAction {

	@GetMapping("order")// 对应是 action的地址
	public String order();
}
