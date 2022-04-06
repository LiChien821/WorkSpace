package com.howhow.course.common;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.course.exception.NoSectionException;
import com.howhow.entity.CourseBasic;
import com.howhow.entity.Section;

@Service
public class LearningSectionService {
	
	@Autowired
	private CommonSectionRepository sectionRepo;
	
	

	
	public boolean createSection(Section section) {
		int sectionNum=section.getSectionNumber();
		int courseID=section.getCourseBasic().getCourseID();
		if(!sectionIsDuplicated(courseID, sectionNum)) {
			sectionRepo.save(section);
			return true;
		}
		return false;
	}


	private boolean sectionIsDuplicated(int courseID ,int sectionNum) {
		if(sectionRepo.findBycourseIDAndSectionNum(courseID, sectionNum)!=null) {
			return true;
		}
		return false;
	}


	public Iterable<Section> findAllByCourseId(int courseID) {
		return 		sectionRepo.findAllByCourseID(courseID);

	}
	
	//findSectionByCourseId - by weijie
	public List<Section> findAllSectionsByCourseId(Integer id) {
		List<Section> op1 = sectionRepo.findAllSectionsByCourseId(id);
		System.out.println();
		if (op1.isEmpty()) {
			return null;
		}
		return op1;
	}
	
	public Section findSectionByID(int id) throws NoSectionException {
		try {
			sectionRepo.findById(id);
			return sectionRepo.findById(id).get();
		} catch (NoSuchElementException ex) {
			throw new NoSectionException("no section found");
		}

	}



	public boolean editLecturesListofSection(Section section) {
		
		int courseID=section.getCourseBasic().getCourseID();
		int sectionNum=section.getSectionNumber();
		Section existedSection=sectionRepo.findBycourseIDAndSectionNum(courseID,sectionNum);
		if (existedSection!=null) {
			existedSection.setLecturesList(section.getLecturesList());
			sectionRepo.save(existedSection);
			return true;
		}else {
			return false;
		}
		
	}
	
	
	public void saveSection(Section section) {
		sectionRepo.save(section);

	}
	
}
