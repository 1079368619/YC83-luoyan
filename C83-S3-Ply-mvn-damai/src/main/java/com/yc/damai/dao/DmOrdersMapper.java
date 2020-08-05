package com.yc.damai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.yc.damai.po.DmOrders;

public interface DmOrdersMapper {

	List<DmOrders> selectAll();
	
	DmOrders selectByid(int id);
	
	//新增订单
	@Insert("insert into dm_orders values(null, #{total}, now(), #{state}, #{uid}, #{aid})")
	@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
	int insert(DmOrders dos);
	
	int update(DmOrders dos);

	int delete(int id);

}
