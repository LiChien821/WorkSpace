package com.howhow.cms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.howhow.account.service.AccountService;
import com.howhow.cms.service.LevelAlterApplyService;
import com.howhow.entity.LevelAlterApply;
import com.howhow.util.UtilityTool;

@Controller
public class LevelAlterApplyController {
	
	@Autowired
	private LevelAlterApplyService laas;
	
	@Autowired
	private AccountService service;
	
    //提交申請單
	@PostMapping("api/applydata")
	public void insertApply(@RequestBody LevelAlterApply apply) {
		apply.setApplylevel("Teacher");
		apply.setApplystatus("未處理");
		apply.setUserAccountDt(service.findByEmail(UtilityTool.getTokenEmail()));
		apply.setSystemtime(UtilityTool.getSysTime());
		laas.insertApply(apply);
	}
}
