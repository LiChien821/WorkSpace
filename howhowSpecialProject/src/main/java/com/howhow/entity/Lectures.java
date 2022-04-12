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
@Table(name = "lectures")
public class Lectures {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lectures_id")
	private int lecturesID;
	
	@Column(name = "lectures_name")
	private String lecturesName;
	
	@Column
	private int lectureNumber;
	
	@Column(name = "attended_resource")
	private String attendedResource;
	
	@Column(name = "video_source")
	private String videoSource;
	
	@Column(name = "isavailabletopreview")
	private boolean isAvailableToPreview;
	
	@Column(name = "previewvideo_url")
	private String previewViedeoUrl;
	
	@Column(name = "system_time")
	private String SystemTime;
	
	@Column
	private String Hint;
	
//	@OneToMany(mappedBy = "lectures")
//	private List<Question> questionList=new ArrayList<Question>();
	@OneToMany(mappedBy = "lectureid",cascade=CascadeType.REMOVE,orphanRemoval = true)                                //modified by weijie(2022-03-22)
	@JsonIgnore
	private List<Bulletin> bulletinList=new ArrayList<Bulletin>(); 
	
	@JsonIgnore   //modified by chien-lin(2022-03-31)
	@OneToMany(mappedBy = "notedlecture",cascade=CascadeType.REMOVE,orphanRemoval = true)
	private List<Notes> notesList=new ArrayList<Notes>();
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="section_id")
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

//	public List<Question> getQuestionList() {
//		return questionList;
//	}
//
//	public void setQuestionList(List<Question> questionList) {
//		this.questionList = questionList;
//	}

	public Section getSection() {
		return section;
	}

	public List<Bulletin> getBulletinList() {
		return bulletinList;
	}

	public void setBulletinList(List<Bulletin> bulletinList) {
		this.bulletinList = bulletinList;
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

	public int getLectureNumber() {
		return lectureNumber;
	}

	public void setLectureNumber(int lectureNumber) {
		this.lectureNumber = lectureNumber;
	}

	public String getHint() {
		return Hint;
	}

	public void setHint(String hint) {
		Hint = hint;
	}
	
	
	
}
