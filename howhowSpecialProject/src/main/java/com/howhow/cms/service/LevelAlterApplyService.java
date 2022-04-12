package com.howhow.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.cms.repository.LevelAlterApplyRepository;
import com.howhow.entity.LevelAlterApply;

@Service
public class LevelAlterApplyService {
	
	@Autowired
	private LevelAlterApplyRepository repo;
	
	public List<LevelAlterApply> findUnprocessApply(String status) {
		return repo.findByapplystatus(status);
	}
	
	public void updateApply(LevelAlterApply apply) {
		repo.save(apply);
	}
	
	public LevelAlterApply findById(int id) {
		if(null != repo.findById(id)) {
			return repo.findById(id).get();
		}
		return null;
	}
	
	public LevelAlterApply insertApply(LevelAlterApply apply) {
		return repo.save(apply);
	}
}
