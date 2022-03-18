package com.howhow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;

@Entity @Table(name="purchasedcourse", 
uniqueConstraints=@UniqueConstraint(columnNames={"COURSEID", "USERID"}))
@Component
public class PurchasedCourse {
	
	@Id @Column(name="PURCHASEDCOURSEID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int purchasedCourseID;
	
	@Transient
	private int courseID;
	
	@Transient
	private int userID;
	
	@Column(name="SYSTEMTIME")
	private String systemTime;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COURSEID")
	private CourseBasic courseBasic;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USERID")
	private UserAccountMt userAccountMt;

	public String getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}

	public int getPurchasedCourseID() {
		return purchasedCourseID;
	}

	public void setPurchasedCourseID(int purchasedCourseID) {
		this.purchasedCourseID = purchasedCourseID;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public CourseBasic getCourseBasic() {
		return courseBasic;
	}

	public void setCourseBasic(CourseBasic courseBasic) {
		this.courseBasic = courseBasic;
	}

	public UserAccountMt getUserAccountMt() {
		return userAccountMt;
	}

	public void setUserAccountMt(UserAccountMt userAccountMt) {
		this.userAccountMt = userAccountMt;
	}
	
	
	
}