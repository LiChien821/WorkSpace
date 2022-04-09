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
		int userid=-1;
		 UserAccountDt accountDetail = service.findByEmail(UtilityTool.getTokenEmail());
		 if(accountDetail!= null) userid = accountDetail.getUserId();
		m.addAttribute("userid",userid);
		m.addAttribute("pageNo", 1);

		return "shopping/browse";
	}
	
	@GetMapping("/courses/{searching}")
	public String ac(Model m, @PathVariable("searching") String searching) {
		int userid=-1;
		 UserAccountDt accountDetail = service.findByEmail(UtilityTool.getTokenEmail());
		 if(accountDetail!= null) userid = accountDetail.getUserId();
		m.addAttribute("searching", searching);
		m.addAttribute("userid",userid);
		m.addAttribute("pageNo", 1);

		return "shopping/browse";
	}
	
	
	@GetMapping("/product")
	public String unit(@RequestParam("id") int id ,Model m) throws CourseNotFoundException {
		
		if(cService.findByID(id)==null) throw new CourseNotFoundException();
		
		int userid=-1;
		UserAccountDt accountDetail = service.findByEmail(UtilityTool.getTokenEmail());
		if(accountDetail!= null) userid = accountDetail.getUserId();
		
		m.addAttribute("courseid", id);
		m.addAttribute("userid",userid);
		m.addAttribute("pageNo", 1);
		
		return "shopping/product";
	}
	
	@GetMapping("/myshop")
	public String enterMyShop(Model m) {
		int userid=-1;
		UserAccountDt accountDetail = service.findByEmail(UtilityTool.getTokenEmail());
		if(accountDetail!= null) userid = accountDetail.getUserId();
		
		m.addAttribute("userid",userid);
		
		return "shopping/myshoppingpage";
	}
	
	@GetMapping("/mycourse")
	public String enterMyCourse(Model m) {
		int userid=-1;
		UserAccountDt accountDetail = service.findByEmail(UtilityTool.getTokenEmail());
		if(accountDetail!= null) userid = accountDetail.getUserId();
		
		m.addAttribute("userid",userid);
		
		return "shopping/mypurchasedpage";
	}
	
}
