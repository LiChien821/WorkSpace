package com.howhow.course.teacherpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.howhow.course.common.LearningAccountService;
import com.howhow.course.common.LearningCourseService;
import com.howhow.course.common.NoCourseException;
import com.howhow.entity.CourseBasic;
import com.howhow.entity.UserAccountMt;

@Controller
@RequestMapping("/teacherPage")
public class TeacherPageController {
	
	@Autowired
	private LearningCourseService courseService;
	
	@Autowired
	private LearningAccountService accountService;
	
	@GetMapping("/page")
	public String homepage(Model model) {
		int accountID=1;
		model.addAttribute("accountID",accountID);
		return "course/teacherPage/page.html";
	}
	
	@PostMapping("/play")
public String playpage(@RequestParam(name="courseId") Integer courseId,Model model) {
		model.addAttribute("courseId",courseId);
		return "course/teacherPage/dashboard";
	}
	
	
	
	@PostMapping("/edit")
	public String editpage(@RequestParam(name="courseId") Integer courseId, Model model) {
		System.out.println(courseId);
		model.addAttribute("courseId",courseId);
			return "course/teacherPage/editcoursepage.html";
		}
	
	
	
	@GetMapping("/new")
	public String newpage(Model model) {
		CourseBasic courseBasic=new CourseBasic();
		
		model.addAttribute("courseBasic",courseBasic);
	
		return "course/teacherPage/newcoursepage.html";
	}
	
	@PostMapping("/processedCreateCourse")
	public String processedCreateCourse(CourseBasic courseBasic,Model model) throws NoCourseException {
		int uid=1;
		UserAccountMt userAccount=accountService.findById(uid).get();
		courseBasic.setCreator(userAccount.getUserAccountDt());
		
		if(courseService.createCourseSucessed(courseBasic)) {
			CourseBasic existedCourse=courseService.findCourseByUIDAndName(uid,courseBasic.getCourseName());
			model.addAttribute("courseId",existedCourse.getCourseId());
			return "course/teacherPage/editcoursepage.html";
		}
		
		return "redirect:/teacherPage/new";
	}
	
}
