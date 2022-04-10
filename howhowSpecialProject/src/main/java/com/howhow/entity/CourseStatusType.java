package com.howhow.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "coursestatustype")
public class CourseStatusType {
	
	@Id
	@Column(name = "status_id")
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(generator = "native")
	private int statusID;

	@Column(name = "status_name")
	private String statusName;

	@Column(name = "system_time")
	private String systemTime;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "statusType")
	private List<CourseAudit> audits = new ArrayList<CourseAudit>();

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}

	public List<CourseAudit> getAudits() {
		return audits;
	}

	public void setAudits(List<CourseAudit> audits) {
		this.audits = audits;
	}

}
