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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

<<<<<<< HEAD
@Entity @Table
=======
@Entity @Table(name = "bulletin")
>>>>>>> 5f1a41e80502b829cae4338d64c4272d7bcaf74b
@Component
//@JsonIdentityInfo(property = "@id",generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Bulletin {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
	@Id @Column
	private Integer bulletinID;
=======
	@Id @Column(name = "bulletin_id")
	private Integer bulletinid;
>>>>>>> 5f1a41e80502b829cae4338d64c4272d7bcaf74b
	
	@ManyToOne
	@JoinColumn(name = "lecture_id")
	@JsonBackReference
	private Lectures lectureid;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "content")
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "launcher_id")
	@JsonBackReference
	private UserAccountDt launcherid;

<<<<<<< HEAD
	@Transient
	private int userID;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column
	private Date creationTime;
	
	@ManyToOne(fetch=FetchType.LAZY)	//修正by chien 3lines
	@JoinColumn(name="lecture_id")
	private Lectures lectures;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private UserAccountDt userAccountDt;
=======
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "creation_time")
	private Date creationtime;

	@OneToMany(mappedBy = "bulletinid")
//	@JsonManagedReference
//	@JsonIgnore
	private List<BulletinReply> replyList = new ArrayList<BulletinReply>(); 
	
	public Integer getBulletinid() { 
		return bulletinid;
	}

	public void setBulletinid(Integer bulletinid) {
		this.bulletinid = bulletinid;
	}
>>>>>>> 5f1a41e80502b829cae4338d64c4272d7bcaf74b


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
	
	
	public List<BulletinReply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<BulletinReply> replyList) {
		this.replyList = replyList;
	}

<<<<<<< HEAD
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
=======
	public Date getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(Date creationtime) {
		this.creationtime = creationtime;
	}

	public UserAccountDt getLauncherid() {
		return launcherid;
	}

	public void setLauncherid(UserAccountDt launcherid) {
		this.launcherid = launcherid;
>>>>>>> 5f1a41e80502b829cae4338d64c4272d7bcaf74b
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
