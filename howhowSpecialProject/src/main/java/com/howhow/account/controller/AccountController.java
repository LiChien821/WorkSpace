package com.howhow.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.howhow.account.service.AccountService;
import com.howhow.entity.UserAccountDt;
import com.howhow.entity.UserAccountMt;
import com.howhow.websecurity.AccountUserDetails;


@Controller
public class AccountController {
	
	@Autowired
	private AccountService service;
	
	@GetMapping("/login")
	public String login() {
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		return "redirect:/";
	}
	
	@GetMapping("/")
	public String index(@AuthenticationPrincipal AccountUserDetails loggedAccount ,Model model) {
		UserAccountMt account=loggedAccount.getLoggedAccount();
		model.addAttribute("account",account);
		System.out.println(account.getUserId());
	 
		return "main";
	}
	
	
	@GetMapping("/editAccount")
	public String editPage(@AuthenticationPrincipal AccountUserDetails loggedAccount ,Model model) {
		
		UserAccountMt loggedaccount=loggedAccount.getLoggedAccount();
		UserAccountMt account=service.findByUserAccount(loggedaccount.getAccount());
		UserAccountDt accountDetail =account.getUserAccountDt();
		model.addAttribute("account",account);
		model.addAttribute("accountDetail",accountDetail);
		return "editaccount";
	}
	
	@GetMapping("/progessDeleteAccount")
	public String processDelete(@AuthenticationPrincipal AccountUserDetails loggedAccount ,Model model) {
		
		UserAccountMt account=loggedAccount.getLoggedAccount();
		
		if(("admin").equals(account.getUserstatus().getAccountLevel().toString())) {
			service.deleteAccount(account);
			return "redirect:/logout";
			
		}
		
		return "redirect:/";
	
		
	}
	
	@PostMapping("/progessEditAccount")
	public String processEdit(@ModelAttribute("account")UserAccountMt account ,@ModelAttribute("accountDetail") UserAccountDt acd,Model model) {
		account.setUserAccountDt(acd);
		service.edit(account);
		
		model.addAttribute("text","修改已完成");
		return "success";
	}
	
}
