package com.howhow.shopping.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.howhow.entity.CourseRank;

public interface CourseRankRepository extends JpaRepository<CourseRank, Integer> {
	
//	public Page<CourseRank> findByCategoryIDByPage(int categoryid, Pageable pageable);
	
//	public CourseRank insertCourseRank(CourseRank courseRank);
}
