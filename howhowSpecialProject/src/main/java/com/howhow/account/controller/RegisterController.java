package com.howhow.account.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.howhow.account.service.AccountService;
import com.howhow.entity.UserAccountDt;
import com.howhow.entity.UserAccountMt;
import com.howhow.entity.UserStatus;
import com.howhow.util.UtilityTool;

@Controller
public class RegisterController {

	@Autowired
	private AccountService service;

	@GetMapping("/register")
	public String registerPage(Model model) {
		UserAccountMt useraccountmt = new UserAccountMt();
		UserAccountDt useraccountdt = new UserAccountDt();
		UserStatus userstatus = new UserStatus();
		model.addAttribute("Account", useraccountmt);
		model.addAttribute("AccountDetail", useraccountdt);
		return "register";
	}

	@PostMapping("/createUser")
	public String createUser(@ModelAttribute("Account") UserAccountMt acc, @ModelAttribute("AccountDetail") UserAccountDt acd,
			Model model, HttpServletRequest request) {
		try {
			service.createUser(acc, acd);
			UtilityTool.sendVerificationEmail(acc, request);
			
			
			service.save(acc);
			model.addAttribute("text", "註冊完成 請收取認證信  完成帳號啟動 ");
			return "index";
		} catch (Exception ex) {
			ex.printStackTrace();
			UserAccountMt useraccountmt = new UserAccountMt();
			UserAccountDt useraccountdt = new UserAccountDt();
			model.addAttribute("Account", useraccountmt);
			model.addAttribute("AccountDetail", useraccountdt);
			model.addAttribute("text", "帳號或信箱已被註冊 請再試一次");
			return "register";
		}
	}

	@GetMapping("/verify")
	public String verfy(@RequestParam(name = "code") String code, @RequestParam(name = "email") String email,
			Model model) {
		service.activeAccount(code, email);

		String text = " verify  completed  your account is avaliable ";
		model.addAttribute("text", text);
		return "index";
	}

}
