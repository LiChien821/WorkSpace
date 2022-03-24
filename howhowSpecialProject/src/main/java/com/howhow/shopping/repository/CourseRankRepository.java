package com.howhow.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.howhow.entity.CourseRank;

public interface CourseRankRepository extends JpaRepository<CourseRank, Integer> {
	
	@Query(value="select *from CourseRank where course_id=?1", nativeQuery=true)
	public List<CourseRank> findByCourseID(int courseid);
	
}
