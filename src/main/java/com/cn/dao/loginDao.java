package com.cn.dao;

import java.util.List;

import com.cn.pojo.LoginBean;


public interface loginDao {
	public List<LoginBean> LoginDao(LoginBean loginBean)throws Exception;
}
