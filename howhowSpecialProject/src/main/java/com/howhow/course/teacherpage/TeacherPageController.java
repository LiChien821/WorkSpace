package com.howhow.course.teacherpage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.howhow.course.common.CommonCategoryRepository;
import com.howhow.course.common.LearningAccountService;
import com.howhow.course.common.LearningCourseService;
import com.howhow.course.exception.CourseDuplicatedException;
import com.howhow.course.exception.NoCourseException;
import com.howhow.entity.Category;
import com.howhow.entity.CourseBasic;
import com.howhow.entity.Section;
import com.howhow.entity.UserAccountDt;
import com.howhow.entity.UserAccountMt;
import com.howhow.websecurity.AccountUserDetails;

@Controller
@RequestMapping("/teacherPage")
public class TeacherPageController {


	@Autowired
	private LearningCourseService courseService;

	@Autowired
	private LearningAccountService accountService;

	@Autowired
	private CommonCategoryRepository caregoryRepo;

	@GetMapping("/page")
	public String homepage(@AuthenticationPrincipal AccountUserDetails loggedAccount , Model model) {
		UserAccountDt creator=loggedAccount.getLoggedAccount().getUserAccountDt();
		int accountID = loggedAccount.getLoggedAccount().getUserId();
		model.addAttribute("creator", creator);
		model.addAttribute("accountID", accountID);
		return "course/teacherPage/page.html";
	}

	@PostMapping("/play/{courseID}")
	public String playpage(@AuthenticationPrincipal AccountUserDetails loggedAccount ,@PathVariable(name = "courseID") String courseID, Model model) throws IOException {
		int courseIntID=Integer.parseInt(courseID);
		UserAccountMt amt=loggedAccount.getLoggedAccount();
		UserAccountDt acd=amt.getUserAccountDt();	
		CourseBasic theCourse=courseService.findCourseByCourseId(courseIntID);
		
		UserAccountDt creator=theCourse.getCreator();
		int num=0;
		for (Section sec :theCourse.getSectionList()) {
			num+=sec.getLecturesList().size();
		}
		
		
		model.addAttribute("theCourse",theCourse);
		model.addAttribute("courseID", courseIntID);
		model.addAttribute("accountDt", acd);
		model.addAttribute("creator", creator);
		model.addAttribute("leactureTotalNum", num);
		return "course/teacherPage/courseToPlay";
	}

	@PostMapping("/edit")
	public String editpage(@AuthenticationPrincipal AccountUserDetails loggedAccount,@RequestParam(name = "courseID") Integer courseID, Model model) {
		UserAccountDt creator=loggedAccount.getLoggedAccount().getUserAccountDt();
		List<Category> cateList = new ArrayList<Category>();
		Iterable<Category> cate = caregoryRepo.findAll();
		cate.forEach(cateList::add);
		model.addAttribute("creator", creator);
		model.addAttribute("courseID", courseID);
		model.addAttribute("cateList", cateList);
		return "course/teacherPage/editcoursepage.html";
	}

	@GetMapping("/new")
	public String newpage(@AuthenticationPrincipal AccountUserDetails loggedAccount,Model model) {
		UserAccountDt creator=loggedAccount.getLoggedAccount().getUserAccountDt();
		CourseBasic courseBasic = new CourseBasic();
		List<Category> cateList = new ArrayList<Category>();
		Iterable<Category> cate = caregoryRepo.findAll();
		cate.forEach(cateList::add);
		model.addAttribute("courseBasic", courseBasic);
		model.addAttribute("cateList", cateList);
		model.addAttribute("creator", creator);
		return "course/teacherPage/newcoursepage.html";
	}

	@PostMapping("/processedCreateCourse")
	public String processedCreateCourse(@AuthenticationPrincipal AccountUserDetails loggedAccount,CourseBasic courseBasic, @RequestParam("poster") MultipartFile multipartfile,
			Model model) {
		int accountID = loggedAccount.getLoggedAccount().getUserId();
		UserAccountMt userAccount = accountService.findById(accountID).get();
		courseBasic.setCreator(userAccount.getUserAccountDt());
		
		try {
				courseService.createCourseSucessed(courseBasic);
				CourseBasic existedCourse = courseService.findCourseByUIDAndName(accountID, courseBasic.getCourseName());
				Integer courseID= existedCourse.getCourseID();
				if(multipartfile !=null && !multipartfile.isEmpty()) {
				courseService.updateCourseAbstractCover(multipartfile,courseID);
				}
				List<Category> cateList = new ArrayList<Category>();
				Iterable<Category> cate = caregoryRepo.findAll();
				cate.forEach(cateList::add);
				UserAccountDt creator=loggedAccount.getLoggedAccount().getUserAccountDt();
				model.addAttribute("creator", creator);
				model.addAttribute("courseID", courseID);
				model.addAttribute("cateList", cateList);
				return "course/teacherPage/editcoursepage.html";
			
		} catch (CourseDuplicatedException | NoCourseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/teacherPage/new";
		}

		
	}

}
