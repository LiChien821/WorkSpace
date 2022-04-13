package com.howhow.entity;

import javax.persistence.CascadeType;
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

@Entity
@Component
@Table(name = "bulletinreplyreportrecord")
public class BulletinReplyReportRecord {
	@Id
	@Column(name = "report_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reportID;

	@Transient
	private int userID;

	@Transient
	private int bulletinreplyID;

	@Transient
	private int reporttype;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserAccountDt userdt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bulletinreply_id")
	private BulletinReply bulletinreply;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reporttype")
	private ReportType typeobj;

	@Column(name = "system_time")
	private String systemtime;

	public int getReportID() {
		return reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getBulletinreplyID() {
		return bulletinreplyID;
	}

	public void setBulletinreplyID(int bulletinreplyID) {
		this.bulletinreplyID = bulletinreplyID;
	}

	public int getReporttype() {
		return reporttype;
	}

	public void setReporttype(int reporttype) {
		this.reporttype = reporttype;
	}


	public UserAccountDt getUserdt() {
		return userdt;
	}

	public void setUserdt(UserAccountDt userdt) {
		this.userdt = userdt;
	}

	public BulletinReply getBulletinreply() {
		return bulletinreply;
	}

	public void setBulletinreply(BulletinReply bulletinreply) {
		this.bulletinreply = bulletinreply;
	}

	public ReportType getTypeobj() {
		return typeobj;
	}

	public void setTypeobj(ReportType typeobj) {
		this.typeobj = typeobj;
	}

	public String getSystemtime() {
		return systemtime;
	}

	public void setSystemtime(String systemtime) {
		this.systemtime = systemtime;
	}
	
}
