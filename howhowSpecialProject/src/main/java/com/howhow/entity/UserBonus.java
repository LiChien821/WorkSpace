package com.howhow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity @Table(name="userbonus")
@Component
public class UserBonus {
	
	@Id
	private int userID;
	
	@Column(name="BonusCount")
	private int bonusCount;
	
	@Column(name="SystemTime")
	private String systemTime;
	
	@ManyToOne(fetch=FetchType.LAZY)
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
