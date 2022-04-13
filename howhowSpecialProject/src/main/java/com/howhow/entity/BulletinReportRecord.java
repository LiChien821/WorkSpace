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
@Table(name = "bulletinreportrecord")
public class BulletinReportRecord {

	@Id
	@Column(name = "report_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reportID;

	@Transient
	private int userID;

	@Transient
	private int bulletinID;

	@Transient
	private int reporttype;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserAccountDt userdt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bulletin_id")
	private Bulletin bulletin;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reporttype")
	private ReportType typeobj;

	@Column(name = "system_time")
	private String systemtime;


	public int getReporttype() {
		return typeobj.getReporttype();
	}

	public String getSystemtime() {
		return systemtime;
	}

	public void setSystemtime(String systemtime) {
		this.systemtime = systemtime;
	}

	public int getReportID() {
		return reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}

	public int getBulletinID() {
		return bulletinID;
	}

	public void setBulletinID(int bulletinID) {
		this.bulletinID = bulletinID;
	}


	public UserAccountDt getUserdt() {
		return userdt;
	}

	public void setUserdt(UserAccountDt userdt) {
		this.userdt = userdt;
	}

	public int getUserID() {
		return userID;
	}

	public Bulletin getBulletin() {
		return bulletin;
	}

	public void setBulletin(Bulletin bulletin) {
		this.bulletin = bulletin;
	}

	public ReportType getTypeobj() {
		return typeobj;
	}

	public void setTypeobj(ReportType typeobj) {
		this.typeobj = typeobj;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void setReporttype(int reporttype) {
		this.reporttype = reporttype;
	}

}
