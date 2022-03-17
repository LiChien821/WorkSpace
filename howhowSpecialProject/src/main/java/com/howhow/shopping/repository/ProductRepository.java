package com.howhow.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howhow.entity.CourseBasic;

public interface ProductRepository extends JpaRepository<CourseBasic, Integer> {
	
	public List<CourseBasic> findByCourseNameLike(String courseName);
	
	public CourseBasic findByCourseName(String courseName);
}
