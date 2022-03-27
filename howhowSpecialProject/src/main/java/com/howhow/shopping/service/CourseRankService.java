package com.howhow.shopping.service;

import java.util.List;
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
	CourseRankRepository repo;
	
	public CourseRank findByID(int id) {
		Optional<CourseRank> course = repo.findById(id);
		if(course.isPresent()) {
			return course.get();
		}
		return null;
	}
	
	public CourseRank insertCourseRank(CourseRank courseRank) {
		
		CourseRank insertedCourseRank = repo.save(courseRank);
		return insertedCourseRank;
	}
	
	public CourseRank updateCourseRank(CourseRank courseRank) {
		
		int id = courseRank.getCourseRankID();
		if(repo.findById(id)==null) {
			System.out.println("此CourseRankID不存在於資料庫");
		}
		CourseRank insert = repo.save(courseRank);
		return insert;
	}
	
	public boolean deleteCourseRankByID(int id) {
		
		if(repo.findById(id)==null) {
			System.out.println("此CourseRankID不存在於資料庫，無法刪除");
		}
		
		repo.deleteById(id);
		return true;
	}
	
	public List<CourseRank> findByCourseID(int id) {
		
		List<CourseRank> list = repo.findByCourseID(id);
		return list;
	}
}
