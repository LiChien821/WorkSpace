package com.howhow.shopping.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.howhow.entity.CourseRank;
import com.howhow.entity.UserAccountMt;

@Controller
public class CourseRankController {

	/*
	 * 以課程ID尋找該課程評價
	 * */
	public List<CourseRank> findCourseRankListByCID(int courseid) {
		
		return null;
	}
	
	/*
	 * 新增評價
	 * */
	public boolean insertCourseRank(CourseRank courseRank) {
		
		return false;
	}
	
	/*
	 * 刪除課程評價
	 * */
	public boolean deleteCourseRank(UserAccountMt user, int courseid) {
		
		return false;
	}
}