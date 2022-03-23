package com.howhow.shopping.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.howhow.entity.CourseRank;
import com.howhow.shopping.repository.CourseRankRepository;

@Service
public class CourseRankService {
	
	@Autowired
	CourseRankRepository cRepo;
	
	public Page<CourseRank> findCourseRankByCourseID(int categoryid, Pageable pageable) {
		Page<CourseRank> courseRankPage = cRepo.findByCourseID(categoryid, pageable);
		return courseRankPage;
	}
	
	
//	public List<CourseRank> findCourseRankByCourseID(int categoryid) {
//		List<CourseRank> courseRankList = cRepo.findByCourseID(categoryid);
//		return courseRankList;
//	}
//	
//	public CourseRank insertCourseRank(CourseRank courseRank) {
//		
//		CourseRank insertedCourseRank = cRepo.insertCourseRank(courseRank);
//		return insertedCourseRank;
//	}
	
	public CourseRank findByRID(int id) {
		Optional<CourseRank> course = cRepo.findById(id);
		if(course.isPresent()) {
			return course.get();
		}
		return null;
	}
	
	public boolean deleteByCID(int id) {
		if(findByRID(id)!=null) {
			cRepo.deleteById(id);
			return true;
		}
		return false;
	}
	
}
