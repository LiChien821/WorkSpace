package com.howhow.cms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.account.service.AccountService;
import com.howhow.account.service.UserAccountDtService;
import com.howhow.cms.dto.LevelAlterApplyDTO;
import com.howhow.cms.service.LevelAlterApplyService;
import com.howhow.entity.LevelAlterApply;
import com.howhow.entity.UserAccountDt;
import com.howhow.shopping.exception.UserNotFoundException;
import com.howhow.util.UtilityTool;

@Controller
public class LevelAlterApplyController {
	
	@Autowired
	private LevelAlterApplyService laas;
	
	@Autowired
	private AccountService as;
	
    //提交申請單
	@PostMapping("/api/applydata")
	@ResponseBody
	public LevelAlterApplyDTO addApply() throws UserNotFoundException {
		UserAccountDt userDt = as.findByEmail(UtilityTool.getTokenEmail());
		if (userDt == null) {
			throw new UserNotFoundException();
		}
		
		LevelAlterApplyDTO applyDTO = new LevelAlterApplyDTO();
		if(laas.isApplied(userDt.getUserId())) {
			applyDTO.setAlreadyApplied(true);
			return applyDTO;
		}
		
		LevelAlterApply apply = new LevelAlterApply(userDt);
		laas.insertApply(apply);
		
		applyDTO.setAlreadyApplied(false);
		
		return applyDTO;
	}
}
