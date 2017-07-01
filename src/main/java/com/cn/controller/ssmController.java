package com.cn.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.service.LoginService;
import com.cn.service.RASPublicPassWord;
import com.cn.service.ssmService;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/")
public class ssmController {
	
	@Autowired
	private ssmService ssmService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private RASPublicPassWord rASPublicPassWord; 
	
	@RequestMapping(value ="/loginText")
	public String  login(HttpServletRequest request){//HttpServletRequest request,
			//Map<String, Object> map = new HashMap<String,Object>();
			//map.put("pd", "公钥");
			//model.addAttribute("publicpassword","公钥");
			//System.out.println(map);
			//return new ModelAndView("login",map);
		//model.addAttribute("pd", "pd");
		return "login";
	}
	
	
	@RequestMapping(value ="/index")
	public String index(){
			return "index";
	}
	
	@RequestMapping(value ="/login",method = RequestMethod.POST)
	@ResponseBody
	public String index(String wdName,String password,Model model){
		System.out.println("--------------开始");
		HashMap<String,Object> map = new HashMap<>();
		map.put("wdName", wdName);
		map.put("password", password);
		String data = loginService.LoginSelect(map);
		System.out.println(data);
		System.out.println("--------------结束");
		if(data.equalsIgnoreCase("index")){
			//返回值给前端
			model.addAttribute("Result", "success");
			return "";
		}else{
			return "login";
		}
	}
	
	@RequestMapping(value = "/Add",method = RequestMethod.POST)
	@ResponseBody
	public String UserAdd( @RequestBody String UserData,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession){
		String Data = UserData;
		String data = ssmService.insertUerService(Data);
		return data;
	}
	
	@RequestMapping(value = "/selectUser",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String SelectUser(@RequestBody Map<String,Object> maps,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo",maps.get("pageNo"));
		map.put("pageSize", maps.get("pageSize"));//获取查询的起始页、例如 start 1 (pageSize*(pageNo-1)) end 10 (pageSize*pageNo)--》(StartPage),
		int No = Integer.parseInt(maps.get("pageNo").toString());
		int Size = Integer.parseInt(maps.get("pageSize").toString());
		if(No <= 1 ){
			map.put("StartPage",1);
			map.put("EndPage", No*Size);
		}else{
			map.put("StartPage",(Size*( No-1)+1));
			map.put("EndPage", No*Size);
		}											 				
		String data = ssmService.selectUser(map);
		return data;
	}
	
	@RequestMapping(value = "/pageCount",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String PageCont(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession){
		String data = ssmService.pagecont();
		JSONObject JSON = new JSONObject();
		int count = Integer.parseInt(data);
		JSON.put("success", count);
		return JSON.toString();
	}
	
	
	@RequestMapping(value = "/UpdateUser",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String UpdataUser(@RequestBody Map<String,Object> map,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession){
		String data = ssmService.UpssmBean(map);
		JSONObject JSONS = new JSONObject();
		if("success".equals(data)){
			JSONS.put("UserData", data);
		}else{
			JSONS.put("UserData", data);
		}
		return JSONS.toString();
	}
	
	@RequestMapping(value = "/DeleteId",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String DeleteUser(@RequestBody Map<String,Object> map,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession){
		String data = ssmService.DeletUser(Integer.parseInt(map.get("UserId").toString()));
		JSONObject JSONS = new JSONObject();
		if("success".equals(data)){
			JSONS.put("UserData", data);
		}else{
			JSONS.put("UserData", data);
		}
		return JSONS.toString();
	}
	
	@RequestMapping(value ="/RASPublicPw",method = RequestMethod.POST)
	@ResponseBody
	public String RASPassWord(String parameter,String parameterTow,Model model){
		System.out.println("--------------获取公钥----------------");
		HashMap<String,Object> map = new HashMap<>();
		String data = rASPublicPassWord.RASpassword(parameter);
		System.out.println(data);
		System.out.println("--------------获取结束----------------");
		if(data.equalsIgnoreCase("index")){
			//返回值给前端
			model.addAttribute("Result", "success");
			return "";
		}else{
			return "login";
		}
	}
}
