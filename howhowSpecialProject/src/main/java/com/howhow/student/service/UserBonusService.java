package com.howhow.student.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.entity.UserBonus;
import com.howhow.shopping.exception.UserNotFoundException;
import com.howhow.student.repository.UserBonusRepository;

@Service
public class UserBonusService {

	@Autowired
	UserBonusRepository repo;
	
	public UserBonus findByID(int id) throws UserNotFoundException {
		Optional<UserBonus> bean = repo.findById(id);
		if(bean.isEmpty()) throw new UserNotFoundException();
		return bean.get();
	}
	
	public UserBonus updateUserBonus(UserBonus userbonus) {
		
		UserBonus save = repo.save(userbonus);
		return save;
	}
	
	
}
