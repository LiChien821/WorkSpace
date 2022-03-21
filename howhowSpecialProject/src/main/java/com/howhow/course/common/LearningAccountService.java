package com.howhow.course.common;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.entity.UserAccountMt;

@Service
public class LearningAccountService {

	@Autowired
	private CommonAccountRepository accountRepo;

	public Optional<UserAccountMt> findById(int uid) {
		
		return accountRepo.findById(uid);
	}
	
	
}
