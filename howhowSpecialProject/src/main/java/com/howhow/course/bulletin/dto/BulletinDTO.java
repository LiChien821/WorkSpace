package com.howhow.course.bulletin.dto;

import java.util.HashMap;
import java.util.List;

public class BulletinDTO {

	private Integer bulletinId;
	private String title;
	private String content;
	private String creationTime;
	private String launcherName;
	private Integer lectureId;
	private List<BulletinReplyDTO> replies;
	private Integer replyCount;
	private String sectionName;
	private String lectureName;
	
	public BulletinDTO(Integer bulletinId, String title, String content, 
			String creationTime, String launcherName, Integer lectureId, 
			List<BulletinReplyDTO> replies, Integer replyCount, String sectionName, String lectureName) {
		this.bulletinId = bulletinId;
		this.title = title;
		this.content = content;
		this.creationTime = creationTime;
		this.launcherName = launcherName;
		this.lectureId = lectureId;
		this.replies = replies;
		this.replyCount = replyCount;
		this.sectionName = sectionName;
		this.lectureName = lectureName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
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

	public List<BulletinReplyDTO> getReplies() {
		return replies;
	}

	public void setReplies(List<BulletinReplyDTO> replies) {
		this.replies = replies;
	}



	
	
	
	
}
