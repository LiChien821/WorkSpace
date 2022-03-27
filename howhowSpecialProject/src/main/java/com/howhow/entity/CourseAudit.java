package com.howhow.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "courseaudit")
@Component
public class CourseAudit {
	@Id @Column(name="audit_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int auditId;
	
	@Column(name = "content")
	private String content;
	
	@Transient
	private int courseId;
	
	@Transient
	private int courseStatus;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "course_status")
	private CourseStatusType statusType;
	
	@Column(name = "system_time")
	private String systemtime;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "course_id")
	private CourseBasic course;

	public int getAuditId() {
		return auditId;
	}

	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(int courseStatus) {
		this.courseStatus = courseStatus;
	}

	public CourseStatusType getStatusType() {
		return statusType;
	}

	public void setStatusType(CourseStatusType statusType) {
		this.statusType = statusType;
	}

	public String getSystemtime() {
		return systemtime;
	}

	public void setSystemtime(String systemtime) {
		this.systemtime = systemtime;
	}

	public CourseBasic getCourse() {
		return course;
	}

	public void setCourse(CourseBasic course) {
		this.course = course;
	}

	
}
