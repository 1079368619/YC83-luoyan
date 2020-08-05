package com.yc.damai.dao;

import java.util.List;

import com.yc.damai.po.DmOrders;

public interface DmOrdersMapper {

	List<DmOrders> selectAll();
	
	DmOrders selectByid(int id);
	
	int insert(DmOrders dp);
	
	int update(DmOrders dp);

	int delete(int id);

}
