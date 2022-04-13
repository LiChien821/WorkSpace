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
import ecpay.payment.integration.domain.AioCheckOutOneTime;

@Controller
public class ECPayProcessCreditOneController {	
	private static AllInOne all = new AllInOne("");
	
	@RequestMapping(value="/ECPayProcessCreditOne", produces="text/html;charset=utf-8")
	@ResponseBody
	public String processPayment(HttpServletRequest request) throws UnknownHostException {		
		String form = genAioCheckOutOneTime(request);		
		System.out.printf("ECPayController 產生消費者付款的表單：\n%s\n",form);	
		return form; 		
	}
	
	private String genAioCheckOutOneTime(HttpServletRequest request ) throws UnknownHostException{	
		AioCheckOutOneTime obj = new AioCheckOutOneTime();
		
		// 須改，由前面order產生時的orderID傳到這
		obj.setMerchantTradeNo(String.format("III%d", new Date().getTime()));	
		obj.setMerchantTradeDate(String.format("%tY/%<tm/%<td %<tH:%<tM:%<tS", new Date() ) );	
		obj.setTotalAmount( request.getParameter("TotalAmount") );	
		obj.setTradeDesc( request.getParameter("TradeDesc") );		
		obj.setItemName( request.getParameter("ItemName") );	
		obj.setCustomField1(request.getParameter("CustomField1"));
		obj.setNeedExtraPaidInfo("N");
		obj.setReturnURL("https://220.133.103.95/ecpay/ECPayServer2");
		String myip = InetAddress.getLocalHost().getHostAddress();
		obj.setOrderResultURL("http://localhost/ECPayResult"); 
		String form = all.aioCheckOut(obj, null);
		return form;
	}	
}