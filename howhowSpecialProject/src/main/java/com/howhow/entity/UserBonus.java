package com.howhow.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

@Entity @Table(name = "userbonus")
@Component
public class UserBonus implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "userAccountMt"))
	@Id
	@Column(name = "user_id")
	@GeneratedValue(generator = "generator")
	private int userID;
	
	@Column(name = "bonus_count")
	private int bonusCount;
	
	@Column(name = "system_time")
	private String systemTime;
	
	@MapsId
	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private UserAccountMt userAccountMt;

	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getBonusCount() {
		return bonusCount;
	}

	public void setBonusCount(int bonusCount) {
		this.bonusCount = bonusCount;
	}

	public String getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}

	public UserAccountMt getUserAccountMt() {
		return userAccountMt;
	}

	public void setUserAccountMt(UserAccountMt userAccountMt) {
		this.userAccountMt = userAccountMt;
	}
	
}
