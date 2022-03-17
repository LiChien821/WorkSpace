package com.howhow.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

@Entity @Table(name="useraccountdt")
@Component
public class UserAccountDt {
	
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name="property", value="userAccountMt"))
	@Id
	@Column(name="UserID")
	@GeneratedValue(generator = "generator")
	private int userId;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="GivenName")
	private String givenName;
	
	@Column(name="FamilyName")
	private String familyName;
	
	@Column(name="Gender")
	private String gender;
	
	@Column(name="Birth")
	private Date birth;
	
	@Column(name="AccountCreationDate")
	private Date accountCreationDate;
	
	@Column(name="SystemTime")
	private String systemtime;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private UserAccountMt userAccountMt;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Date getAccountCreationDate() {
		return accountCreationDate;
	}

	public void setAccountCreationDate(Date accountCreationDate) {
		this.accountCreationDate = accountCreationDate;
	}

	public String getSystemtime() {
		return systemtime;
	}

	public void setSystemtime(String systemtime) {
		this.systemtime = systemtime;
	}

	public UserAccountMt getUserAccountMt() {
		return userAccountMt;
	}

	public void setUserAccountMt(UserAccountMt userAccountMt) {
		this.userAccountMt = userAccountMt;
	}
	
	
	
}
