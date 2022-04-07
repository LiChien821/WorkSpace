package com.howhow.shopping.dto;

public class CourseRankDTO {
	
	private int courserankid;
	private int courseid;
	private int userid;
	private int rank;
	private String rankdate;
	private String message;
	private String coursename;
	private String usergivenname;
	
	public CourseRankDTO() {
	}
	
	public CourseRankDTO(int courserankid, int courseid, int userid, int rank, String rankdate, String message,
			String coursename, String usergivenname) {
		this.courserankid = courserankid;
		this.courseid = courseid;
		this.userid = userid;
		this.rank = rank;
		this.rankdate = rankdate;
		this.message = message;
		this.coursename = coursename;
		this.usergivenname = usergivenname;
	}
	public int getCourserankid() {
		return courserankid;
	}
	public void setCourserankid(int courserankid) {
		this.courserankid = courserankid;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getRankdate() {
		return rankdate;
	}
	public void setRankdate(String rankdate) {
		this.rankdate = rankdate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getUsergivenname() {
		return usergivenname;
	}
	public void setUsergivenname(String usergivenname) {
		this.usergivenname = usergivenname;
	}
	
	
	
	
}
