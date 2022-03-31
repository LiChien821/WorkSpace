package com.howhow.course.bulletin.dto;

import java.util.HashMap;

public class BulletinDTO {

	private Integer bulletinId;
	private String title;
	private String content;
	private String creationTime;
	private String launcherName;
	private Integer lectureId;
	private HashMap<Integer, BulletinReplyDTO> replies;
	
	public BulletinDTO(Integer bulletinId, String title, String content, 
			String creationTime, String launcherName, Integer lectureId, 
			HashMap<Integer, BulletinReplyDTO> replies) {
		this.bulletinId = bulletinId;
		this.title = title;
		this.content = content;
		this.creationTime = creationTime;
		this.launcherName = launcherName;
		this.lectureId = lectureId;
		this.replies = replies;
	}
	
	public BulletinDTO(Integer bulletinId, String title, String content, 
			String creationTime, String launcherName,
			HashMap<Integer, BulletinReplyDTO> replies) {
		this.bulletinId = bulletinId;
		this.title = title;
		this.content = content;
		this.creationTime = creationTime;
		this.launcherName = launcherName;
		this.replies = replies;
	}

	public Integer getBulletinId() {
		return bulletinId;
	}

	public void setBulletinId(Integer bulletinId) {
		this.bulletinId = bulletinId;
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

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getLauncherName() {
		return launcherName;
	}

	public void setLauncherName(String launcherName) {
		this.launcherName = launcherName;
	}

	public Integer getLectureId() {
		return lectureId;
	}

	public void setLectureId(Integer lectureId) {
		this.lectureId = lectureId;
	}

	public HashMap<Integer, BulletinReplyDTO> getReplies() {
		return replies;
	}

	public void setReplies(HashMap<Integer, BulletinReplyDTO> replies) {
		this.replies = replies;
	}



	
	
	
	
}
