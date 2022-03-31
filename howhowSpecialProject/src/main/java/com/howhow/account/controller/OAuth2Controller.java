package com.howhow.account.controller;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.howhow.account.repository.AccountDetailRepository;
import com.howhow.account.repository.AccountRepository;
import com.howhow.account.service.AccountService;
import com.howhow.entity.UserAccountDt;
import com.howhow.entity.UserAccountMt;
import com.howhow.entity.UserBonus;
import com.howhow.entity.UserStatus;
import com.howhow.util.UtilityTool;

import reactor.core.publisher.Mono;

@Controller
public class OAuth2Controller {
	
	@Autowired
	private AccountService service;
	@Autowired
	private AccountRepository repo;
	@Autowired
	private AccountDetailRepository detailRepo;

	@Autowired
	private PasswordEncoder bcryptoEncoder;

	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;
	
	@RequestMapping("/login")
	public String index(Model model, OAuth2AuthenticationToken authentication) {
		OAuth2AuthorizedClient authorizedClient = this.getAuthorizedClient(authentication);
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
		return "index";
	}

	private OAuth2AuthorizedClient getAuthorizedClient(OAuth2AuthenticationToken authentication) {
		return this.authorizedClientService.loadAuthorizedClient(
			authentication.getAuthorizedClientRegistrationId(), authentication.getName());

    }
	
	@GetMapping("/google_register")
	public String registerPage(Model model) {
		UserAccountMt useraccountmt = new UserAccountMt();
		UserAccountDt useraccountdt = new UserAccountDt();
		UserStatus userstatus = new UserStatus();
		model.addAttribute("Account", useraccountmt);
		model.addAttribute("AccountDetail", useraccountdt);
		return "google_register";
	}
	
	@RequestMapping("/gcreateUser")
	public String gcreateUser(Model model,OAuth2AuthenticationToken authentication,@ModelAttribute("Account") UserAccountMt acc, 
			@ModelAttribute("AccountDetail") UserAccountDt acd,HttpServletRequest request) {
		
		
		OAuth2AuthorizedClient authorizedClient =
				this.authorizedClientService.loadAuthorizedClient(
					authentication.getAuthorizedClientRegistrationId(),authentication.getName());
			OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
			System.out.println(accessToken.getTokenValue());
			Map userAttributes = Collections.emptyMap();
			String userInfoEndpointUri = authorizedClient.getClientRegistration()
				.getProviderDetails().getUserInfoEndpoint().getUri();
			if (!StringUtils.isEmpty(userInfoEndpointUri)) {// userInfoEndpointUri is optional for OIDC Clients
				userAttributes = WebClient.builder()
					.filter(oauth2Credentials(authorizedClient))
					.build().get().uri(userInfoEndpointUri).retrieve().bodyToMono(Map.class).block();
			}
			
			acc.setAccount(userAttributes.get("email").toString());
			acd.setAccount(userAttributes.get("email").toString());
			//acc.setUserId(Integer.valueOf(userAttributes.get("sub").toString()));
			acd.setGivenName(userAttributes.get("given_name").toString());
			acd.setFamilyName(userAttributes.get("family_name").toString());
			acd.setEmail(userAttributes.get("email").toString());
			acd.setUserAccountMt(acc);
			acc.setUserAccountDt(acd);
			
			UserBonus bonus = new UserBonus();
			bonus.setUserID(acc);
			
			UserStatus status = new UserStatus();
			status.setUserAccountMt(acc);
			acc.setUserstatus(status);
			
			acc.setUserBonus(bonus);
			acc.setSystemTime(new java.util.Date());
			acd.setSystemTime(new java.util.Date());
			acd.setAcountCreationTime(new java.util.Date());
			model.addAttribute("userAttributes", userAttributes);
			//加密password
			String userAccount = acc.getAccount();
			if (repo.findByAccount(userAccount) == null && detailRepo.findByEmail(acd.getEmail())== null) {
				String encodePassword = bcryptoEncoder.encode(acc.getPassword());
				acc.setPassword(encodePassword);
				repo.save(acc);
				
			
				//service.save(acc);
				model.addAttribute("text", "註冊完成 請收取認證信  完成帳號啟動 ");
				return "index";
			
		}else {
			throw new RuntimeException("duplicated ");
		}
		
	

		
		
	}


	
	private ExchangeFilterFunction oauth2Credentials(OAuth2AuthorizedClient authorizedClient) {
		return ExchangeFilterFunction.ofRequestProcessor(
			clientRequest -> {
			ClientRequest authorizedRequest = ClientRequest.from(clientRequest)
			.header(HttpHeaders.AUTHORIZATION, "Bearer " + 
			authorizedClient.getAccessToken().getTokenValue()).build();
			return Mono.just(authorizedRequest);
		});
    }
}