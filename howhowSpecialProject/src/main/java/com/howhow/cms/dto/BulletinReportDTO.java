package com.howhow.cms.dto;

public class BulletinReportDTO {
	private Integer reportid;
	private Integer reportedPerson;
	private Integer bulletionID;
	private String reportcontent;
	private String reporttypename;
	private String reporttime;

	public Integer getReportedPerson() {
		return reportedPerson;
	}

	public void setReportedPerson(Integer reportedPerson) {
		this.reportedPerson = reportedPerson;
	}

	public String getReportcontent() {
		return reportcontent;
	}

	public void setReportcontent(String reportcontent) {
		this.reportcontent = reportcontent;
	}

	public String getReporttypename() {
		return reporttypename;
	}

	public void setReporttypename(String reporttypename) {
		this.reporttypename = reporttypename;
	}

	public String getReporttime() {
		return reporttime;
	}

	public void setReporttime(String reporttime) {
		this.reporttime = reporttime;
	}

	public Integer getReportid() {
		return reportid;
	}

	public void setReportid(Integer reportid) {
		this.reportid = reportid;
	}

	public Integer getBulletionID() {
		return bulletionID;
	}

	public void setBulletionID(Integer bulletionID) {
		this.bulletionID = bulletionID;
	}

}
