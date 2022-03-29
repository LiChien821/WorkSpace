package com.howhow.ecpayment.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

@Controller
public class ECPayProcessController {	
	private static AllInOne all = new AllInOne("");
	
	@RequestMapping(value="/ECPayProcess", produces="text/html;charset=utf-8")
	@ResponseBody
	public String processPayment(HttpServletRequest request) {		
		String form = genAioCheckOutALL(request);		
		System.out.printf("ECPayController 產生消費者付款的表單：\n%s\n",form);	
		return form; 		
	}
	
	private String genAioCheckOutALL(HttpServletRequest request ){	
		AioCheckOutALL obj = new AioCheckOutALL();
		obj.setMerchantTradeNo(String.format("III%d", new Date().getTime()));	
		obj.setMerchantTradeDate(String.format("%tY/%<tm/%<td %<tH:%<tM:%<tS", new Date() ) );	
		obj.setTotalAmount( request.getParameter("TotalAmount") );	
		obj.setTradeDesc( request.getParameter("TradeDesc") );		
		obj.setItemName( request.getParameter("ItemName") );		
		obj.setNeedExtraPaidInfo("N");
		obj.setReturnURL("https://220.133.103.95/ecpay/ECPayServer2");
		obj.setOrderResultURL("http://localhost:8082/howhow/ECPayServer3"); 
		String form = all.aioCheckOut(obj, null);
		return form;
	}	
}