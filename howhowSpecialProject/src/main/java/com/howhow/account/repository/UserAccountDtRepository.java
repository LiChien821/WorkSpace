package com.howhow.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howhow.entity.UserAccountDt;

public interface UserAccountDtRepository extends JpaRepository<UserAccountDt, Integer> {
	
	public UserAccountDt findByEmail(String email);

}
