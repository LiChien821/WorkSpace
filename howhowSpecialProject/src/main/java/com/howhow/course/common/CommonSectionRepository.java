package com.howhow.course.common;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.howhow.entity.Section;

public interface CommonSectionRepository extends PagingAndSortingRepository<Section, Integer> {
		
	@Query("SELECT s FROM Section s WHERE s.courseBasic.courseID = ?1 AND s.sectionNumber=?2")
	public Section findBycourseIDAndSectionNum(int courseID,int sectionNumber);
	
	@Query("SELECT s FROM Section s WHERE s.courseBasic.courseID = ?1")
	public Iterable<Section>  findAllByCourseID(int courseID);
}
