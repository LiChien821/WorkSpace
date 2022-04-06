package com.howhow.course.forother;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.howhow.course.common.LearningCourseService;
import com.howhow.entity.CourseBasic;
import com.howhow.entity.Section;
import com.howhow.entity.UserAccountDt;
import com.howhow.entity.UserAccountMt;
import com.howhow.websecurity.AccountUserDetails;

@Controller
public class ForOtherController {

	@Autowired
	private LearningCourseService courseService;

	
	@PostMapping("/play/{courseID}")
	public String playpage(@AuthenticationPrincipal AccountUserDetails loggedAccount ,@PathVariable(name = "courseID") String courseID, Model model) throws IOException {
		
		int courseIntID=Integer.parseInt(courseID);
		UserAccountMt amt=loggedAccount.getLoggedAccount();
		UserAccountDt acd=amt.getUserAccountDt();	
		
		CourseBasic theCourse=courseService.findCourseByCourseId(courseIntID);
		if(amt.getPurchasedCourseList().contains(theCourse)) {
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
