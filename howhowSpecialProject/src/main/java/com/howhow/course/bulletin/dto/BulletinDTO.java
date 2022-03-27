package com.howhow.course.bulletin.dto;

import java.util.Date;
import java.util.HashMap;

public class BulletinDTO {

	private Integer bulletinid;
	private String title;
	private String content;
	private String creationtime;
	private String launchername;
	private Integer lectureid;
	private HashMap<Integer, BulletinReplyDTO> replymap;
	
	public BulletinDTO(Integer bulletinid, String title, String content, 
			String creationtime, String launchername, Integer lectureid, 
			HashMap<Integer, BulletinReplyDTO> replymap) {
		this.bulletinid = bulletinid;
		this.title = title;
		this.content = content;
		this.creationtime = creationtime;
		this.launchername = launchername;
		this.lectureid = lectureid;
		this.replymap = replymap;
	}
	
	public BulletinDTO(Integer bulletinid, String title, String content, 
			String creationtime, String launchername,
			HashMap<Integer, BulletinReplyDTO> replymap) {
		this.bulletinid = bulletinid;
		this.title = title;
		this.content = content;
		this.creationtime = creationtime;
		this.launchername = launchername;
		this.replymap = replymap;
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

	public String getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(String creationTime) {
		this.creationtime = creationTime;
	}

	public String getLaunchername() {
		return launchername;
	}

	public void setLaunchername(String launchername) {
		this.launchername = launchername;
	}

	public Integer getLectureid() {
		return lectureid;
	}

	public void setLectureid(Integer lectureid) {
		this.lectureid = lectureid;
	}

	public HashMap<Integer, BulletinReplyDTO> getReplymap() {
		return replymap;
	}

	public void setReplymap(HashMap<Integer, BulletinReplyDTO> replymap) {
		this.replymap = replymap;
	}
	
	
	
	
}
