package com.howhow.cms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.account.service.UserAccountDtService;
import com.howhow.cms.dto.CourseBasicDTO;
import com.howhow.cms.service.CourseStatusTypeService;
import com.howhow.cms.service.SystemMessageService;
import com.howhow.entity.CourseBasic;
import com.howhow.entity.SystemMessage;
import com.howhow.entity.UserAccountDt;
import com.howhow.shopping.exception.CourseNotFoundException;
import com.howhow.shopping.service.CourseBasicService;
import com.howhow.util.UtilityTool;

@Controller
@RequestMapping("/cms")
public class CourseAuditController {

	@Autowired
	private CourseBasicService cbs;

	@Autowired
	private CourseStatusTypeService csts;

	@Autowired
	private SystemMessageService sms;

	@GetMapping("/courseaudit")
	public String guidToCourse() {
		return "cms/courseauditmain.html";
	}

	// 顯示所有未審核課程
	@ResponseBody
	@GetMapping("/coursedata")
	public List<CourseBasicDTO> showAllPendingApproval() {
		List<CourseBasic> basics = cbs.findUnAuditCourses(2);
		List<CourseBasicDTO> basicDTOs = new ArrayList<CourseBasicDTO>();
		
		for(CourseBasic basic : basics) {
			CourseBasicDTO basicDTO = new CourseBasicDTO();
			
			basicDTO.setCourseID(basic.getCourseID());
			basicDTO.setCourseName(basic.getCourseName());
			basicDTO.setCategoryName(basic.getCategory().getName());
			basicDTO.setCreateID(basic.getCreator().getUserId());
			basicDTO.setDescript(basic.getDescription());
			basicDTO.setPrice(basic.getPrice());
			basicDTO.setStatus(basic.getStatusType().getStatusName());
			basicDTOs.add(basicDTO);
		}
		return basicDTOs;
	}

	// 審核課程
	@ResponseBody
	@PutMapping("/coursedata/{status}")
	public List<CourseBasicDTO> auditCourse(@RequestBody CourseBasicDTO courseBasic, @PathVariable("status") int status)
			throws CourseNotFoundException {
		CourseBasic course = cbs.findByID(courseBasic.getCourseID());
		course.setStatusType(csts.findById(status));

		cbs.updateCourseBasic(course);


		if (status == 1) {
			setMessage(course, "y", course.getCourseName() + " 課程審核通過");
		} else if (status == 3) {
			setMessage(course, "y", course.getCourseName() + " 課程審核未通過");
		}

		return showAllPendingApproval();
	}

	public void setMessage(CourseBasic course, String display, String note) {
		SystemMessage message = new SystemMessage();
		
		message.setUserdt(course.getCreator());
		message.setDisplay(display);
		message.setMessageContext(note);
		message.setSystemTime(UtilityTool.getSysTime());

		sms.insertSystemMessage(message);
	}
}
