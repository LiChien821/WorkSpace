package com.howhow.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.account.repository.UserStatusRespository;
import com.howhow.entity.UserStatus;

@Service
public class UserStatusService {
	
	@Autowired
	private UserStatusRespository usr;
	
	public UserStatus findById(int id) {
		if(null != usr.findById(id)){
			return usr.findById(id).get();
		}
		return null;
	}
	
	public UserStatus updateUserStatus(UserStatus userStatus) {
		return usr.save(userStatus);
	}
}
