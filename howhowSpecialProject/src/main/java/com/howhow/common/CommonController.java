package com.howhow.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.account.service.AccountService;
import com.howhow.account.service.UserStatusService;
import com.howhow.entity.CourseBasic;
import com.howhow.entity.PurchasedCourse;
import com.howhow.entity.UserAccountDt;
import com.howhow.entity.UserStatus;
import com.howhow.shopping.exception.UserNotFoundException;
import com.howhow.shopping.service.CourseBasicService;
import com.howhow.util.UtilityTool;

@Controller
public class CommonController {

	@Autowired
	private AccountService aService;
	
	@Autowired
	private UserStatusService usService;
	
	@Autowired
	CourseBasicService cService;

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
	public HashMap<String, Object> checkLoginStatus() throws UserNotFoundException {
		UserAccountDt accountdt = aService.findByEmail(UtilityTool.getTokenEmail());
		HashMap<String, Object> output = new HashMap<String, Object>();
		if (accountdt == null) {
			output.put("userId", -1);
			output.put("isLogged", false);
			return output;
		}
		int userid = accountdt.getUserId();
		output.put("userId", userid);
		output.put("isLogged", true);
		return output;
	}
	
	@GetMapping("/api/findAllPurchasedCoursesByUserid/{userid}")
	@ResponseBody
	public List<CourseBasic> findAllPurchasedCoursesByUserid (@PathVariable("userid") int userid) {
		List<CourseBasic> list = cService.findAllbyUserID(userid);
		return list;
	}
	
	
	
}
