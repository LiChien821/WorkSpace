package com.howhow.shopping.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.howhow.entity.CourseRank;

public interface CourseRankRepository extends JpaRepository<CourseRank, Integer> {
	
	@Query(value="SELECT * from CourseRank where courseid=?1", nativeQuery=true)
	public Page<CourseRank> findByCourseID(int courseid, Pageable pageable);

//	@Query(value="from CourseRank where courseid=?1")
//	public List<CourseRank> findByCourseID(int courseid);

	// public CourseRank insertCourseRank(CourseRank courseRank);
}
