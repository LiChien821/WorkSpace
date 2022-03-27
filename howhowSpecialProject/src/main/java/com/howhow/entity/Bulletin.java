package com.howhow.entity;

import java.util.ArrayList;
import java.util.Date;
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

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name = "bulletin")
@Component
public class Bulletin {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id @Column(name = "bulletin_id")
	private Integer bulletinid;
	
	@ManyToOne
	@JoinColumn(name = "lecture_id")
	@JsonIgnore
	private Lectures lectureid;
	
	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@ManyToOne
	@JoinColumn(name = "launcherid")
	@JsonIgnore
	private UserAccountDt launcherid;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "creation_time")
	private Date creationTime;

	@OneToMany(mappedBy = "bulletinid")
	@JsonIgnore
	private List<BulletinReply> replyList = new ArrayList<BulletinReply>(); 
	
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

	public Integer getBulletinid() {
		return bulletinid;
	}

	public void setBulletinid(Integer bulletinid) {
		this.bulletinid = bulletinid;
	}

	public UserAccountDt getLauncherid() {
		return launcherid;
	}

	public void setLauncherid(UserAccountDt launcherid) {
		this.launcherid = launcherid;
	}

	public List<BulletinReply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<BulletinReply> replyList) {
		this.replyList = replyList;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
}
