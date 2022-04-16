package com.howhow.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.howhow.entity.CourseBasic;

public interface CourseBasicRepository extends JpaRepository<CourseBasic, Integer> {
	
	public List<CourseBasic> findByCourseNameLike(String name);
	
	@Query(value="from CourseBasic where category_id=?1")
	public List<CourseBasic> findByCategoryID(int categoryid);
	
	@Query(value="from CourseBasic where status_id=?1")
	public List<CourseBasic> findByStatusID(int statusid);
	
	@Query(value="SELECT * FROM coursebasic\n"
			+ "WHERE course_id IN (\n"
			+ "	SELECT course_id FROM purchasedcourse\n"
			+ "	WHERE user_id = ?1 );", nativeQuery = true)
	public List<CourseBasic> findAllbyUserID(int userid);
	
}
