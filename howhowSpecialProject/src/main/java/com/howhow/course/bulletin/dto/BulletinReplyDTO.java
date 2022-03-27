package com.howhow.course.bulletin.dto;

import java.util.Date;
import java.util.HashMap;

public class BulletinReplyDTO {
	
	private Integer bulletinreplyid;
	private String replycontent;
	private String creationtime;
	private String respondentname;
	
	public BulletinReplyDTO(Integer bulletinreplyid, String replycontent, 
			String creationtime, String respondentname) {
		this.bulletinreplyid = bulletinreplyid;
		this.replycontent = replycontent;
		this.creationtime = creationtime;
		this.respondentname = respondentname;
	}

	public Integer getBulletinreplyid() {
		return bulletinreplyid;
	}

	public void setBulletinreplyid(Integer bulletinreplyid) {
		this.bulletinreplyid = bulletinreplyid;
	}

	public String getReplycontent() {
		return replycontent;
	}

	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}

	public String getCreationtime() {
		return creationtime;
	}

	public void setCreationTime(String creationtime) {
		this.creationtime = creationtime;
	}

	public String getRespondentname() {
		return respondentname;
	}

	public void setRespondentname(String respondentname) {
		this.respondentname = respondentname;
	}
	
	
}
