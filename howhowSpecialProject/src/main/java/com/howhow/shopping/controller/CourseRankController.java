package com.howhow.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.entity.CourseRank;
import com.howhow.entity.UserAccountMt;
import com.howhow.shopping.service.CourseRankService;

@Controller
public class CourseRankController {
	
	@Autowired
	CourseRankService cService;
	
	/*
	 * 以課程ID尋找該課程評價
	 * */
	@GetMapping("/querycourserank/{courserankid}")
	@ResponseBody
	public CourseRank findCourseRankListByCID(@PathVariable("courserankid") int courserankid) {
		
		CourseRank course = cService.findByCID(courserankid);
		
		return course;
	}
	
	/*
	 * 新增評價
	 * */
//	public boolean insertCourseRank(CourseRank courseRank) {
//		CourseRank insertCourseRank = cService.insertCourseRank(courseRank);
//		
//		if(insertCourseRank!=null) return true;
//		
//		return false;
//	}
	
	/*
	 * 刪除課程評價
	 * */
	public boolean deleteCourseRank(UserAccountMt user, int courseid) {
		CourseRank course = cService.findByCID(courseid);
		if(course!=null) {
			cService.deleteByCID(courseid);
			return true;
		}
		
		return false;
	}
	
	/*
	 * 依照Category搜尋課程評價List
	 * */
//	public List<CourseRank> findCourseRankByCategory(@PathVariable("pageNo") int pageNo, int categoryid, Model m) {
//		int pageSize = 5;
//		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
//		
//		Page<CourseRank> page = cService.findCourseRankByCategory(categoryid, pageable);
//		m.addAttribute("totalPages", page.getTotalPages());
//		m.addAttribute("totalElements", page.getTotalElements());
//		
//		return page.getContent();
//		
//	}
}