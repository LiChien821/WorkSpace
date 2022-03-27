package com.howhow.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name = "paymethodtype")
@Component
public class PayMethodType {
	
	@Id @Column(name = "paymethod_id")
	private int payMethodID;

	@Column(name = "paymethod_name")
	private String payMethodName;
	
	@Column(name = "system_time")
	private String systemTime;

	public int getPayMethodID() {
		return payMethodID;
	}

	public void setPayMethodID(int payMethodID) {
		this.payMethodID = payMethodID;
	}

	public String getPayMethodName() {
		return payMethodName;
	}

	public void setPayMethodName(String payMethodName) {
		this.payMethodName = payMethodName;
	}

	public String getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}
	
}
