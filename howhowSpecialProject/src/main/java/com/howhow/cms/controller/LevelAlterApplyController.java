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
import com.howhow.entity.UserAccountDt;
import com.howhow.util.UtilityTool;

@Controller
public class LevelAlterApplyController {
	
	@Autowired
	private LevelAlterApplyService laas;
	
	@Autowired
	private AccountService as;
	
    //提交申請單
	@PostMapping("api/applydata")
	public LevelAlterApplyDTO addApply() {
		UserAccountDt userDt = as.findByEmail(UtilityTool.getTokenEmail());
		LevelAlterApplyDTO applyDTO = new LevelAlterApplyDTO();
		
		if(laas.checkRepeat(userDt.getUserId())) {
			applyDTO.setAlreadyApplied(true);
			return applyDTO;
		}
		
		LevelAlterApply apply = new LevelAlterApply(userDt);
		laas.insertApply(apply);
		
		applyDTO.setAlreadyApplied(false);
		
		return applyDTO;
	}
}
