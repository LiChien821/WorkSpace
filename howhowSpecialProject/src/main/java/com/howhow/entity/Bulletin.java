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

@Entity @Table(name="bullet")
@Component
public class Bulletin {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id @Column
	private Integer bulletinid;
	
	@ManyToOne
	@JoinColumn
	private Lectures lectureid;
	
	@Column
	private String title;

	@Column
	private String content;

	@ManyToOne
	@JoinColumn
	private UserAccountDt userid;
	
	@OneToMany(mappedBy = "bulletinreplyid")
	private List<BulletinReply> bulletinReplyList = new ArrayList<BulletinReply>();
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column
	private Date creationtime;

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

	public UserAccountDt getUserid() {
		return userid;
	}

	public void setUserid(UserAccountDt userid) {
		this.userid = userid;
	}

	public Date getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(Date creationtime) {
		this.creationtime = creationtime;
	}

	public List<BulletinReply> getBulletinReplyList() {
		return bulletinReplyList;
	}

	public void setBulletinReplyList(List<BulletinReply> bulletinReplyList) {
		this.bulletinReplyList = bulletinReplyList;
	}

	
	
}
