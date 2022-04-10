package com.howhow.paymentflow.ecpayment.controller;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howhow.shopping.exception.DuplicatedPurchasedCourseException;
import com.howhow.shopping.exception.OrderNotFoundException;
import com.howhow.student.api.PurchasedCourseController;
import com.howhow.student.exception.OrderStatusErrorException;

import ecpay.payment.integration.AllInOne;

@Controller
public class ECPayResultController {
	
	@Autowired
	PurchasedCourseController pController;
	
	
	public static AllInOne all = new AllInOne("");		 
	
	@PostMapping(value="/ECPayResult",  produces="text/html;charset=utf-8")
	@ResponseBody
	public String processPaymentResult2(HttpServletRequest request) throws NumberFormatException, OrderNotFoundException, OrderStatusErrorException, DuplicatedPurchasedCourseException {		
		
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
			String orderidString = dict.get("CustomField1");
			pController.insertPurchasedCourse(Integer.parseInt(orderidString));
			
			return "<h1>付款成功</h1>";			 
		}
		else
			return "<h1>付款失敗</h1>";	
	}
}