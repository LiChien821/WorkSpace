package com.howhow.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howhow.entity.UserAccountMt;

public interface TestRepository extends JpaRepository<UserAccountMt, Integer> {
	
	public Optional<UserAccountMt> findByAccount(String account);
}
