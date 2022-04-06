package com.howhow.course.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.howhow.entity.Lectures;
import com.howhow.entity.Section;

@Service
public class LearningLecturesService {

	@Autowired
	private CommonLectureRepository lectureRepo;

	@Autowired
	private BlobContainerClient containerClient;

	public boolean createLecture(Lectures lecture) {
		int lectureNum = lecture.getLectureNumber();
		int sectionID = lecture.getSection().getSectionID();
		if (!lectureIsDuplicated(sectionID, lectureNum)) {
			lectureRepo.save(lecture);
			return true;
		}
		return false;
	}

	private boolean lectureIsDuplicated(int sectionID, int lectureNum) {
		if (lectureRepo.findBysectionIDAndLectureNum(sectionID, lectureNum) != null) {
			return true;
		}
		return false;

	}

	public Iterable<Lectures> findAllBySectionID(int sectionID) {

		return lectureRepo.findAllBySectionID(sectionID);
	}
	
	//findLectureBySectionId - by weijie
	public List<Lectures> findAllLecturesBySectionId(Integer id){
		List<Lectures> op1 = lectureRepo.findAllLecturesBySectionId(id);
		if (op1.isEmpty()) {
			return null;
		}
		return op1;
	}

	public Lectures findByLectureID(int lectureID) {

		return lectureRepo.findById(lectureID).get();
	}

	public Lectures updateLecturesWithVideoSource(MultipartFile multipartfile, int lectureID) throws IOException {
		Lectures existedLectures = findByLectureID(lectureID);

		String fileName = StringUtils.cleanPath(multipartfile.getOriginalFilename());

		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i);
		}
		InputStream inputStream = multipartfile.getInputStream();

		BlobClient blobClient = containerClient
				.getBlobClient(existedLectures.getLectureNumber() + existedLectures.getLecturesName() + extension);

		blobClient.upload(inputStream, inputStream.available(), true);
		System.out.println("please waiting");
		existedLectures
				.setVideoSource(existedLectures.getLectureNumber() + existedLectures.getLecturesName() + extension);

		lectureRepo.save(existedLectures);
		return existedLectures;
	}
}
