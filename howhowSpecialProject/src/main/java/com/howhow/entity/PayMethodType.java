package com.howhow.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity @Table(name="paymethodtype")
@Component
public class PayMethodType {
	
	@Id @Column(name="PayMethodID")
	private int payMethodID;

	@Column(name="PayMethodName")
	private String payMethodName;
	
	@Column(name="SystemTime")
	private String systemTime;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "payMethodtype", cascade = CascadeType.ALL)
	private Set<OrderMt> orderMts = new HashSet<OrderMt>();
	
}
