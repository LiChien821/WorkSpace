package com.howhow.course.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

@Service
public class LearningCourseService {
	public final static int NUMBEROFCOURSEPAGE = 6;
	@Autowired
	private CommonCourseRepository repo;



	@Autowired
	private BlobContainerClient containerClient;

	public boolean createCourseSucessed(CourseBasic course) throws CourseDuplicatedException {
		int uid = course.getCreator().getUserId();
		String courseName = course.getCourseName();
		CourseBasic existedCourse = repo.findCourseByUIDAndCourseName(uid, courseName).orElse(null);
		if (existedCourse == null) {
//			CourseStatusType type= findby(typeid);
//			course.setStatusType(type);
			course.setDiscount(1);
			repo.save(course);

			return true;
		} else {
			throw new CourseDuplicatedException();
		}

	}



	public CourseBasic findCourseByUIDAndName(Integer uid, String Name) throws NoCourseException {

		return repo.findCourseByUIDAndCourseName(uid, Name)
				.orElseThrow(()-> new NoCourseException("no course found"));
		
	}

	public void editSectionListofCourse(CourseBasic asignedcourse) throws NoCourseException {
		CourseBasic existedCourse = repo.findCourseByUIDAndCourseName(asignedcourse.getCreator().getUserId(),
				asignedcourse.getCourseName()).orElseThrow(()-> new NoCourseException("no course found"));
	
				existedCourse.setSectionList(asignedcourse.getSectionList());
				repo.save(existedCourse);
				
		
	}

	public Iterable<CourseBasic> findAllCourseByUID(int id) {
		  Sort sort=Sort.by("courseID").ascending();
		return repo.findAllCourseByUID(id,sort);

	}

	public CourseBasic findCourseByCourseId(int courseId) throws IOException {
		return repo.findById(courseId).orElseThrow( ()->new IOException("無此courseID"+courseId) );
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
			CourseBasic existedCourse = findCourseByCourseId(course.getCourseID());
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
