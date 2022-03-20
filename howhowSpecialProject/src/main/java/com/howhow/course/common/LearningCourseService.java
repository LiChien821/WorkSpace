package com.howhow.course.common;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.stereotype.Service;

import com.howhow.entity.CourseBasic;
import com.howhow.entity.Lectures;
import com.howhow.entity.Section;

@Service
public class LearningCourseService {
	public final static int NUMBEROFCOURSEPAGE = 6;
	@Autowired
	private CommonCourseRepository repo;

	@Autowired
	private CommonSectionRepository sectionRepo;

	public boolean createCourseSucessed(CourseBasic course) {
		int uid = course.getCreator().getUserId();
		String courseName = course.getCourseName();
		CourseBasic existedCourse = repo.findCourseByUIDAndCourseName(uid, courseName);
		if (existedCourse == null) {
			repo.save(course);

			return true;
		} else {
			return false;
		}

	}

//	private boolean courseNameIsDuplicated(int couresID,String courseName) {
//		if(	repo.findCourseByUIDAndCourseName(couresID,courseName)!=null) {
//			return true;
//		}else {
//			return false;
//		}
//		
//	}

//	public Course findCourseByID(Integer id) throws NoCourseException {
//
//		try {
//			repo.findById(id);
//			return repo.findById(id).get();
//		} catch (NoSuchElementException ex) {
//			throw new NoCourseException("no course found");
//		}
//
//	}
	public CourseBasic findCourseByUIDAndName(Integer uid, String Name) throws NoCourseException {

		CourseBasic existedCourse = repo.findCourseByUIDAndCourseName(uid, Name);
		if (existedCourse != null) {
			return existedCourse;
		} else {
			throw new NoCourseException("no course found");
		}

	}

	public boolean editSectionListofCourse(CourseBasic asignedcourse) throws NoCourseException {
		CourseBasic existedCourse = repo.findCourseByUIDAndCourseName(asignedcourse.getCreator().getUserId(),
				asignedcourse.getCourseName());
		if (existedCourse != null) {
			existedCourse.setSectionList(asignedcourse.getSectionList());
			repo.save(existedCourse);
			return true;
		} else {
			return false;
		}

	}




	public Iterable<CourseBasic> findAllCourseByUID(int id) {
		return repo.findAllCourseByUID(id);

	}

	public CourseBasic findCourseByCourseId(int courseId) {
		return repo.findById(courseId).get();
	}

	public void encrypto(String password) {
		BCryptPasswordEncoder en = new BCryptPasswordEncoder(BCryptVersion.$2Y, 4);
		String result = en.encode("password");
		Boolean ans = en.matches(password, result);
		System.out.println(result);
		System.out.println("try to match raw password:" + ans);

	}

}
