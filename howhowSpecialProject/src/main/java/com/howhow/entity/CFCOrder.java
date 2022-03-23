package com.howhow.entity;

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
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;

@Entity @Table(
uniqueConstraints=@UniqueConstraint(columnNames={"USER_ID", "CROWD_FUND_ID"}))
@Component
public class CFCOrder {
	
	@Id @Column(name = "cfcorder_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cfcOrderID;
	
	@Transient
	private int userID;
	
	@Transient
	private int crowdFundID;
	
	@Column(name = "system_time")
	private String systemTime;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private UserAccountMt userAccountMt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="crowd_fund_id")
	private CFCourse cFCourse;

	public String getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}

	public int getCfcOrderID() {
		return cfcOrderID;
	}

	public void setCfcOrderID(int cfcOrderID) {
		this.cfcOrderID = cfcOrderID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getCrowdFundID() {
		return crowdFundID;
	}

	public void setCrowdFundID(int crowdFundID) {
		this.crowdFundID = crowdFundID;
	}

	public UserAccountMt getUserAccountMt() {
		return userAccountMt;
	}

	public void setUserAccountMt(UserAccountMt userAccountMt) {
		this.userAccountMt = userAccountMt;
	}

	public CFCourse getcFCourse() {
		return cFCourse;
	}

	public void setcFCourse(CFCourse cFCourse) {
		this.cFCourse = cFCourse;
	}	
	
	
	
}
