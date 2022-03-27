package com.howhow.account.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.account.repository.UserAccountDtRepository;
import com.howhow.entity.UserAccountDt;

@Service
@Transactional
public class UserAccountDtService {

	@Autowired
	private UserAccountDtRepository uadRepo;
	
	//insert
	public UserAccountDt insert(UserAccountDt userAccountDt) {
		return uadRepo.save(userAccountDt);
	}
	
	//findAllUserAccountDt
	public List<UserAccountDt> findAllUserAccountDt() {
		return uadRepo.findAll();
	}
	
	//findById
	public UserAccountDt findById(Integer id) {
		Optional<UserAccountDt> op1 = uadRepo.findById(id);
		if (op1.isEmpty()) {
			return null;
		}
		return op1.get();	
	}
}
