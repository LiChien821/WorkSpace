package com.howhow.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="lecturesBasic")
public class Lectures {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int lecturesID;
	
	@Column
	private String lecturesName;
	
	
	@Column
	private String attendedResource;
	
	@Column
	private String videoSource;
	
	@Column
	private boolean isAvailableToPreview;
	
	@Column
	private String previewViedeoUrl;
	
	
	@Column(name="SystemTime")
	private String SystemTime;
	
	@OneToMany(mappedBy = "lectures")
	private List<Question> questionList=new ArrayList<Question>();
	
	@OneToMany(mappedBy = "notedlecture")
	private List<Notes> notesList=new ArrayList<Notes>();
	
	@ManyToOne
	@JoinColumn(name="sectionID")
	private Section section;

	public int getLecturesID() {
		return lecturesID;
	}

	public void setLecturesID(int lecturesID) {
		this.lecturesID = lecturesID;
	}

	public String getLecturesName() {
		return lecturesName;
	}

	public void setLecturesName(String lecturesName) {
		this.lecturesName = lecturesName;
	}

	public String getAttendedResource() {
		return attendedResource;
	}

	public void setAttendedResource(String attendedResource) {
		this.attendedResource = attendedResource;
	}

	public String getVideoSource() {
		return videoSource;
	}

	public void setVideoSource(String videoSource) {
		this.videoSource = videoSource;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public String getPreviewViedeoUrl() {
		return previewViedeoUrl;
	}

	public void setPreviewViedeoUrl(String previewViedeoUrl) {
		this.previewViedeoUrl = previewViedeoUrl;
	}

	public boolean isAvailableToPreview() {
		return isAvailableToPreview;
	}

	public void setAvailableToPreview(boolean isAvailableToPreview) {
		this.isAvailableToPreview = isAvailableToPreview;
	}

	public List<Notes> getNotesList() {
		return notesList;
	}

	public void setNotesList(List<Notes> notesList) {
		this.notesList = notesList;
	}

	public String getSystemTime() {
		return SystemTime;
	}

	public void setSystemTime(String systemTime) {
		SystemTime = systemTime;
	}
	
	
	
}
