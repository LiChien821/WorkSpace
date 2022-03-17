package com.howhow.account.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.howhow.entity.UserAccountDt;

public interface AccountDetailRepository extends PagingAndSortingRepository<UserAccountDt, Integer> {

	public UserAccountDt findByEmail(String useremail);
}
