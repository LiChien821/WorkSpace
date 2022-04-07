package com.howhow.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.howhow.entity.PurchasedCourse;

public interface PurchasedCourseRepository extends JpaRepository<PurchasedCourse, Integer> {
	
	@Query(value="select * from PurchasedCourse where user_id=?1", nativeQuery = true)
	public List<PurchasedCourse> findByUserID(int id);
	
	@Query(value="select * from PurchasedCourse where user_id=?1 and course_id=?2", nativeQuery = true)
	public PurchasedCourse findPurchasedStatus(int id, int courseid);
	
	@Query(value="select * from PurchasedCourse where course_id=?1", nativeQuery = true)
	public List<PurchasedCourse> findByCourseID(int id);
	
	@Query(value="select count(*) from PurchasedCourse where course_id=?1", nativeQuery = true)
	public Integer findStudentCount(int courseid);
}
