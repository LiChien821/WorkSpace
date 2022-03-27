package com.howhow.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.howhow.entity.PurchasedCourse;

public interface PurchasedCourseRepository extends JpaRepository<PurchasedCourse, Integer> {
	
	@Query(value="select * from PurchasedCourse where user_id=?1", nativeQuery = true)
	public List<PurchasedCourse> findByUserID(int id);
}
