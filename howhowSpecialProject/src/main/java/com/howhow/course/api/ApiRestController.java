package com.howhow.course.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
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
import com.howhow.course.exception.NotesDuplicationException;
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
//			Lectures lecture=lectureService.updateLecturesWithPreviewVideo(multipartfile, lectureID);
//			int courseID=lecture.getSection().getCourseBasic().getCourseID();
//			return sectionService.findAllPreviewableSectionByCourseID(courseID);
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
	
	
	
	
	
	

//	@PostMapping(value = "/api/askForSectionList")
//	public ResponseEntity returnSectionList(@RequestBody Map<String, String> data)
//			throws NumberFormatException, NoCourseException {
//		int courseID = Integer.parseInt("courseID");
//		CourseBasic existedCourse = courseService.findCourseByCourseId(courseID);
//		List<Section> seciontist = existedCourse.getSectionList();
//		return ResponseEntity.ok().body(seciontist);
//	}

	@PostMapping(value = "/api/testForign")
	public ResponseEntity testForign(@RequestParam("file") MultipartFile multipartfile,
			@RequestParam("videofile") MultipartFile videofile, @RequestParam("courseName") String courseName,
			@RequestParam("category") String category, @RequestParam("price") String price,
			@RequestParam("createtime") String createtime) throws IOException, NoCourseException {

		//////////////// video/////////////////
		CourseBasic course = new CourseBasic();
		if (!multipartfile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartfile.getOriginalFilename());
			course.setCourseCover(fileName);
			CourseBasic saveCourse = null;
//			if(service.editCourse(course)) {
//				 saveCourse =service.findCourseByUIDAndName(course.getCreator().getUid(), course.getCourseName());
//			}

			String uploadDir = "course-photos/" + saveCourse.getCourseID();

			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, multipartfile);
			///////////////////////////////////////////////
//			Path ImageDir=Paths.get("../course-photos");
//			String ImageDirPath =ImageDir.toFile().getAbsolutePath();
//			
//			String src="file:\\"+ ImageDirPath +"\\"+fileName;

			String src = "../course-photos/" + saveCourse.getCourseID() + "/" + fileName;

//			String images="/howhow/images"+"/"+fileName;
			/////////////////////////////////////////////

//			    System.out.println("ImageDirPath="+ImageDirPath);
			System.out.println(src);

			//////////////////////////////////////////////////////////////////////////
			if (!videofile.isEmpty()) {
				String videofileName = StringUtils.cleanPath(videofile.getOriginalFilename());
				String videouploadDir = "course-videos/" + saveCourse.getCourseID() + "/" + videofileName;

				FileUploadUtil.cleanDir(videouploadDir);
				FileUploadUtil.saveFile(videouploadDir, videofileName, videofile);

			}

			//////////////////////////////////////////////////////////////////////////
			HttpHeaders headers = new HttpHeaders();
			headers.add("Access-Control-Allow-Origin", "*");
			headers.add("Content-Type", "application/json");

			return ResponseEntity.ok().headers(headers).body(src);
		} else {
//			if(user.getPhotos().isEmpty() ) user.setPhotos(null);
			course.setCourseCover(null);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Access-Control-Allow-Origin", "*");
			headers.add("Content-Type", "application/json");

//			service.saveCourse(course);
			return ResponseEntity.ok().headers(headers).body("ok");
		}

	}

	@PostMapping(value = "/api/testForignAll")
	public ResponseEntity testForignAll() {
//		TestUser tuser=new TestUser();
//		TestUserDetail tuserDetail=new TestUserDetail();
//		tuserDetail.setAddress("高雄");
//		tuser.setUserdetail(tuserDetail);
//		tuserDetail.setTestUser(tuser);
//		tuser.setEmail("addd@gmail.com");
//		tuser.setName("addd");
//		tuser.setPassword("addddddddd");
//		tuser.setRoles("addd");
//		repo.save(tuser);

//		Iterable<TestUser> userIter= repo.findAll();
		String jsonString = null;

		courseService.encrypto("paword");

//		for( TestUser theuser : userIter) {
//			TestUser dto= new TestUser();
////			dto.setEmail(theuser.getEmail());
////			dto.setName(theuser.getName());
////			dto.setPassword(theuser.getPassword());
////			dto.setRoles(theuser.getRoles());
////			dto.setUserdetail(theuser.getUserdetail());
////			dto.setUserID(theuser.getUserID());
////			jsonuserListDe.add(dto);
//			 dto= theuser;
//			 userList.add(dto);
//		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Content-Type", "application/json");
		System.out.println(jsonString);
		return ResponseEntity.ok().headers(headers).body("ok");
	}
//	@GetMapping(value = "/api/getAllNotes")
//	@ResponseStatus(HttpStatus.CREATED)
//	public Iterable<Notes> returnAllNotesList() {
//		
//		return notesService.findAllNotesList();
//	
//
//	}

}
