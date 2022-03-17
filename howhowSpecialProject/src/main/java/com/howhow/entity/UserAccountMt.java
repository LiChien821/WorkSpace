package com.howhow.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity @Table(name="useraccountmt")
@Component
public class UserAccountMt {
	
	@Id @Column(name="UserID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int userId;
	
	@Column(name="Account")
	String account;
	
	@Column(name="Password")
	String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name="AccountLevel")
	private AccountLevel accountLevel =AccountLevel.Student;
	
	@Column(name="EmailAuth")
	private boolean emailAuth=false;
	
	@Column(name="Verificationcode")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String verificationcode;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private UserAccountDt userAccountDt;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private Set<PurchasedCourse> purchasedCourses = new HashSet<PurchasedCourse>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private Set<OrderMt> orderMts = new HashSet<OrderMt>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private Set<ShoppingCart> shoppingCarts = new HashSet<ShoppingCart>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private Set<CFCOrder> cfcOrders = new HashSet<CFCOrder>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private Set<FavoriteCourse> favoriteCourses = new HashSet<FavoriteCourse>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private Set<CourseRank> courseRanks = new HashSet<CourseRank>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private Set<UserBonus> userBonuses = new HashSet<UserBonus>();
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountLevel getAccountLevel() {
		return accountLevel;
	}

	public void setAccountLevel(AccountLevel accountLevel) {
		this.accountLevel = accountLevel;
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

	public UserAccountDt getUserAccountDt() {
		return userAccountDt;
	}

	public void setUserAccountDt(UserAccountDt userAccountDt) {
		this.userAccountDt = userAccountDt;
	}	
	
	
}
