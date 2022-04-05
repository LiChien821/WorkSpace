package com.howhow.account.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.howhow.account.service.AccountService;
import com.howhow.entity.UserAccountDt;
import com.howhow.entity.UserAccountMt;
import com.howhow.util.UtilityTool;
import com.howhow.websecurity.AccountUserDetails;

@Controller
public class AccountController {

	@Autowired
	private AccountService service;
	
	@Autowired
	private PasswordEncoder bcryptoEncoder;

	@GetMapping("/index")
	public String processTest() {
		return "index";
	}


	@GetMapping("/login")
	public String login() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		return "redirect:/home";
	}

	@GetMapping("/home")
	public String home( Model model,@RequestParam(required=false,name="password") String pwd) {

		  UserAccountDt udt = service.findByEmail(UtilityTool.getTokenEmail());
		 
		  UserAccountMt account = udt.getUserAccountMt();
		  model.addAttribute("account", account);
		  
		  System.out.println(pwd);

		  if(pwd != null) {
			  account.setPassword(bcryptoEncoder.encode(pwd));
			  System.out.println("已嘗試");
		  }
		 
		  
	


		return "main";
	}
	@GetMapping("/editpwd")
	public String editpwd( Model model) {
		
		 UserAccountDt accountDetail = service.findByEmail(UtilityTool.getTokenEmail());
			
			UserAccountMt account = accountDetail.getUserAccountMt();
			
			model.addAttribute("Account", account);
		
			
			
			return "editpwd";

	}
	


	@GetMapping("/editAccount")
	public String editPage( Model model) {
		
		
		 UserAccountDt accountDetail = service.findByEmail(UtilityTool.getTokenEmail());
		
		UserAccountMt account = accountDetail.getUserAccountMt();
		
		model.addAttribute("Account", account);
		model.addAttribute("AccountDetail", accountDetail);
		return "editaccount";
	}

	@GetMapping("/progessDeleteAccount")
	public String processDelete( Model model) {
		
		UserAccountDt accountDetail = service.findByEmail(UtilityTool.getTokenEmail());
		
			UserAccountMt account = accountDetail.getUserAccountMt();

		if (("admin").equals(account.getUserstatus().getAccountLevel().toString())) {
			service.deleteAccount(account);
			return "redirect:/logout";

		}

		return "redirect:/";

	}

	@PostMapping("/progessEditAccount")
	public String processEdit( Model model,@ModelAttribute("AccountDetail") UserAccountDt acd) {
		
		
		  UserAccountDt accountdt = service.findByEmail(UtilityTool.getTokenEmail());
		 
		  accountdt.setGivenName(acd.getGivenName());
		  accountdt.setFamilyName(acd.getFamilyName());
		  accountdt.setGender(acd.getGender());
		  accountdt.setBirth(acd.getBirth());
		UserAccountMt account= accountdt.getUserAccountMt();
		account.setUserAccountDt(accountdt);
		service.edit(account);

		model.addAttribute("text", "修改已完成");
		return "index";
	}
	
	@PostMapping("/progessEditPwd")
	public String processEdit( Model model,@ModelAttribute("Account") UserAccountMt acc) {
		
		
		  UserAccountDt accountdt = service.findByEmail(UtilityTool.getTokenEmail());
		  UserAccountMt account= accountdt.getUserAccountMt();
		  
		  account.setPassword(bcryptoEncoder.encode(acc.getPassword()));
		  
		
		account.setUserAccountDt(accountdt);
		service.edit(account);

		model.addAttribute("text", "修改已完成");
		return "index";
	}

}
