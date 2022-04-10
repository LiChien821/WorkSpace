package com.howhow.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.account.service.AccountService;
import com.howhow.entity.UserAccountDt;
import com.howhow.entity.UserBonus;
import com.howhow.shopping.exception.CourseNotFoundException;
import com.howhow.shopping.service.CourseBasicService;
import com.howhow.test.service.TestService;
import com.howhow.util.UtilityTool;

@Controller
public class TestController {
	
	@Autowired
	AccountService service;
	
	@Autowired
	TestService tService;
	
	@Autowired
	CourseBasicService cService;
	
	
	@GetMapping("/test")
	public String processTest() {
		return "test";
	}
	
	@GetMapping("/querytest")
	@ResponseBody
	public UserBonus queryUserBonusByAccount(@RequestParam("account") String account) {
		UserBonus userBonus = tService.findbyAccount(account);
		return userBonus;
	}
	

	
	

	
}
