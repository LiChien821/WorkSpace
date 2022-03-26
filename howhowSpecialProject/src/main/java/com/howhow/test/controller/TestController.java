package com.howhow.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.entity.UserBonus;
import com.howhow.test.service.TestService;

@Controller
public class TestController {
	
	@Autowired
	TestService tService;
	
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
	
//	@GetMapping("/login")
//	public String login() {
//		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
//		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//			return "login";
//		}
//		return "redirect:/";
//	}
//	
//	
	
}
