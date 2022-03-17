package com.howhow.websecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.howhow.account.repository.AccountRepository;
import com.howhow.entity.UserAccountMt;

public class AccountUserDetailsService implements UserDetailsService {
	@Autowired
	private AccountRepository repo;
	@Override
	public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {
		
		
	UserAccountMt user =repo.findByAccount(userAccount);
	if (user != null) {
		return new AccountUserDetails(user);
	}
		throw new UsernameNotFoundException("could not found"+userAccount);
	}

}
