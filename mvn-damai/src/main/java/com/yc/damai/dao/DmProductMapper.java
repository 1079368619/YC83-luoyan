package com.yc.damai.dao;

import java.util.List;

import com.yc.damai.po.DmProduct;

public interface DmProductMapper {

	List<DmProduct> selectAll();
	
	DmProduct selectById(int id);
	
	int insert(DmProduct dp);
	
	int update(DmProduct dp);

	int delete(int id);

}
