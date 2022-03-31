package com.howhow.ecpayment.controller;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ecpay.payment.integration.AllInOne;

@Controller
public class ECPayResultController {
	
	public static AllInOne all = new AllInOne("");		 
	
	@PostMapping(value="/ECPayResult",  produces="text/html;charset=utf-8")
	@ResponseBody
	public String processPaymentResult2(HttpServletRequest request) {		
		
		Hashtable<String, String> dict = new Hashtable<String, String>();
		Enumeration<String> enumeration = request.getParameterNames();
		while(enumeration.hasMoreElements()) {
			String paramName = enumeration.nextElement();
			String paramValue = request.getParameter(paramName);
			dict.put(paramName, paramValue);			
		}
		System.out.printf("【ECPayResult.java】用戶端付款成功後回傳「付款結果」通知給伺服端的參數們：\n%s\n", dict.toString());
		boolean checkStatus = all.compareCheckMacValue(dict); 
		if ("1".equals(dict.get("RtnCode")) && checkStatus==true ){
			return "<h1>付款成功</h1>";			 
		}
		else
			return "<h1>付款失敗</h1>";	
	}
}