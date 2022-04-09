package com.howhow.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.howhow.account.service.AccountService;
import com.howhow.entity.UserAccountDt;
import com.howhow.shopping.exception.CourseNotFoundException;
import com.howhow.shopping.service.CourseBasicService;
import com.howhow.util.UtilityTool;

@Controller
public class ShoppingController {
	
	@Autowired
	AccountService service;
	
	@Autowired
	CourseBasicService cService;
	
	
	@GetMapping("/courses")
	public String browseCourses(Model m, @RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "category", defaultValue = "") String category) {
		int userid=-1;
		UserAccountDt accountDetail = service.findByEmail(UtilityTool.getTokenEmail());
		if(accountDetail!= null) userid = accountDetail.getUserId();
		m.addAttribute("categorying", category);
		m.addAttribute("searching", search);
		m.addAttribute("userid",userid);
		m.addAttribute("pageNo", 1);

		return "shopping/browse";
	}
	
	
	@GetMapping("/product")
	public String browseProduct(@RequestParam("id") int id ,Model m) throws CourseNotFoundException {
		
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
