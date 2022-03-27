package com.howhow.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.entity.CourseBasic;
import com.howhow.shopping.repository.CourseBasicRepository;

@Service
public class CourseBasicService {

	@Autowired
	CourseBasicRepository repo;
	
	public CourseBasic findByID(int id) {
		
		Optional<CourseBasic> bean = repo.findById(id);
		
		if(bean.isEmpty()) {
			System.out.println("此coursebasicID不存在");
			return null;
		}
		
		return bean.get();
	}
	
	public List<CourseBasic> findAll() {
		
		List<CourseBasic> list = repo.findAll();
		if(list.size()==0) System.out.println("無CourseBasic存在於資料庫內");
		return list;
		
	}
	
	public List<CourseBasic> findByCourseNameLike(String name) {
		
		String query = "%"+name+"%";
		List<CourseBasic> list = repo.findByCourseNameLike(query);
		return list;
		
	}
	
	public List<CourseBasic> findByCategoryID(int cid) {
		
		List<CourseBasic> list = repo.findByCategoryID(cid);
		return list;
		
	}
	
	public CourseBasic insertCourseBasic(CourseBasic course) {
		
		CourseBasic insertcourse = repo.save(course);
		return insertcourse;
	}
	
	public boolean deleteById(int id) {
		
		if(repo.findById(id)==null) {
			System.out.println("此courseID未存在於資料庫，故無法刪除");
			return false;
		}
		
		repo.deleteById(id);
		return true;
	}
	
	public CourseBasic updateCourseBasic(CourseBasic course) {
		
		int courseID = course.getCourseID();
		if(repo.findById(courseID)==null) {
			System.out.println("此courseID未存在於資料庫");
			return null;
		}
		
		CourseBasic updatecourse = repo.save(course);
		return updatecourse;
	}
	
}
