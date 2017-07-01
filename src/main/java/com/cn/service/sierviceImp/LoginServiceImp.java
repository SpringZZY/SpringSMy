package com.cn.service.sierviceImp;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.dao.loginDao;
import com.cn.pojo.LoginBean;
import com.cn.service.LoginService;


@Service
public class LoginServiceImp implements LoginService {

	@Autowired
	loginDao loginDao;
	
	@Override
	public String LoginSelect(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		String Result = "";
		List<LoginBean> listResult = null;
		try {
			LoginBean loginBean = new LoginBean();
			loginBean.setPassword(map.get("password").toString());
			loginBean.setWdName(map.get("wdName").toString());
			listResult = loginDao.LoginDao(loginBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(listResult != null && listResult.size()>0){
			Result = "index";
		}else{
			Result = "账户密码错误！";
		}
		return Result;
	}
	

	public static int fun(double a,double b){
        return (int)Math.ceil(a/b);
    }
    
    public static void main(String[] args) {
        int result = fun(10.0,9.0);
        System.out.println(result);
    }

}
