package com.cn.service;

import java.util.HashMap;
import java.util.Map;


public interface ssmService {
	public String insertUerService(String UserData);
	//查询
	public String selectUser(HashMap<String, Object> map);
	
	//分页统计页数，总数
	public String pagecont();
	
	//Update修改
	public String UpssmBean(Map<String,Object> map);
	

	//Delet
	public String DeletUser(int Id);
}
