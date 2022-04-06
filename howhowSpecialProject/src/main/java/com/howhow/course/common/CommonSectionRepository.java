package com.howhow.course.common;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.howhow.entity.Section;

public interface CommonSectionRepository extends PagingAndSortingRepository<Section, Integer> {
		
	@Query("SELECT s FROM Section s WHERE s.courseBasic.courseID = ?1 AND s.sectionNumber=?2")
	public Section findBycourseIDAndSectionNum(int courseID,int sectionNumber);
	
	@Query("SELECT s FROM Section s WHERE s.courseBasic.courseID = ?1")
	public Iterable<Section>  findAllByCourseID(int courseID);
	
	@Query(nativeQuery = true,
	           value = "SELECT * FROM section \n"
	           		+ "WHERE course_id = ?1\n"
	           		+ "ORDER BY section_id;")
	public List<Section> findAllSectionsByCourseId(Integer id);
}
