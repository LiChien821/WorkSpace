package com.howhow.course.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.howhow.course.common.CommonCategoryRepository;
import com.howhow.course.common.LearningAccountService;
import com.howhow.course.common.LearningCourseService;
import com.howhow.course.common.LearningLecturesService;
import com.howhow.course.common.LearningNotesService;
import com.howhow.course.common.LearningSectionService;
import com.howhow.course.exception.BadEequestException;
import com.howhow.course.exception.BlobUploadException;
import com.howhow.course.exception.CourseDuplicatedException;
import com.howhow.course.exception.LectureDuplicationException;
import com.howhow.course.exception.NoCourseException;
import com.howhow.course.exception.NoSectionException;
import com.howhow.course.exception.SessionDuplicationException;
import com.howhow.course.exception.WrongInputException;
import com.howhow.course.exception.updateLectureVideoIOException;
import com.howhow.entity.Category;
import com.howhow.entity.CourseBasic;
import com.howhow.entity.Lectures;
import com.howhow.entity.Notes;
import com.howhow.entity.Section;
import com.howhow.entity.UserAccountMt;

@RestController
public class ApiRestController {

	@Value("${blob.url.setting}")
	private String blobSetting;

	@Autowired
	private LearningCourseService courseService;

	@Autowired
	private LearningSectionService sectionService;

	@Autowired
	private LearningAccountService accountService;

	@Autowired
	private LearningLecturesService lectureService;
	
	@Autowired
	private LearningNotesService notesService;

	@Autowired
	private CommonCategoryRepository categoryRepo;
	
	
	

	@GetMapping("/api/getBlobUrl")
	public String getBlobUrl() {
		return blobSetting;
	}

	@GetMapping("/api/getAllCategory")
	public Iterable<Category> getAllCategory() {
		return categoryRepo.findAll();
				
	}
	@GetMapping("/api/getAllNotes/{UID}/{lecturesID}")
	public Iterable<Notes> getAllNotes(@PathVariable("UID") String UID,@PathVariable("lecturesID") String lecturesID) {
		int uidInt=Integer.parseInt(UID);
		int lecturesIDInt=Integer.parseInt(lecturesID);

		return notesService.findAllNotesListByUIDAndLectureID(uidInt,lecturesIDInt);
				
	}

	@GetMapping("/api/getLectureList/{sectionID}")
	public Iterable<Lectures> getAllLectureFromSectionID(@PathVariable("sectionID") int sectionID) {
		return lectureService.findAllBySectionID(sectionID);
	}

	@GetMapping("/api/getSectionList/{courseID}")
	public Iterable<Section> getAllSectionFromCourseID(@PathVariable("courseID") int courseID) {
		return sectionService.findAllByCourseId(courseID);
	}

	@GetMapping("/api/getAllCourse/{accountID}")
	public Iterable<CourseBasic> getCourseListFromAccountID(@PathVariable("accountID") int accountID) {
		return courseService.findAllCourseByUID(accountID);
	}

	@GetMapping("/api/getCourse/{courseID}")
	public CourseBasic getCourseFromCourseID(@PathVariable("courseID") int courseID) throws WrongInputException   {
		try {
			return courseService.findCourseByCourseId(courseID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WrongInputException("無此ID"+courseID);
		}
				
	}

	@PostMapping("/api/updateCourseAbstractCover")
	public CourseBasic updateCourseAbstractCover(@RequestParam("file") MultipartFile multipartfile,
			@RequestParam("courseID") int courseID) throws BlobUploadException  {

		try {
			
	    	return courseService.updateCourseAbstractCover(multipartfile,courseID);
		} catch (IOException e) {
			e.printStackTrace();
			throw new BlobUploadException("updateCourseAbstractCover fail");
		}
    	
		
	}
	
	@PostMapping(value = "/api/createNotes")
	@ResponseStatus(HttpStatus.CREATED)
	public Iterable<Notes> createNoteAndReturnNotesList(
	@RequestBody JsonNoteRecevier reciver) {
		if(notesService.createNote(reciver)) {		
			return notesService.findAllNotesListByUIDAndLectureID(reciver);
		}
		return null;
	}
	

	@PostMapping("/api/updateCourseAbstract")
	public CourseBasic updateCourseAbstract(@RequestBody CourseBasic course) throws BadEequestException, IOException {
		if(courseService.updateCourseAbstract(course)) {
		
			return courseService.findCourseByCourseId(course.getCourseID());
		}else {
			throw new BadEequestException("錯誤提交");
		}	
	}
	
	@PostMapping("/api/updateLectureVideo")
	public Lectures updateLectureVideo(@RequestParam("videofile") MultipartFile multipartfile,
			@RequestParam("lectureID") int lectureID) throws  updateLectureVideoIOException {
		
		try {	
	    	return lectureService.updateLecturesWithVideoSource(multipartfile, lectureID);
		} catch (IOException e) {
		
			e.printStackTrace();
			throw new updateLectureVideoIOException();
		}
		
	}

	

	@PostMapping(value = "/api/createSection/{courseID}")
	@ResponseStatus(HttpStatus.CREATED)
	public Iterable<Section> createSectionAndReturnSectionList(@PathVariable("courseID") int courseID,
			@RequestBody Section inputSection) throws NoCourseException, SessionDuplicationException, IOException {
		CourseBasic existedCourse = courseService.findCourseByCourseId(courseID);
		inputSection.setCourseBasic(existedCourse);
		if (!sectionService.createSection(inputSection)) {
			throw new SessionDuplicationException();
		}
		List<Section> sectionlist = existedCourse.getSectionList();
		sectionlist.add(inputSection);
		existedCourse.setSectionList(sectionlist);
		courseService.editSectionListofCourse(existedCourse);
		return sectionService.findAllByCourseId(courseID);


	}
	
	
	@PostMapping(path = "/api/createAbstract/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public CourseBasic createAbstractAndReturnCourseData(@PathVariable("id") int uid,
			@RequestBody CourseBasic newcourse) throws NumberFormatException, NoCourseException, CourseDuplicatedException {

		UserAccountMt acd = accountService.findById(uid).get();
		newcourse.setCreator(acd.getUserAccountDt());
		try {
			courseService.createCourseSucessed(newcourse);
			newcourse = courseService.findCourseByUIDAndName(acd.getUserId(), newcourse.getCourseName());
			return newcourse;
		} catch (Exception e) {
			throw new CourseDuplicatedException();
		}
		

	}
	
	@PostMapping(path = "/api/updateLecturePreviewVideoReturnPreviewableSectionlist")
	public List<Section> updateLecturePreviewVideoReturnPreviewableSectionlist(@RequestParam("previewVideofile") MultipartFile multipartfile,
			@RequestParam("lectureID") int lectureID) throws updateLectureVideoIOException, IOException  {

		if(multipartfile.isEmpty() || multipartfile==null) {
			throw new updateLectureVideoIOException();
		} else  {
			Lectures lecture=lectureService.updateLecturesWithPreviewVideo(multipartfile, lectureID);
			int courseID=lecture.getSection().getCourseBasic().getCourseID();
			
			return sectionService.findAllPreviewableSectionByCourseID(courseID);
		}
		

	}

	@PostMapping(value = "/api/createLecture/{sectionID}")
	@ResponseStatus(HttpStatus.CREATED)
	public Iterable<Lectures> createLecture(@PathVariable("sectionID") int sectionID, @RequestBody Lectures lecture)
			throws NumberFormatException, NoSectionException, LectureDuplicationException {
		Section existedSection = sectionService.findSectionByID(sectionID);
		lecture.setSection(existedSection);
		if (!lectureService.createLecture(lecture)) {
			throw new LectureDuplicationException();
		}
		List<Lectures> lectureList = existedSection.getLecturesList();
		lectureList.add(lecture);
		existedSection.setLecturesList(lectureList);
		sectionService.editLecturesListofSection(existedSection);
		return lectureService.findAllBySectionID(sectionID);

	}
	@PostMapping(value = "/api/updateSectionName/{courseID}")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Section> updateSectionName(@PathVariable("courseID") int courseID, @RequestBody Section upSection) throws NoSectionException
			{
		Section existedSection= sectionService.findSectionByID(upSection.getSectionID());  
		existedSection.setSectionName(upSection.getSectionName());
		sectionService.saveSection(existedSection);  
		return sectionService.findAllByCourseId(courseID);

	}
	@PostMapping(value = "/api/updateLecturesName/{sectionID}")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Lectures> updateLecturesName(@PathVariable("sectionID") int sectionID, @RequestBody Lectures upLectures) throws NoSectionException
			{
		Lectures existedLectures= lectureService.findByLectureID(upLectures.getLecturesID());
		existedLectures.setLecturesName(upLectures.getLecturesName());
		lectureService.saveLectures(existedLectures);  
		return lectureService.findAllBySectionID(sectionID);

	}
	
	

}
