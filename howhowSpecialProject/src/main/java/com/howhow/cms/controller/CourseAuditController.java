package com.howhow.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.cms.service.CourseStatusTypeService;
import com.howhow.entity.CourseBasic;
import com.howhow.shopping.exception.CourseNotFoundException;
import com.howhow.shopping.service.CourseBasicService;

@Controller
@RequestMapping("/cms")
public class CourseAuditController {

	@Autowired
	private CourseBasicService cbs;
	
	@Autowired
	private CourseStatusTypeService csts;

	@GetMapping("/courseaudit")
	public String guidToCourse() {
		return "cms/courseauditmain.html";
	}
	
	// 顯示所有未審核課程
	@ResponseBody
	@GetMapping("/coursedata/")
	public List<CourseBasic> showAllPendingApproval() {
		return cbs.findUnAuditCourses(2);
	}
	
	// 審核課程
	@ResponseBody
	@PutMapping("/coursedata/{status}")
	public List<CourseBasic> auditCourse(@RequestBody CourseBasic courseBasic, @PathVariable("status") int status) throws CourseNotFoundException {
		CourseBasic course = cbs.findByID(courseBasic.getCourseID());
		
		course.setStatusType(csts.findById(status));
		
		cbs.updateCourseBasic(course);
		
		return showAllPendingApproval();
	}
	
	
}
