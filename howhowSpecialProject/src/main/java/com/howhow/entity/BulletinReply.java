package com.howhow.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity @Table
@Component
public class BulletinReply {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id @Column
	private Integer bulletinreplyid;
	
	@ManyToOne
	@JoinColumn
	private Integer bulletinid;
	
	@Column
	private String title;
	
	@Column
	private String content;
	
	@Column
	private UserAccountDt userid;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column
	private Date creationtime;

	public Integer getBulletinreplyid() {
		return bulletinreplyid;
	}

	public void setBulletinreplyid(Integer bulletinreplyid) {
		this.bulletinreplyid = bulletinreplyid;
	}

	public Integer getBulletinid() {
		return bulletinid;
	}

	public void setBulletinid(Integer bulletinid) {
		this.bulletinid = bulletinid;
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
	
	
	
}
