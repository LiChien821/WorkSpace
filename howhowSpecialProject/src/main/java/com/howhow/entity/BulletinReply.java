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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name = "bulletinreply")
@Component
public class BulletinReply {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id @Column(name = "bulletinreply_id")
	private Integer bulletinreplyid;
	
	@ManyToOne
	@JoinColumn(name = "bulletin_id")
//	@JsonBackReference
	@JsonIgnore
	private Bulletin bulletinid;
	
	@Column(name = "reply_content")
	private String replycontent;
	
	@ManyToOne
	@JoinColumn(name = "respondent")
	@JsonBackReference
	private UserAccountDt respondent;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "creation_time")
	private Date creationtime;

	public Integer getBulletinreplyid() {
		return bulletinreplyid;
	}

	public void setBulletinreplyid(Integer bulletinreplyid) {
		this.bulletinreplyid = bulletinreplyid;
	}

	public Bulletin getBulletinid() {
		return bulletinid;
	}

	public void setBulletinid(Bulletin bulletinid) {
		this.bulletinid = bulletinid;
	}

	public String getReplycontent() {
		return replycontent;
	}

	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}

	public UserAccountDt getRespondent() {
		return respondent;
	}

	public void setRespondent(UserAccountDt respondent) {
		this.respondent = respondent;
	}

	public Date getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(Date creationtime) {
		this.creationtime = creationtime;
	}
	
}
