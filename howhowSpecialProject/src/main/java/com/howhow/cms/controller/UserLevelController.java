package com.howhow.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.account.service.UserStatusService;
import com.howhow.entity.UserAccountDt;
import com.howhow.entity.UserStatus;

@Controller
public class UserLevelController {
	
	@Autowired
	private UserStatusService uss;
	
	@GetMapping("/userstatus")
	public String guidToUserStatus() {
		return "cms/userstatusmain.html";
	}
	
	// 搜尋指定用戶狀態
	@ResponseBody
	@GetMapping("/userstatusdata")
	public UserStatus findUserStatus(@RequestBody UserAccountDt userDt) {
		return uss.findById(userDt.getUserId());
	}
	
	// 更改指定用戶等級
	@ResponseBody
	@PostMapping("/userstatusdata")
	public UserStatus updateStatus(@RequestBody UserStatus userStatus) {
		return uss.updateUserStatus(userStatus);
	}
}
