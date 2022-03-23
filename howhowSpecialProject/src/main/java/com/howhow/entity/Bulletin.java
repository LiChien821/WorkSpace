package com.howhow.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity @Table(name = "bulletin")
@Component
public class Bulletin {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id @Column(name = "bulletin_id")
	private Integer bulletinID;
	
	@Transient	//修正by chien
	private Lectures lectureid;
	
	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Transient
	private int userID;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "creation_time")
	private Date creationTime;
	
	@ManyToOne(fetch=FetchType.LAZY)	//修正by chien 3lines
	@JoinColumn(name="lecture_id")
	private Lectures lectures;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private UserAccountDt userAccountDt;

	public Lectures getLectureid() {
		return lectureid;
	}

	public void setLectureid(Lectures lectureid) {
		this.lectureid = lectureid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Lectures getLectures() {
		return lectures;
	}

	public void setLectures(Lectures lectures) {
		this.lectures = lectures;
	}

	public UserAccountDt getUserAccountDt() {
		return userAccountDt;
	}

	public void setUserAccountDt(UserAccountDt userAccountDt) {
		this.userAccountDt = userAccountDt;
	}

	public Integer getBulletinID() {
		return bulletinID;
	}

	public void setBulletinID(Integer bulletinID) {
		this.bulletinID = bulletinID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
	
	
	
}
