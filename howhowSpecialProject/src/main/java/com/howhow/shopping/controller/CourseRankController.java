package com.howhow.shopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.entity.CourseBasic;
import com.howhow.entity.CourseRank;
import com.howhow.entity.UserAccountMt;
import com.howhow.shopping.service.CourseRankService;
import com.howhow.test.service.TestService;

@Controller
public class CourseRankController {
	
	@Autowired
	CourseRankService cService;
	
	@Autowired
	TestService tService;
	
	/*
	 * 以課程ID尋找該課程評價
	 * */
	@GetMapping("/querycourserank/{courserankid}")
	@ResponseBody
	public CourseRank findCourseRankListByCID(@PathVariable("courserankid") int courserankid) {
		
		CourseRank course = cService.findByRID(courserankid);
		
		return course;
	}
	
	/*
	 * 新增評價
	 * */
	@GetMapping("/insertcourserank")
	@ResponseBody
	public CourseRank insertCourseRank(@RequestBody CourseRank courseRank) {
		UserAccountMt user = new UserAccountMt();
		CourseBasic course = new CourseBasic();
		
		user.setUserId(1);
		course.setCourseID(4);
		List<CourseRank> courseRankList = new ArrayList<CourseRank>();
		courseRankList.add(courseRank);
		tService.saveUser(user);
		
		CourseRank insertCourseRank = cService.insertCourseRank(courseRank);
		
		
		return insertCourseRank;
	}
	
	/*
	 * 刪除課程評價
	 * */
	public boolean deleteCourseRank(UserAccountMt user, int courseid) {
		CourseRank course = cService.findByRID(courseid);
		if(course!=null) {
			cService.deleteByCID(courseid);
			return true;
		}
		
		return false;
	}
	
	/*
	 * 依照Category搜尋課程評價List
	 * */
	@GetMapping("/querybycourseid/{courseid}")
	@ResponseBody
	public List<CourseRank> findCourseRankByCategory(@RequestParam("pageNo") int pageNo, @PathVariable("courseid") int courseid, Model m) {
		int pageSize = 5;
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		
		Page<CourseRank> page = cService.findCourseRankByCourseID(courseid, pageable);
		
		return page.getContent();
		
	}
}