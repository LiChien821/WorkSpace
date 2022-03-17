package com.howhow.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.entity.CourseBasic;
import com.howhow.shopping.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository pRepo;
	
	public List<CourseBasic> findCourseByCourseNameLike(String courseName) {
		String name = "%"+courseName+"%";
		List<CourseBasic> list = pRepo.findByCourseNameLike(name);
		return list;
	}
	
	public CourseBasic findCourseByCourseName(String courseName) {
		CourseBasic bean = pRepo.findByCourseName(courseName);
		return bean;
	}
}
