package com.howhow.account.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.howhow.entity.UserAccountMt;

public interface AccountRepository extends PagingAndSortingRepository<UserAccountMt, Integer> {
		
	
	public UserAccountMt findByAccount(String userAccount);
	
//	@Query("SELECT  a.uid FROM UserAccountMt a WHERE a.Account = ?1")
//	public int findIdByAccount(String Account);
}
