package com.howhow.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.howhow.entity.LevelAlterApply;

public interface LevelAlterApplyRepository extends JpaRepository<LevelAlterApply, Integer> {
	
	public List<LevelAlterApply> findByapplystatus(String status);
	
//	@Query(value = "from LevelAlterApply where user_id = ?1")
	@Query(nativeQuery = true,
    value = "SELECT COUNT(*) FROM levelalterapply\n"
    		+ "WHERE user_id = ?1 ")
	public Integer findByUserId(int userid);
}
