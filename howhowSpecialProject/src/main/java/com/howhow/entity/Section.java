package com.howhow.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "section")
public class Section {
	
	@Id
	@Column(name = "section_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sectionID;
	
	@Column(name = "section_number")
	private int sectionNumber;
	
	@Column(name = "section_name")
	private String sectionName;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="course_id")
	private CourseBasic courseBasic;
	
	@OneToMany(mappedBy = "section",cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Lectures> lecturesList=new ArrayList<>();
	
	@Column(name = "system_time")
	private String SystemTime;


	public int getSectionID() {
		return sectionID;
	}

	public void setSectionID(int sectionID) {
		this.sectionID = sectionID;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}



	public List<Lectures> getLecturesList() {
		return lecturesList;
	}

	public void setLecturesList(List<Lectures> lecturesList) {
		this.lecturesList = lecturesList;
	}

	public int getSectionNumber() {
		return sectionNumber;
	}

	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}

	public CourseBasic getCourseBasic() {
		return courseBasic;
	}

	public void setCourseBasic(CourseBasic courseBasic) {
		this.courseBasic = courseBasic;
	}

	public String getSystemTime() {
		return SystemTime;
	}

	public void setSystemTime(String systemTime) {
		SystemTime = systemTime;
	}



}
