package com.howhow.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howhow.entity.LevelAlterApply;

public interface LevelAlterApplyRepository extends JpaRepository<LevelAlterApply, Integer> {
	public List<LevelAlterApply> findByapplystatus(String status);
}
