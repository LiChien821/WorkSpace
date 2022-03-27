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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name="purchasedcourse",
uniqueConstraints=@UniqueConstraint(columnNames={"course_id", "user_id"}))
@Component
public class PurchasedCourse {
	
	@Id @Column(name = "purchasedcourse_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int purchasedCourseID;
	
	@Column(name = "system_time")
	private String systemTime;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="course_id")
	private CourseBasic courseBasic;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private UserAccountMt userAccountMt;

	public PurchasedCourse() {
	}
	
	public PurchasedCourse(String systemTime, CourseBasic courseBasic, UserAccountMt userAccountMt) {
		super();
		this.systemTime = systemTime;
		this.courseBasic = courseBasic;
		this.userAccountMt = userAccountMt;
	}

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
