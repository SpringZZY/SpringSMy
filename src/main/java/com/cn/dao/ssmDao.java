package com.cn.dao;

import java.util.HashMap;
import java.util.List;

import com.cn.pojo.ssmBean;


public interface ssmDao {
	//新增User信息
	public int insertUserDao(ssmBean ssmbean);
	
	//查询User信息
	public List<ssmBean> selectDao(HashMap<String, Object> map);
	
	//分页总数计算以及条数
	public int pageCount();
	
	//Update修改
	public int UpdateUserDao(HashMap<String, Object> map);
	
	//Delete修改
	public int DeletUserDao(int Id);
}
