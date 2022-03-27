package com.howhow.account.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.howhow.account.service.UserAccountMtService;
import com.howhow.entity.UserAccountMt;


@RestController
public class UserAccountController {

	@Autowired
	private UserAccountMtService userMtService;

	@GetMapping("/insert")
	public UserAccountMt processInsertAction() {

		UserAccountMt mt1 = new UserAccountMt();
		mt1.setAccount("kyoko");
		mt1.setPassword("1234");
		mt1.setSystemTime(new java.util.Date());
		return userMtService.insert(mt1);

	}

	@GetMapping("/query.controller")
	public UserAccountMt processQueryByIdAction() {
		
		return userMtService.findById(1);
		
	}
	@GetMapping("/querybyacc")
	public List<UserAccountMt> processFindByAccount() {
		return userMtService.findByAccount("howhow91");
		
	}
	

}
