package com.howhow.account.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.account.repository.AccountDetailRepository;
import com.howhow.entity.UserAccountDt;

@Service
public class AccountDetailService {

	@Autowired
	AccountDetailRepository repo;
	
	public UserAccountDt findByID(int id) {
		
		Optional<UserAccountDt> bean = repo.findById(id);
		return bean.get();
	}
	
}
