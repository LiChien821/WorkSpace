package com.howhow.util.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.account.service.AccountService;
import com.howhow.entity.UserAccountDt;
import com.howhow.util.UtilityTool;

@Controller
public class UtilController {

	@Autowired
	AccountService service;
	
	
	/*
	 * 若無login則回傳false，若有login則回傳true
	 * */
	@GetMapping("/api/checklogin")
	@ResponseBody
	public Integer checkLogin() {
		UserAccountDt accountDetail = service.findByEmail(UtilityTool.getTokenEmail());
		if(accountDetail==null) return null;
		int userId = accountDetail.getUserId();
		return userId;
	}
}
