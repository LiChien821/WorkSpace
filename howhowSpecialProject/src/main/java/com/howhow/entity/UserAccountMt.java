package com.howhow.entity;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name = "useraccountmt")
@Component
public class UserAccountMt implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id @Column(name="userid")	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int userId;
	
	@Column(name = "account")
	String account;
	
	@Column(name = "password")
	String password;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "systemtime")
	private java.util.Date systemTime;
	
	@Column(name = "VERIFICATIONCODE")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String verificationcode;
	
	public String getVerificationcode() {
		return verificationcode;
	}

	public void setVerificationcode(String verificationcode) {
		this.verificationcode = verificationcode;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private UserAccountDt userAccountDt;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private List<PurchasedCourse> purchasedCourseList = new ArrayList<PurchasedCourse>();
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private List<OrderMt> orderMtList = new ArrayList<OrderMt>();
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private List<ShoppingCart> shoppingCartList = new ArrayList<ShoppingCart>();
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private List<CFCOrder> cfcOrderList = new ArrayList<CFCOrder>();
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private List<FavoriteCourse> favoriteCourseList = new ArrayList<FavoriteCourse>();
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private List<CourseRank> courseRankList = new ArrayList<CourseRank>();
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private UserBonus userBonus;
	

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccountMt", cascade = CascadeType.ALL)
	private UserStatus userstatus;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public java.util.Date getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(java.util.Date systemTime) {
		this.systemTime = systemTime;
	}

	public UserAccountDt getUserAccountDt() {
		return userAccountDt;
	}

	public void setUserAccountDt(UserAccountDt userAccountDt) {
		this.userAccountDt = userAccountDt;
	}


	public UserStatus getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(UserStatus userstatus) {
		this.userstatus = userstatus;
	}

	public List<PurchasedCourse> getPurchasedCourseList() {
		return purchasedCourseList;
	}

	public void setPurchasedCourseList(List<PurchasedCourse> purchasedCourseList) {
		this.purchasedCourseList = purchasedCourseList;
	}

	public List<OrderMt> getOrderMtList() {
		return orderMtList;
	}

	public void setOrderMtList(List<OrderMt> orderMtList) {
		this.orderMtList = orderMtList;
	}

	public List<ShoppingCart> getShoppingCartList() {
		return shoppingCartList;
	}

	public void setShoppingCartList(List<ShoppingCart> shoppingCartList) {
		this.shoppingCartList = shoppingCartList;
	}

	public List<CFCOrder> getCfcOrderList() {
		return cfcOrderList;
	}

	public void setCfcOrderList(List<CFCOrder> cfcOrderList) {
		this.cfcOrderList = cfcOrderList;
	}

	public List<FavoriteCourse> getFavoriteCourseList() {
		return favoriteCourseList;
	}

	public void setFavoriteCourseList(List<FavoriteCourse> favoriteCourseList) {
		this.favoriteCourseList = favoriteCourseList;
	}

	public List<CourseRank> getCourseRankList() {
		return courseRankList;
	}

	public void setCourseRankList(List<CourseRank> courseRankList) {
		this.courseRankList = courseRankList;
	}

	public UserBonus getUserBonus() {
		return userBonus;
	}

	public void setUserBonus(UserBonus userBonus) {
		this.userBonus = userBonus;
	}
		
	
}
