package com.howhow.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.entity.PurchasedCourse;
import com.howhow.student.repository.PurchasedCourseRepository;

@Service
public class PurchasedCourseService {
	
	@Autowired
	PurchasedCourseRepository repo;
	
	public PurchasedCourse findByID(int id){
		Optional<PurchasedCourse> bean = repo.findById(id);
		if(bean.isEmpty()) return null;
		return bean.get();
	}
	
	public List<PurchasedCourse> findByUserID(int id) {
		List<PurchasedCourse> list = repo.findByUserID(id);
		return list;
	}
	
	public boolean deleteByID(int id) {
		if(repo.findById(id).isEmpty()) return false;
		repo.deleteById(id);
		return true;
	}
}
