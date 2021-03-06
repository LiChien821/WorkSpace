package com.howhow.websecurity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.howhow.entity.AccountLevel;
import com.howhow.entity.UserAccountMt;

public class AccountUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	private UserAccountMt userAccount;

	public AccountUserDetails(UserAccountMt userAccount) {
		super();
		this.userAccount = userAccount;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authories = new ArrayList<>();
		AccountLevel role = userAccount.getUserstatus().getAccountLevel();
		authories.add(new SimpleGrantedAuthority(role.name()));
//		authories.add(new SimpleGrantedAuthority(userId));
		return authories;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userAccount.getPassword();

	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userAccount.getAccount();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {

		// return userAccount.getUserstatus().isEmailAuth();
		return true;
	}

	public UserAccountMt getLoggedAccount() {
		return userAccount;
	}

	public String getEmail() {
		return userAccount.getUserAccountDt().getEmail();
	}
}
