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

@Entity @Table(name="bulletin")
@Component
//@JsonIdentityInfo(property = "@id",generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Bulletin {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id @Column(name = "BULLETINID")
	private Integer bulletinid;
	
	@ManyToOne
	@JoinColumn(name = "LECTURESID")
	@JsonBackReference
	private Lectures lectureid;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "CONTENT")
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "LAUNCHERID")
	@JsonBackReference
	private UserAccountDt launcherid;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "CREATIONTIME")
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
	}

	
	
}
