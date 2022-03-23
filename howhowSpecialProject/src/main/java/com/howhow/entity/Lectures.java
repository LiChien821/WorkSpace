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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="lecturesBasic")
@JsonIdentityInfo(property = "@id",generator = ObjectIdGenerators.IntSequenceGenerator.class)
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
	
	
	@Column(name="SYSTEMTIME")
	private String SystemTime;
	
//	@OneToMany(mappedBy = "lectures")                                 //modified by weijie(2022-03-22)
//	private List<Question> questionList=new ArrayList<Question>();
	@OneToMany(mappedBy = "lectureid")                                //modified by weijie(2022-03-22)
	@JsonManagedReference
	private List<Bulletin> bulletinList=new ArrayList<Bulletin>();    
	
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

//	public List<Question> getQuestionList() {                          //modified by weijie(2022-03-22)
//		return questionList;                                           
//	}
//
//	public void setQuestionList(List<Question> questionList) {         //modified by weijie(2022-03-22)
//		this.questionList = questionList;                              
//	}

	public Section getSection() {
		return section;
	}

	public List<Bulletin> getBulletinList() {                         //modified by weijie(2022-03-22)
		return bulletinList;
	}

	public void setBulletinList(List<Bulletin> bulletinList) {        //modified by weijie(2022-03-22)
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
	
	
	
}
