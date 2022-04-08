package com.howhow.course.common;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.howhow.entity.Lectures;

public interface CommonLectureRepository extends PagingAndSortingRepository<Lectures, Integer> {
	
	@Query("SELECT l FROM Lectures l WHERE l.section.sectionID = ?1 AND l.lectureNumber=?2")
	public Lectures findBysectionIDAndLectureNum(int sectionID, int lectureNum);
	
	@Query("SELECT l FROM Lectures l WHERE l.section.sectionID = ?1")
	public Iterable<Lectures> findAllBySectionID(int sectionID,Sort sort);

	@Query(nativeQuery = true,
	           value = "SELECT * FROM lectures \n"
	           		+ "WHERE section_id = ?1\n"
	           		+ "ORDER BY lectures_id;")
	public List<Lectures> findAllLecturesBySectionId(Integer id);
}
