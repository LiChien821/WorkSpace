package com.howhow.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name = "ordermt")
@Component
public class OrderMt {
	
	@Id @Column(name = "order_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderID;
	
	@Column(name = "order_date")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	private Date orderDate;
	
	@Column(name = "total_price")
	private int totalPrice;
	
	@Column(name = "system_time")
	private String systemTime;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderMt", cascade = CascadeType.ALL)
	private List<OrderDt> orderDtList = new ArrayList<OrderDt>();
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private UserAccountMt userAccountMt;
	
	@Column(name="paymethodtype_id")
	private int payMethodTypeID;
	
	@Column(name="orderstatustype_id")
	private int orderStatusTypeID;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY, mappedBy="orderMt", cascade = CascadeType.ALL)
	private PaymentFlow paymentFlow;
	
	
	public OrderMt(UserAccountMt userAccountMt) {
		this.userAccountMt = userAccountMt;
		this.payMethodTypeID = 1;
		this.orderStatusTypeID = 1;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}

	public List<OrderDt> getOrderDtList() {
		return orderDtList;
	}

	public void setOrderDtList(List<OrderDt> orderDtList) {
		this.orderDtList = orderDtList;
	}

	public UserAccountMt getUserAccountMt() {
		return userAccountMt;
	}

	public void setUserAccountMt(UserAccountMt userAccountMt) {
		this.userAccountMt = userAccountMt;
	}

	public PaymentFlow getPaymentFlow() {
		return paymentFlow;
	}

	public void setPaymentFlow(PaymentFlow paymentFlow) {
		this.paymentFlow = paymentFlow;
	}

	public int getPayMethodTypeID() {
		return payMethodTypeID;
	}

	public void setPayMethodTypeID(int payMethodTypeID) {
		this.payMethodTypeID = payMethodTypeID;
	}

	public int getOrderStatusTypeID() {
		return orderStatusTypeID;
	}

	public void setOrderStatusTypeID(int orderStatusTypeID) {
		this.orderStatusTypeID = orderStatusTypeID;
	}
	
}
