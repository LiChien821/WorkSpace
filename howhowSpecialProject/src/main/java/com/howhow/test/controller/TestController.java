package com.howhow.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.entity.UserBonus;
import com.howhow.test.service.TestService;
import com.howhow.websecurity.AccountUserDetails;

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
	
	@GetMapping("/courses")
	public String ac(Model m) {
		m.addAttribute("pageNo", 1);
		return "shopping/browse";
	}
	
	@GetMapping("/product")
	public String unit(@AuthenticationPrincipal AccountUserDetails loggedAccount,	@RequestParam("id") int id ,Model m) {
		m.addAttribute("courseid", id);
		int userid=-1;
		if(loggedAccount!=null) {
			userid = loggedAccount.getLoggedAccount().getUserId();
		}
		m.addAttribute("userid",userid);
		
		return "shopping/product";
	}
	
	
}
