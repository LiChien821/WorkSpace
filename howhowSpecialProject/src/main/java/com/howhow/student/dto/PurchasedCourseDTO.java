package com.howhow.student.dto;

public class PurchasedCourseDTO {
	
	int userid;
	int courseid;
	
	public PurchasedCourseDTO() {
	}
	
	public PurchasedCourseDTO(int userid, int courseid) {
		this.userid = userid;
		this.courseid = courseid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	
	
}
