package com.howhow.cms.dto;

public class ReportDetailObj {
	private Integer reportedPerson;
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

}
