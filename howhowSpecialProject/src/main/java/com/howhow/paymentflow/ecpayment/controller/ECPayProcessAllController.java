package com.howhow.paymentflow.ecpayment.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

@Controller
public class ECPayProcessAllController {	
	private static AllInOne all = new AllInOne("");
	
	@RequestMapping(value="/ECPayProcessAll", produces="text/html;charset=utf-8")
	@ResponseBody
	public String processPayment(HttpServletRequest request) throws UnknownHostException {		
		String form = genAioCheckOutALL(request);		
		return form;
	}
	
	private String genAioCheckOutALL(HttpServletRequest request ) throws UnknownHostException{	
		AioCheckOutALL obj = new AioCheckOutALL();
		obj.setMerchantTradeNo(String.format("III%d", new Date().getTime()));	
		obj.setMerchantTradeDate(String.format("%tY/%<tm/%<td %<tH:%<tM:%<tS", new Date() ) );	
		obj.setTotalAmount( request.getParameter("TotalAmount") );	
		obj.setTradeDesc( request.getParameter("TradeDesc") );		
		obj.setItemName( request.getParameter("ItemName") );	
		obj.setCustomField1(request.getParameter("CustomField1"));
		obj.setNeedExtraPaidInfo("N");
		obj.setReturnURL("https://220.133.103.95/howhow/ECPayResponse");
		obj.setOrderResultURL("http://localhost/ECPayResult"); 
		String form = all.aioCheckOut(obj, null);
		return form;
	}
}