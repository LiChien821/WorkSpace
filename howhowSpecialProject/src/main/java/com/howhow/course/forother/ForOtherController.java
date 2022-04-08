package com.howhow.course.forother;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.howhow.account.service.AccountService;
import com.howhow.course.common.LearningCourseService;
import com.howhow.entity.CourseBasic;
import com.howhow.entity.Section;
import com.howhow.entity.UserAccountDt;
import com.howhow.entity.UserAccountMt;
import com.howhow.student.service.PurchasedCourseService;
import com.howhow.util.UtilityTool;

@Controller
public class ForOtherController {

	@Autowired
	private LearningCourseService courseService;
	
	@Autowired
	private AccountService service;
	
	@Autowired
	private PurchasedCourseService pService;
	
	
	@GetMapping("/play/{courseID}")
	public String playpage(@PathVariable(name = "courseID") String courseID, Model model) throws IOException {
		
		UserAccountDt acd = service.findByEmail(UtilityTool.getTokenEmail());
		UserAccountMt amt = acd.getUserAccountMt();
		
		int courseIntID=Integer.parseInt(courseID);
		
		/*
		UserAccountMt amt=loggedAccount.getLoggedAccount();
		UserAccountDt acd=amt.getUserAccountDt();	
		*/
		CourseBasic theCourse=courseService.findCourseByCourseId(courseIntID);
		if(pService.findPurchasedStatus(acd.getUserId(), courseIntID)) {
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
		}else {
			return "redirect:/home";
		}
	}
}
