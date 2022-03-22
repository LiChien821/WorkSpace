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

@Entity @Table(name="bullet")
@Component
public class Bulletin {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id @Column
	private Integer bulletinid;
	
	@Transient	//修正by chien
	private Lectures lectureid;
	
	@Column
	private String title;

	@Column
	private String content;

	@Transient
	private int userid;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column
	private Date creationtime;
	
	@ManyToOne(fetch=FetchType.LAZY)	//修正by chien 3lines
	@JoinColumn(name="LECTUREID")
	private Lectures lectures;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USERID")
	private UserAccountDt userAccountDt;
	
	public Integer getBulletinid() {
		return bulletinid;
	}

	public void setBulletinid(Integer bulletinid) {
		this.bulletinid = bulletinid;
	}

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

	public Date getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(Date creationtime) {
		this.creationtime = creationtime;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
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

	
	
}
