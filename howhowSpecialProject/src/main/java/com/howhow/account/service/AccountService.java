package com.howhow.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.howhow.account.repository.AccountDetailRepository;
import com.howhow.account.repository.AccountRepository;
import com.howhow.entity.UserAccountDt;
import com.howhow.entity.UserAccountMt;

@Service
public class AccountService {

	@Autowired
	private AccountRepository repo;

	@Autowired
	private AccountDetailRepository detailRepo;

	@Autowired
	private PasswordEncoder bcryptoEncoder;

	public void createUser(UserAccountMt account, UserAccountDt acd) {
		acd.setUserAccountMt(account);
		account.setUserAccountDt(acd);

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
		newaccountDetail.setUser_Id(databaseAccount.getUserId());
		databaseAccount.setUserAccountDt(newaccountDetail);
		repo.save(databaseAccount);

	}

	public boolean activeAccount(String code, String userEmail) {
		UserAccountDt acd = detailRepo.findByEmail(userEmail);
		UserAccountMt user = repo.findById(acd.getUser_Id()).get();
		if (code.equals(user.getUserstatus().getVerificationcode())) {
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
}
