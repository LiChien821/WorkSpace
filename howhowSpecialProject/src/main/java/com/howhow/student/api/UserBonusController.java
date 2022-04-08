package com.howhow.student.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.account.service.AccountService;
import com.howhow.entity.UserAccountMt;
import com.howhow.entity.UserBonus;
import com.howhow.shopping.exception.UserNotFoundException;
import com.howhow.student.exception.BonusTypeErrorException;
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
	@GetMapping("/api/addbonusbyid/{id}/{bonus}")
	@ResponseBody
	public UserBonus addBonusById(@PathVariable("id") int id, @PathVariable("bonus") int bonus) throws BonusTypeErrorException, UserNotFoundException {
		
		if(bonus<=0) throw new BonusTypeErrorException();
		
		UserBonus userbonus = bService.findByID(id);
		
		int bonusCount = userbonus.getBonusCount();
		userbonus.setBonusCount(bonusCount+bonus);
		userbonus.setSystemTime(UtilityTool.getSysTime());
		
		UserBonus updateUserBonus = bService.updateUserBonus(userbonus);
		
		return updateUserBonus;
	}
	
	/*
	 * 查看用戶紅利點數
	 * */
	@GetMapping("/api/findbonusbyid/{id}")
	@ResponseBody
	public UserBonus checkBonusById(@PathVariable("id") int id) throws UserNotFoundException {
		
		UserBonus userbonus = bService.findByID(id);
		return userbonus;
	}
	
	/*
	 * 使用紅利點數
	 * */
	@GetMapping("/api/usebonus/{id}/{bonus}")
	@ResponseBody
	public UserBonus useBonus(@PathVariable("id") int id, @PathVariable("bonus") int usebonus) throws UserNotFoundException, BonusTypeErrorException{
		
		if(usebonus<=0) throw new BonusTypeErrorException();
		
		UserBonus user = bService.findByID(id);
		if(user==null) throw new UserNotFoundException();
		int bonusCount = user.getBonusCount();
		int finalbonus = bonusCount-usebonus;
		if(finalbonus<0) throw new BonusTypeErrorException();
		
		user.setBonusCount(finalbonus);
		
		UserBonus updateUserBonus = bService.updateUserBonus(user);
		
		return updateUserBonus;
	}
	
	
}