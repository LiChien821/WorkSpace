package com.howhow.account.service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import com.howhow.account.repository.AccountDetailRepository;
import com.howhow.account.repository.AccountRepository;
import com.howhow.entity.UserAccountDt;
import com.howhow.entity.UserAccountMt;
import com.howhow.entity.UserBonus;
import com.howhow.entity.UserStatus;

@Service
public class AccountService {

	@Autowired
	private AccountRepository repo;

	@Autowired
	private AccountDetailRepository detailRepo;

	@Autowired
	private PasswordEncoder bcryptoEncoder;
	
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;

	public void createUser(UserAccountMt account, UserAccountDt acd) {
		acd.setUserAccountMt(account);
		account.setUserAccountDt(acd);
		
		UserBonus bonus = new UserBonus();
		bonus.setUserID(account);
		
		UserStatus status = new UserStatus();
		status.setUserAccountMt(account);
		account.setUserstatus(status);
		
		account.setUserBonus(bonus);
		account.setSystemTime(new java.util.Date());
		acd.setSystemTime(new java.util.Date());
		acd.setAcountCreationTime(new java.util.Date());
		
		//加密password
		String userAccount = account.getAccount();
		if (repo.findByAccount(userAccount) == null && detailRepo.findByEmail(acd.getEmail())== null) {
			String encodePassword = bcryptoEncoder.encode(account.getPassword());
			account.setPassword(encodePassword);
			repo.save(account);
		}else {
			throw new RuntimeException("duplicated ");
		}
		

	}
	public void gcreateUser(OAuth2AuthenticationToken authentication,UserAccountMt account, UserAccountDt acd) {
		acd.setUserAccountMt(account);
		account.setUserAccountDt(acd);
		
		UserBonus bonus = new UserBonus();
		bonus.setUserID(account);
		
		UserStatus status = new UserStatus();
		status.setUserAccountMt(account);
		account.setUserstatus(status);
		
		account.setUserBonus(bonus);
		account.setSystemTime(new java.util.Date());
		acd.setSystemTime(new java.util.Date());
		acd.setAcountCreationTime(new java.util.Date());
		OAuth2AuthorizedClient authorizedClient =
				this.authorizedClientService.loadAuthorizedClient(
					authentication.getAuthorizedClientRegistrationId(),authentication.getName());
			OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
			System.out.println(accessToken.getTokenValue());
			Map userAttributes = Collections.emptyMap();
			String userInfoEndpointUri = authorizedClient.getClientRegistration()
				.getProviderDetails().getUserInfoEndpoint().getUri();
			
		
		account.setAccount(userAttributes.get("email").toString());
		account.setUserId(Integer.valueOf(userAttributes.get("sub").toString()));
		acd.setGivenName(userAttributes.get("given_name").toString());
		acd.setFamilyName(userAttributes.get("family_name").toString());
		acd.setEmail(userAttributes.get("email").toString());
		
		//加密password
		String userAccount = account.getAccount();
		if (repo.findByAccount(userAccount) == null && detailRepo.findByEmail(acd.getEmail())== null) {
			String encodePassword = bcryptoEncoder.encode(account.getPassword());
			account.setPassword(encodePassword);
			repo.save(account);
		}else {
			throw new RuntimeException("duplicated ");
		}
	}

	public void edit(UserAccountMt account) {
		String accountName = account.getAccount();

		UserAccountMt databaseAccount = repo.findByAccount(accountName);
		String pwd = account.getPassword().trim();
		if (pwd != "" & pwd != null) {

			databaseAccount.setPassword(bcryptoEncoder.encode(pwd));
		}
		UserAccountDt newaccountDetail = account.getUserAccountDt();
		newaccountDetail.setUserId(databaseAccount.getUserId());
		databaseAccount.setUserAccountDt(newaccountDetail);
		repo.save(databaseAccount);

	}

	public boolean activeAccount(String code, String userEmail) {
		UserAccountDt acd = detailRepo.findByEmail(userEmail);
		UserAccountMt user = repo.findById(acd.getUserId()).get();
		if (code.equals(user.getVerificationcode())) {
			user.getUserstatus().setEmailAuth(true);
			repo.save(user);
			return true;
		}
		return false;
	}

	public UserAccountMt findByUserAccount(String userAccount) {
		return repo.findByAccount(userAccount);
	}

	public boolean deleteAccount(UserAccountMt userAccount) {
		try {
			int id = userAccount.getUserId();
			repo.deleteById(id);
			detailRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public void save(UserAccountMt acc) {
		repo.save(acc);

	}
	
	public UserAccountMt findByID(int id) {
		Optional<UserAccountMt> bean = repo.findById(id);
		return bean.get();
	}
}
