package com.howhow.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howhow.entity.CourseBasic;
import com.howhow.shopping.exception.CourseNotFoundException;
import com.howhow.shopping.exception.UserNotFoundException;
import com.howhow.shopping.repository.CourseBasicRepository;

@Service
public class CourseBasicService {

	@Autowired
	CourseBasicRepository repo;

	public CourseBasic findByID(int id) {

		Optional<CourseBasic> bean = repo.findById(id);

		if (bean.isEmpty()) {
			return null;
		}

		return bean.get();
	}

	public List<CourseBasic> findAll() {

		List<CourseBasic> list = repo.findAll();
		if (list.size() == 0)
			System.out.println("無CourseBasic存在於資料庫內");
		return list;

	}

	public List<CourseBasic> findByCourseNameLike(String name) {

		String query = "%" + name + "%";
		List<CourseBasic> list = repo.findByCourseNameLike(query);
		return list;

	}

	public List<CourseBasic> findByCategoryID(int cid) {

		List<CourseBasic> list = repo.findByCategoryID(cid);
		return list;

	}

	public CourseBasic insertCourseBasic(CourseBasic course) throws UserNotFoundException {

		try {
			CourseBasic insertcourse = repo.save(course);

			return insertcourse;
		} catch (Exception e) {
			throw new UserNotFoundException();
		}
	}

	public boolean deleteById(int id) throws CourseNotFoundException {
		
		if(repo.findById(id)==null) {
			throw new CourseNotFoundException();
		}
		
		repo.deleteById(id);
		return true;
	}

	public CourseBasic updateCourseBasic(CourseBasic course) throws CourseNotFoundException{
		
		int courseID = course.getCourseID();
		if(repo.findById(courseID)==null) {
			throw new CourseNotFoundException();
		}
		
		CourseBasic updatecourse = repo.save(course);
		return updatecourse;
	}
	
	public List<CourseBasic> findUnAuditCourses(int statusid){
		return repo.findByStatusID(statusid);
	}
	
}
