package com.howhow.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Component
@Table(name="REPORTRECORD")
public class ReportRecord {
	
	@Id @Column(name ="REPORTID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reportid;
	
	@Column(name ="USERID")
	private int userid;
	
	@Column(name ="QUESTIONID")
	private int questionid;
	
	@Column(name ="REPORTYPE")
	private int reporttype;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "SYSTEMTIME")
	private Date systemtime;

	public int getReportid() {
		return reportid;
	}

	public void setReportid(int reportid) {
		this.reportid = reportid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getQuestionid() {
		return questionid;
	}

	public void setQuestionid(int questionid) {
		this.questionid = questionid;
	}

	public int getReporttype() {
		return reporttype;
	}

	public void setReporttype(int reporttype) {
		this.reporttype = reporttype;
	}

	public Date getSystemtime() {
		return systemtime;
	}

	public void setSystemtime(Date systemtime) {
		this.systemtime = systemtime;
	}
	
	
}
