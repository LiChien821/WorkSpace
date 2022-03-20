package com.howhow.course.common;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.howhow.entity.Lectures;

public interface CommonLectureRepository extends PagingAndSortingRepository<Lectures, Integer> {
	
	@Query("SELECT l FROM Lectures l WHERE l.section.sectionID = ?1 AND l.lectureNumber=?2")
	public Lectures findBysectionIDAndLectureNum(int sectionID, int lectureNum);
	
	@Query("SELECT l FROM Lectures l WHERE l.section.sectionID = ?1")
	public Iterable<Lectures> findAllBySectionID(int sectionID);

}
