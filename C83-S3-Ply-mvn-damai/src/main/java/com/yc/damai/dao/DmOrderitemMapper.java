package com.yc.damai.dao;

import java.util.List;

import com.yc.damai.po.DmOrderitem;

public interface DmOrderitemMapper {

	List<DmOrderitem> selectAll();
	
	DmOrderitem selectById(int id);
	
	int insert(DmOrderitem dp);
	
	int update(DmOrderitem dp);

	int delete(int id);

}
