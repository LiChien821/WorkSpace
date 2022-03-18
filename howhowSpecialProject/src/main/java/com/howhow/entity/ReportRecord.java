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

@Entity
@Component
@Table(name = "reportrecord")
public class ReportRecord {

	@Id
	@Column(name = "REPORTID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reportid;

	@Transient
	private int userid;

	@Transient
	private int questionid;

	@Transient
	private int reporttype;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERID")
	private UserAccountMt usermt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUESTIONID")
	private Question question;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPORTTYPE")
	private ReportType typeobj;

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
		return usermt.getUserId();
	}


	public int getQuestionid() {
		return question.getQuestionID();
	}


	public int getReporttype() {
		return typeobj.getReporttype();
	}


	public Date getSystemtime() {
		return systemtime;
	}

	public void setSystemtime(Date systemtime) {
		this.systemtime = systemtime;
	}

}
