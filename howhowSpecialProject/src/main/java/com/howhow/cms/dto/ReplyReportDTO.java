package com.howhow.cms.dto;

public class ReplyReportDTO {
	private Integer reportid;
	private Integer reportedPerson;
	private Integer replyID;
	private String reportcontent;
	private String reporttypename;
	private String reporttime;

	public Integer getReportid() {
		return reportid;
	}

	public void setReportid(Integer reportid) {
		this.reportid = reportid;
	}

	public Integer getReportedPerson() {
		return reportedPerson;
	}

	public void setReportedPerson(Integer reportedPerson) {
		this.reportedPerson = reportedPerson;
	}

	public Integer getReplyID() {
		return replyID;
	}

	public void setReplyID(Integer replyID) {
		this.replyID = replyID;
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

}
