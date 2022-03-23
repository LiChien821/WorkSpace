package com.howhow.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity @Table(name = "bulletinreply")
@Component
public class BulletinReply {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id @Column(name = "bulletinreply_id")
	private Integer bulletinReplyID;
	
	@Transient	//修正by chien
	private Integer bulletinID;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "content")
	private String content;
	
	@Transient	//修正by chien
	private int userID;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "creation_time")
	private Date creationTime;
	
	@ManyToOne(fetch = FetchType.LAZY) //修正by chien
	@JoinColumn(name="bulletin_id")
	private Bulletin bulletin;
	
	@ManyToOne(fetch=FetchType.LAZY)	//修正by chien
	@JoinColumn(name="user_id")
	private UserAccountDt userAccountDt;

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

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Bulletin getBulletin() {
		return bulletin;
	}

	public void setBulletin(Bulletin bulletin) {
		this.bulletin = bulletin;
	}

	public UserAccountDt getUserAccountDt() {
		return userAccountDt;
	}

	public void setUserAccountDt(UserAccountDt userAccountDt) {
		this.userAccountDt = userAccountDt;
	}

	public Integer getBulletinReplyID() {
		return bulletinReplyID;
	}

	public void setBulletinReplyID(Integer bulletinReplyID) {
		this.bulletinReplyID = bulletinReplyID;
	}

	public Integer getBulletinID() {
		return bulletinID;
	}

	public void setBulletinID(Integer bulletinID) {
		this.bulletinID = bulletinID;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
}
