package com.howhow.test.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.entity.UserAccountMt;
import com.howhow.entity.UserBonus;
import com.howhow.test.repository.TestRepository;

@Service
public class TestService {

	@Autowired
	TestRepository tRepo;

	public UserBonus findbyAccount(String account) {
		Optional<UserAccountMt> accountquery = tRepo.findByAccount(account);
		UserBonus userBonus = accountquery.get().getUserBonus();

		return userBonus;
	}

	public void saveUser(UserAccountMt user) {

		tRepo.save(user);
	}
}
