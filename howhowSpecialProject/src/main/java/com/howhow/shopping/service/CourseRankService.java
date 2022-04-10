package com.howhow.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.entity.CourseRank;
import com.howhow.shopping.exception.CourseRankNotFoundException;
import com.howhow.shopping.exception.UserOrCourseNotFoundException;
import com.howhow.shopping.repository.CourseRankRepository;

@Service
public class CourseRankService {
	
	@Autowired
	CourseRankRepository repo;
	
	public CourseRank findByID(int id) {
		Optional<CourseRank> course = repo.findById(id);
		if(course.isPresent()) {
			return course.get();
		}
		return null;
	}
	
	public CourseRank insertCourseRank(CourseRank courseRank) throws UserOrCourseNotFoundException {
		
		try {
		CourseRank insertedCourseRank = repo.save(courseRank);
		return insertedCourseRank;
		} catch (Exception e) {
			throw new UserOrCourseNotFoundException();
		}
	}
	
	public CourseRank updateCourseRank(CourseRank courseRank) throws CourseRankNotFoundException {
		
		int id = courseRank.getCourseRankID();
		if(repo.findById(id)==null) {
			throw new CourseRankNotFoundException();
		}
		CourseRank insert = repo.save(courseRank);
		return insert;
	}
	
	public boolean deleteCourseRankByID(int id) throws CourseRankNotFoundException {
		
		if(repo.findById(id)==null) {
			throw new CourseRankNotFoundException();
		}
		
		repo.deleteById(id);
		return true;
	}
	
	
	public boolean deleteCourseRankByUIDandCID(int userid, int courseid) throws CourseRankNotFoundException {
		
		CourseRank status = repo.findByUserIDandCourseID(userid, courseid);
		if(status==null) throw new CourseRankNotFoundException();
		
		int id = status.getCourseRankID();
		repo.deleteById(id);
		
		return true;
	}
	
	
	public List<CourseRank> findByCourseID(int id) {
		
		List<CourseRank> list = repo.findByCourseID(id);
		return list;
	}
	
	public List<CourseRank> findByUserID(int userid) {
		List<CourseRank> list = repo.findByUserID(userid);
		return list;
	}
	
}
