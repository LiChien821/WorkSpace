package com.howhow.course.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.howhow.course.exception.CourseDuplicatedException;
import com.howhow.course.exception.NoCourseException;
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

	@Autowired
	private BlobContainerClient containerClient;

	public boolean createCourseSucessed(CourseBasic course) throws CourseDuplicatedException {
		int uid = course.getCreator().getUserId();
		String courseName = course.getCourseName();
		CourseBasic existedCourse = repo.findCourseByUIDAndCourseName(uid, courseName);
		if (existedCourse == null) {
			repo.save(course);

			return true;
		} else {
			throw new CourseDuplicatedException();
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

	public CourseBasic updateCourseAbstractCover(MultipartFile multipartfile, int courseID) throws IOException {
		
		String fileName = StringUtils.cleanPath(multipartfile.getOriginalFilename());
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23)
				+ str.substring(24);
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i);
		}
		CourseBasic existedCourse = findCourseByCourseId(courseID);
		String oldCover=existedCourse.getCourseCover();
		if(oldCover != "" && oldCover != null) {
		BlobClient oldBlobClient = containerClient.getBlobClient(oldCover);
		oldBlobClient.delete();
		}
		InputStream inputStream = multipartfile.getInputStream();
		BlobClient blobClient = containerClient.getBlobClient(temp + existedCourse.getCourseName() + extension);
		blobClient.upload(inputStream, inputStream.available(), true);
		existedCourse.setCourseCover(temp + existedCourse.getCourseName() + extension);

		repo.save(existedCourse);
		return existedCourse;
		
	}

	public boolean updateCourseAbstract(CourseBasic course) {
		try {
			CourseBasic existedCourse = findCourseByCourseId(course.getCourseId());
			if (course.getCategory() != null) {
				existedCourse.setCategory(course.getCategory());
			}
			if (course.getCourseName() != null && course.getCourseName() != "") {
				existedCourse.setCourseName(course.getCourseName());
			}
			if (course.getDescription() != null && course.getDescription() != "") {
				existedCourse.setDescription(course.getDescription());
			}
			if (course.getPrice() != 0) {
				existedCourse.setPrice(course.getPrice());
			}
			repo.save(existedCourse);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
