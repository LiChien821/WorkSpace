package com.howhow.account.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.howhow.account.repository.UserAccountMtRepository;
import com.howhow.entity.UserAccountMt;

@Service
@Transactional
public class UserAccountMtService {
	
	@Autowired
	private UserAccountMtRepository mtrepo;

	//新增
	public UserAccountMt insert(UserAccountMt useraccountmt) {
		return mtrepo.save(useraccountmt);
	}

	public UserAccountMt findById(Integer id) {
		Optional<UserAccountMt> op1 = mtrepo.findById(id);
		if(op1.isEmpty()) {
			return null;
		}
		return op1.get();
	}
	
	public List<UserAccountMt> findByAccount(String account) {
		return mtrepo.findByAccount(account);
		
	}
	
	
}	

