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
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity @Table(name = "ordermt")
@Component
public class OrderMt {
	
	@Id @Column(name = "order_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderID;
	
	@Column(name = "order_date")
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	private Date orderDate;
	
	@Transient
	private int userID;
	
	@Column(name = "total_price")
	private int totalPrice;
	
	@Transient
	private int payMethodID;
	
	@Transient
	private int orderStatusID;
	
	@Column(name = "system_time")
	private String systemTime;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderMt", cascade = CascadeType.ALL)
	private List<OrderDt> orderDtList = new ArrayList<OrderDt>();
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private UserAccountMt userAccountMt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pay_method_id")
	private PayMethodType payMethodType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order_status_id")
	private OrderStatusType orderStatusType;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="orderMt", cascade = CascadeType.ALL)
	private PaymentFlow paymentFlow;
	
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

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getPayMethodID() {
		return payMethodID;
	}

	public void setPayMethodID(int payMethodID) {
		this.payMethodID = payMethodID;
	}

	public int getOrderStatusID() {
		return orderStatusID;
	}

	public void setOrderStatusID(int orderStatusID) {
		this.orderStatusID = orderStatusID;
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

	public PayMethodType getPayMethodType() {
		return payMethodType;
	}

	public void setPayMethodtype(PayMethodType payMethodType) {
		this.payMethodType = payMethodType;
	}

	public OrderStatusType getOrderStatusType() {
		return orderStatusType;
	}

	public void setOrderStatusType(OrderStatusType orderStatusType) {
		this.orderStatusType = orderStatusType;
	}

	public PaymentFlow getPaymentFlow() {
		return paymentFlow;
	}

	public void setPaymentFlow(PaymentFlow paymentFlow) {
		this.paymentFlow = paymentFlow;
	}
	
	
}
