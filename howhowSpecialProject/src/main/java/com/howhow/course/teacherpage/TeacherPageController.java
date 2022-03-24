package com.howhow.course.teacherpage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.specialized.BlobOutputStream;
import com.howhow.course.common.CommonCategoryRepository;
import com.howhow.course.common.LearningAccountService;
import com.howhow.course.common.LearningCourseService;
import com.howhow.course.exception.CourseDuplicatedException;
import com.howhow.course.exception.NoCourseException;
import com.howhow.entity.Category;
import com.howhow.entity.CourseBasic;
import com.howhow.entity.UserAccountMt;

@Controller
@RequestMapping("/teacherPage")
public class TeacherPageController {

	@Autowired
	private BlobContainerClient containerClient;

	@Autowired
	private LearningCourseService courseService;

	@Autowired
	private LearningAccountService accountService;

	@Autowired
	private CommonCategoryRepository caregoryRepo;

	@GetMapping("/page")
	public String homepage(Model model) {
		int accountID = 1;
		model.addAttribute("accountID", accountID);
		return "course/teacherPage/page.html";
	}

	@PostMapping("/play")
	public String playpage(@RequestParam(name = "courseId") Integer courseId, Model model) {
		model.addAttribute("courseId", courseId);
		return "course/teacherPage/dashboard";
	}

	@PostMapping("/edit")
	public String editpage(@RequestParam(name = "courseId") Integer courseId, Model model) {
		System.out.println(courseId);
		List<Category> cateList = new ArrayList<Category>();
		Iterable<Category> cate = caregoryRepo.findAll();
		cate.forEach(cateList::add);
		model.addAttribute("courseId", courseId);
		model.addAttribute("cateList", cateList);
		return "course/teacherPage/editcoursepage.html";
	}

	@GetMapping("/new")
	public String newpage(Model model) {
		CourseBasic courseBasic = new CourseBasic();
		List<Category> cateList = new ArrayList<Category>();
		Iterable<Category> cate = caregoryRepo.findAll();
		cate.forEach(cateList::add);
		model.addAttribute("courseBasic", courseBasic);
		model.addAttribute("cateList", cateList);

		return "course/teacherPage/newcoursepage.html";
	}

	@PostMapping("/processedCreateCourse")
	public String processedCreateCourse(CourseBasic courseBasic, @RequestParam("poster") MultipartFile multipartfile,
			Model model) throws NoCourseException, IOException, CourseDuplicatedException {
		int uid = 1;
		UserAccountMt userAccount = accountService.findById(uid).get();
		courseBasic.setCreator(userAccount.getUserAccountDt());
		
		if (courseService.createCourseSucessed(courseBasic)) {
			CourseBasic existedCourse = courseService.findCourseByUIDAndName(uid, courseBasic.getCourseName());
			Integer courseID= existedCourse.getCourseId();
			if(multipartfile !=null && !multipartfile.isEmpty()) {
			courseService.updateCourseAbstractCover(multipartfile,courseID);
			}
			model.addAttribute("courseId", courseID);
			return "course/teacherPage/editcoursepage.html";
		}

		return "redirect:/teacherPage/new";
	}

}
