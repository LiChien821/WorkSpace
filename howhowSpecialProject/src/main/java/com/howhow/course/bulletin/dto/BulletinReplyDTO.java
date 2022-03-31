package com.howhow.course.bulletin.dto;

public class BulletinReplyDTO {
	
	private Integer bulletinreplyId;
	private String replyContent;
	private String creationTime;
	private String respondentName;
	
	public BulletinReplyDTO(Integer bulletinreplyid, String replycontent, 
			String creationtime, String respondentname) {
		this.bulletinreplyId = bulletinreplyid;
		this.replyContent = replycontent;
		this.creationTime = creationtime;
		this.respondentName = respondentname;
	}

	public Integer getBulletinreplyId() {
		return bulletinreplyId;
	}

	public void setBulletinreplyId(Integer bulletinreplyId) {
		this.bulletinreplyId = bulletinreplyId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getRespondentName() {
		return respondentName;
	}

	public void setRespondentName(String respondentName) {
		this.respondentName = respondentName;
	}

	
	
	
}
