package com.cn.service.sierviceImp;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.dao.ssmDao;
import com.cn.pojo.ssmBean;
import com.cn.service.ssmService;

import net.sf.json.JSONObject;


@Service
public class ssmServiceImp implements ssmService {

	@Autowired
	private ssmDao ssmdao;

	@Override
	public String insertUerService(String UserData) {
		JSONObject jsono = new JSONObject();
		JSONObject jsonobject =	JSONObject.fromObject(UserData);
		jsonobject = (JSONObject) jsonobject.get("UserData");
		Map map = (Map)jsonobject;
		ssmBean ssmbean = new ssmBean();
		int id = Integer.parseInt(map.get("UserId").toString());
		int start = Integer.parseInt(map.get("UserStart").toString());
		ssmbean.setNickname(map.get("UserName").toString());
		ssmbean.setId(id);
		ssmbean.setState(start);
		int count = ssmdao.insertUserDao(ssmbean);
		String Result = "";
		if(count > 0){
			Result = "secusess";
		}else{
			Result = "Error";
		}
		jsono.put("Result", Result);
		return jsono.toString();
	}

	@Override
	public String selectUser(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		JSONObject selectData = new JSONObject();
		 List<ssmBean> list = ssmdao.selectDao(map);
		 selectData.put("UserData", list);
		return selectData.toString(); 
	}
	
	
	@Override
	public String pagecont() {
		// TODO Auto-generated method stub
		int pagecont = ssmdao.pageCount();
		String count = String.valueOf(pagecont);
		return count;
	}

	@Override
	public String UpssmBean(Map<String,Object> maps) {
		// TODO Auto-generated method stub
		int UpReuslt = 0;
		try {
			int UserId = Integer.parseInt(maps.get("UserId").toString());
			HashMap<String,Object> p = (HashMap<String, Object>) maps.get("map");
			p.put("UserId", UserId);
			UpReuslt = ssmdao.UpdateUserDao(p);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(UpReuslt > 0){
			return "success";
		}else{
			return "Error";
		}
	}

	@Override
	public String DeletUser(int Id) {
		int UpReuslt = ssmdao.DeletUserDao(Id);
		if(UpReuslt > 0){
			return "success";
		}else{
			return "Error";
		}
	}
	
	
}
