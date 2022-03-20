package com.howhow.course.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.howhow.entity.Lectures;
import com.howhow.entity.Section;

@Service
public class LearningLecturesService {
	
	@Autowired
	private CommonLectureRepository lectureRepo;
	
	
	public boolean createLecture(Lectures lecture) {
		int lectureNum=lecture.getLectureNumber();
		int sectionID=lecture.getSection().getSectionID();
		if(!lectureIsDuplicated(sectionID, lectureNum)) {
			lectureRepo.save(lecture);
			return true;
		}
		return false;
	}


	private boolean lectureIsDuplicated(int sectionID, int lectureNum) {
		if(lectureRepo.findBysectionIDAndLectureNum(sectionID, lectureNum)!=null) {
			return true;
		}
		return false;
		
	}


	public Iterable<Lectures> findAllBySectionID(int sectionID) {
		
		return lectureRepo.findAllBySectionID(sectionID);
	}
}
