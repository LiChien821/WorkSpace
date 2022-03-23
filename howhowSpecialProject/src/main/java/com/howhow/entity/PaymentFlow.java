package com.howhow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class PaymentFlow {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentID;
	
	@Transient
	private int orderID;

	@Column
	private String merchantID;

	@Column
	private String merchantTradeNo;

	@Column
	private int rtnCode;

	@Column
	private String rtnMsg;

	@Column
	private String tradeNo;

	@Column
	private int tradeAmt;

	@Column
	private String paymentType;

	@Column
	private String tradeDate;

	@Column
	private String checkMacvalue;

	@Column
	private String systemTime;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	private OrderMt orderMt;

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	public String getMerchantTradeNo() {
		return merchantTradeNo;
	}

	public void setMerchantTradeNo(String merchantTradeNo) {
		this.merchantTradeNo = merchantTradeNo;
	}

	public int getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(int rtnCode) {
		this.rtnCode = rtnCode;
	}

	public String getRtnMsg() {
		return rtnMsg;
	}

	public void setRtnMsg(String rtnMsg) {
		this.rtnMsg = rtnMsg;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public int getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(int tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}

	public String getCheckMacvalue() {
		return checkMacvalue;
	}

	public void setCheckMacvalue(String checkMacvalue) {
		this.checkMacvalue = checkMacvalue;
	}

	public OrderMt getOrderMt() {
		return orderMt;
	}

	public void setOrderMt(OrderMt orderMt) {
		this.orderMt = orderMt;
	}
	
	
}