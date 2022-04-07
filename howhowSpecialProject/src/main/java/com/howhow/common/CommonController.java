package com.howhow.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.howhow.account.service.UserAccountDtService;
import com.howhow.course.bulletin.service.BulletinReplyService;
import com.howhow.course.bulletin.service.BulletinService;
import com.howhow.course.common.LearningLecturesService;
import com.howhow.course.common.LearningSectionService;

@Controller
public class CommonController {

	@Autowired
	private BulletinService bService;
	
	@Autowired
	private LearningLecturesService llService;
	
	@Autowired
	private LearningSectionService lsService;
	
	@Autowired
	private BulletinReplyService brService;

	@Autowired
	private UserAccountDtService uadService;

	@Autowired
	private LearningLecturesService lService;

	@GetMapping("/navbar-test")
	public String processBulletin() {
		return "universal-navbar";
	}
	
	
}
