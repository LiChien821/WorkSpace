package com.howhow.student.dto;

public class PurchasedCourseDTO {
	
	int userid;
	int courseid;
	String coursename;
	String categoryname;
	
	public PurchasedCourseDTO() {
	}
	
	public PurchasedCourseDTO(int userid, int courseid) {
		this.userid = userid;
		this.courseid = courseid;
	}
	
	
	public PurchasedCourseDTO(int userid, int courseid, String coursename, String categoryname) {
		this.userid = userid;
		this.courseid = courseid;
		this.coursename = coursename;
		this.categoryname = categoryname;
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

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	
	
	
}
