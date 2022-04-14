package com.howhow.cms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.howhow.account.service.AccountService;
import com.howhow.account.service.UserAccountDtService;
import com.howhow.cms.dto.LevelAlterApplyDTO;
import com.howhow.cms.service.LevelAlterApplyService;
import com.howhow.entity.LevelAlterApply;
import com.howhow.util.UtilityTool;

@Controller
public class LevelAlterApplyController {
	
	@Autowired
	private LevelAlterApplyService laas;
	
	@Autowired
	private UserAccountDtService uads;
	
    //提交申請單
//	@PostMapping("api/applydata")
//	public boolean addApply(@RequestBody LevelAlterApplyDTO applyDTO) {
//		LevelAlterApply apply = new LevelAlterApply();
//		apply.setApplylevel("Teacher");
//		apply.setApplystatus("未處理");
//		apply.setSystemtime(UtilityTool.getSysTime());
//		apply.setUserAccountDt(uads.findById(applyDTO.getUserid()));
//		
//		laas.insertApply(apply);
//		
//		return true;
//	}
}
