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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity @Table(name = "bulletinreply")
@Component
//@JsonIdentityInfo(property = "@id",generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class BulletinReply {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
	@Id @Column
	private Integer bulletinReplyID;
	
	@Transient	//修正by chien
	private Integer bulletinID;
	
	@Column
	private String title;
	
	@Column
	private String content;
	
	@Transient	//修正by chien
	private int userID;
=======
	@Id @Column(name = "bulletinreply_id")
	private Integer bulletinreplyid;
	
	@ManyToOne
	@JoinColumn(name = "bulletin_id")
//	@JsonBackReference
	@JsonIgnore
	private Bulletin bulletinid;
	
	@Column(name = "reply_content")
	private String replycontent;
>>>>>>> 5f1a41e80502b829cae4338d64c4272d7bcaf74b
	
	@ManyToOne
	@JoinColumn(name = "respondent")
	@JsonBackReference
	private UserAccountDt respondent;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
<<<<<<< HEAD
	@Column
	private Date creationTime;
	
	@ManyToOne(fetch = FetchType.LAZY) //修正by chien
	@JoinColumn(name="bulletin_id")
	private Bulletin bulletin;
	
	@ManyToOne(fetch=FetchType.LAZY)	//修正by chien
	@JoinColumn(name="user_id")
	private UserAccountDt userAccountDt;
=======
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
>>>>>>> 5f1a41e80502b829cae4338d64c4272d7bcaf74b

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

	public int getUserID() {
		return userID;
	}

<<<<<<< HEAD
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
=======
	public void setCreationtime(Date creationtime) {
		this.creationtime = creationtime;
	}
	
>>>>>>> 5f1a41e80502b829cae4338d64c4272d7bcaf74b
	
}
