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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.howhow.util.UtilityTool;

@Entity
@Component
@Table(name = "levelalterapply")
public class LevelAlterApply {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "apply_id")
	private int applyid;
	
	@Column(name = "apply_level")
	private String applylevel;
	
	@Column(name = "apply_status")
	private String applystatus;
	
	@Transient
	private Integer userid;
	
	@Column(name = "system_time")
	private String systemtime;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private UserAccountDt userAccountDt;

	public LevelAlterApply(UserAccountDt userDt) {
		applylevel = "Teacher";
		applystatus = "未處理";
		userAccountDt = userDt;
		systemtime = UtilityTool.getSysTime();
	}
	
	public LevelAlterApply() {
		
	}
	
	public int getApplyid() {
		return applyid;
	}

	public void setApplyid(int applyid) {
		this.applyid = applyid;
	}

	public String getApplylevel() {
		return applylevel;
	}

	public void setApplylevel(String applylevel) {
		this.applylevel = applylevel;
	}

	public String getSystemtime() {
		return systemtime;
	}

	public void setSystemtime(String systemtime) {
		this.systemtime = systemtime;
	}

	public UserAccountDt getUserAccountDt() {
		return userAccountDt;
	}

	public void setUserAccountDt(UserAccountDt userAccountDt) {
		this.userAccountDt = userAccountDt;
	}

	public String getApplystatus() {
		return applystatus;
	}

	public void setApplystatus(String applystatus) {
		this.applystatus = applystatus;
	}

	public Integer getUserid() {
		return this.userAccountDt.getUserId();
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	
}
