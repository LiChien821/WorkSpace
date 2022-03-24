package com.howhow.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "userstatus")
@Component
public class UserStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "userAccountMt"))
	@Id
	@Column(name = "USERID")
	@GeneratedValue(generator = "generator")
	private int userId;

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "userAccountMt"))
	@Column(name = "ACCOUNT", unique = true)
	private String account;
	

	@Column(name = "ACCOUNTSTATUS")
	private int accountStatus;

	@Column(name="EMAILAUTH")
	private boolean emailAuth = false;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ACCOUNTLEVEL")
	private AccountLevel accountLevel =AccountLevel.Student;

	@Column(name = "VERIFICATIONCODE")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String verificationcode;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "SYSTEMTIME")
	private java.util.Date systemTime;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERID", referencedColumnName = "USERID",insertable = false,updatable = false)
	private UserAccountMt userAccountMt;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Account", referencedColumnName = "ACCOUNT",insertable = false,updatable = false)
	private UserAccountMt userAccountMt2;


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public UserAccountMt getUserAccountMt2() {
		return userAccountMt2;
	}

	public void setUserAccountMt2(UserAccountMt userAccountMt2) {
		this.userAccountMt2 = userAccountMt2;
	}



	public int getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(int accountStatus) {
		this.accountStatus = accountStatus;
	}

	public boolean isEmailAuth() {
		return emailAuth;
	}

	public void setEmailAuth(boolean emailAuth) {
		this.emailAuth = emailAuth;
	}

	public String getVerificationcode() {
		return verificationcode;
	}

	public void setVerificationcode(String verificationcode) {
		this.verificationcode = verificationcode;
	}

	public java.util.Date getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(java.util.Date systemTime) {
		this.systemTime = systemTime;
	}

	public UserAccountMt getUserAccountMt() {
		return userAccountMt;
	}

	public void setUserAccountMt(UserAccountMt userAccountMt) {
		this.userAccountMt = userAccountMt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AccountLevel getAccountLevel() {
		return accountLevel;
	}

	public void setAccountLevel(AccountLevel accountLevel) {
		this.accountLevel = accountLevel;
	}
	
	
}
