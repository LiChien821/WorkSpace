package com.howhow.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.account.service.AccountService;
import com.howhow.account.service.UserStatusService;
import com.howhow.entity.UserAccountDt;
import com.howhow.entity.UserStatus;
import com.howhow.util.UtilityTool;

@Controller
public class CommonController {

	@Autowired
	private AccountService aService;
	
	@Autowired
	private UserStatusService usService;

	@GetMapping("/api/navbar-test")
	public String processNavbar() {
		return "universal-navbar-origin";
	}
	
	@GetMapping("/api/footer-test")
	public String processFooter() {
		return "universal-footer";
	}
	
	@GetMapping("/api/searchCourseInfo/{query}")
	public String searchCourseInfo (@PathVariable("query") String query) {
		return "searchResult";
	}
	
	@GetMapping("/api/checkLoginStatus")
	@ResponseBody
	public boolean checkLoginStatus() {
		UserAccountDt accountdt = aService.findByEmail(UtilityTool.getTokenEmail());
		if (accountdt == null) {
			return false;
		};
		
		return true;
	}
	
}
