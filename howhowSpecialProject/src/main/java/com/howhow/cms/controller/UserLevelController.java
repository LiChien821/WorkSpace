package com.howhow.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.howhow.account.service.UserStatusService;
import com.howhow.cms.service.LevelAlterApplyService;
import com.howhow.entity.LevelAlterApply;
import com.howhow.entity.UserAccountDt;
import com.howhow.entity.UserStatus;

@Controller
@RequestMapping("/cms")
public class UserLevelController {
	
	@Autowired
	private UserStatusService uss;
	
	@Autowired
	private LevelAlterApplyService laas;
	
	@GetMapping("/userstatus")
	public String guidToUserStatus() {
		return "cms/userstatusmain.html";
	}
	
	// 搜尋指定用戶狀態
	@ResponseBody
	@GetMapping("/userstatusdata/{userid}")
	public UserStatus findUserStatus(@PathVariable("userid") int userID) {
		return uss.findById(userID);
	}
	
	// 搜尋申請狀態變動用戶
	@ResponseBody
	@GetMapping("/applydata")
	public List<LevelAlterApply> searchUser(){
		return laas.findUnprocessApply("未處理");
	}
	
	@ResponseBody
	@PutMapping("/applydata")
	public List<LevelAlterApply> updateApply(@RequestBody LevelAlterApply apply){
		UserAccountDt userDtObj = laas.findById(apply.getApplyid()).getUserAccountDt();
		apply.setUserAccountDt(userDtObj);
		laas.updateApply(apply);
		return searchUser();
	}
	
	// 更改指定用戶等級
	@ResponseBody
	@PutMapping("/userstatusdata")
	public UserStatus updateStatus(@RequestBody UserStatus userStatus) {
		return uss.updateUserStatus(userStatus);
	}
}
