package com.howhow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "paymentflow")
@Component
public class PaymentFlow {

	@Id
	@Column(name = "PAYMENTID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String paymentID;

	@Transient
	private int orderID;

	@Column(name = "MERCHANTID")
	private String merchantID;

	@Column(name = "MERCHANTTRADENO")
	private String merchantTradeNo;

	@Column(name = "RTNCODE")
	private int rtnCode;

	@Column(name = "RTNMSG")
	private String rtnMsg;

	@Column(name = "TRADENO")
	private String tradeNo;

	@Column(name = "TRADEAMT")
	private int tradeAmt;

	@Column(name = "PAYMENTTYPE")
	private String paymentType;

	@Column(name = "TRADEDATE")
	private String tradeDate;

	@Column(name = "CHECKMACVALUE")
	private String checkMacvalue;

	@Column(name = "SYSTEMTIME")
	private String systemTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDERID")
	private OrderMt orderMt;

	public String getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(String paymentID) {
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
}