package com.howhow.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.account.service.AccountService;
import com.howhow.entity.UserAccountMt;
import com.howhow.entity.UserBonus;
import com.howhow.student.service.UserBonusService;
import com.howhow.util.UtilityTool;

@Controller
public class UserBonusController {

	@Autowired
	UserBonusService bService;
	
	@Autowired
	AccountService accService;
	
	/*
	 * 更新用戶紅利點數
	 * */
	@GetMapping("/addbonusbyid/{id}/{bonus}")
	@ResponseBody
	public UserBonus updateBonusById(@PathVariable("id") int id, @PathVariable("bonus") int bonus) {
		
		UserBonus userbonus = bService.findByID(id);
		if(userbonus==null) {
			UserAccountMt mt = accService.findByID(id);
			
			UserBonus newbonus = new UserBonus();
			newbonus.setBonusCount(bonus);
			newbonus.setSystemTime(UtilityTool.getSysTime());
			
			mt.setUserBonus(userbonus);
			newbonus.setUserID(mt);
			
			UserBonus createBonus = bService.updateUserBonus(newbonus);
			
			return createBonus;
		}
		
		int bonusCount = userbonus.getBonusCount();
		userbonus.setBonusCount(bonusCount+bonus);
		userbonus.setSystemTime(UtilityTool.getSysTime());
		
		UserBonus updateUserBonus = bService.updateUserBonus(userbonus);
		
		return updateUserBonus;
	}
	
	/*
	 * 查看用戶紅利點數
	 * */
	@GetMapping("/findbonusbyid/{id}")
	@ResponseBody
	public UserBonus checkBonusById(@PathVariable("id") int id) {
		
		UserBonus userbonus = bService.findByID(id);
		return userbonus;
	}
	
	/*
	 * 使用紅利點數
	 * */
	@GetMapping("/usebonus/{id}/{bonus}")
	@ResponseBody
	public UserBonus useBonus(@PathVariable("id") int id, @PathVariable("bonus") int bonus) {
		
		UserBonus user = bService.findByID(id);
		if(user==null) return null;
		int bonusCount = user.getBonusCount();
		int finalbonus = bonusCount-bonus;
		if(finalbonus<0) return null;
		
		user.setBonusCount(finalbonus);
		
		UserBonus updateUserBonus = bService.updateUserBonus(user);
		
		return updateUserBonus;
	}
	
	
}