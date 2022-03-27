package com.howhow.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.howhow.entity.UserAccountMt;

public interface UserAccountMtRepository extends JpaRepository<UserAccountMt, Integer> {
	
	@Query(value = "select * from UserAccountMt where account = :account" , nativeQuery = true)
	public List<UserAccountMt> findByAccount(String account);
	



	

}
