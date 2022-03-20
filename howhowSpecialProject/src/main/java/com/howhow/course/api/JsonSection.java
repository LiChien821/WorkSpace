package com.howhow.course.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import com.howhow.entity.Lectures;
import com.howhow.entity.Section;

public class JsonSection {
		
	
	private int sectionID;
	
	
	private int sectionNumber;
	
	
	private String sectionName;
	
	private List<Lectures> lecturesList=new ArrayList<>();

	public JsonSection(Section section) {
		super();
		this.sectionID = section.getSectionID();
		this.sectionNumber =section.getSectionNumber();
		this.sectionName = section.getSectionName();
		this.lecturesList = section.getLecturesList();
	}

	public int getSectionID() {
		return sectionID;
	}

	public void setSectionID(int sectionID) {
		this.sectionID = sectionID;
	}

	public int getSectionNumber() {
		return sectionNumber;
	}

	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
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
}
