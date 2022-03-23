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
	@Column(name = "USER_ID")
	@GeneratedValue(generator = "generator")
	private int User_Id;

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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_Id", referencedColumnName = "USERID",insertable = false,updatable = false)
	@JoinColumn(name = "Account", referencedColumnName = "ACCOUNT",insertable = false,updatable = false)
	private UserAccountMt userAccountMt;

	public int getUserId() {
		return User_Id;
	}

	public void setUserId(int userId) {
		User_Id = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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
