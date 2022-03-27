package com.howhow.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;


@Component
@Table(name = "reporttype")
@Entity
public class ReportType {
	
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(generator = "native")
	@Id @Column(name = "reporttype_id")
	private int reporttype;

	@Column(name = "report_name")
	private String reportname;

	@Column(name = "system_time")
	private String systemtime;

	public int getReporttype() {
		return reporttype;
	}

	public void setReporttype(int reporttype) {
		this.reporttype = reporttype;
	}

	public String getReportname() {
		return reportname;
	}

	public void setReportname(String reportname) {
		this.reportname = reportname;
	}

	public String getSystemtime() {
		return systemtime;
	}

	public void setSystemtime(String systemtime) {
		this.systemtime = systemtime;
	}

}
