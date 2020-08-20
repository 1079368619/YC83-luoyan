package com.yc.damai.dao;

import java.util.List;

import com.yc.damai.po.DmOrderitem;

public interface DmOrderitemMapper {

	List<DmOrderitem> selectAll();
	
	DmOrderitem selectById(int id);
	
	//
	int insert(DmOrderitem doi);
	
	int update(DmOrderitem doi);

	int delete(int id);

}
