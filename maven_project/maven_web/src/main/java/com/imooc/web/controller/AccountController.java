package com.imooc.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.framework.base.controller.BaseController;
import com.imooc.web.service.AccountService;

@Controller
@RequestMapping("account/*")
public class AccountController extends BaseController{
	
	@Autowired
	private AccountService accountService;
   
	@RequestMapping("test/{name}/{money}")
	public @ResponseBody Map<String, Object> accountTest(@PathVariable String name,@PathVariable Double money){
		Map<String,Object> res=new HashMap<>();
		String result=this.accountService.changeMoney(money, name);
		if("success".equals(result)){
			res.put("state", "success");
			res.put("msg", "操作成功");
		}else{
			res.put("state", "fail");
			res.put("msg", result);
		}
		return res;
	}
}
