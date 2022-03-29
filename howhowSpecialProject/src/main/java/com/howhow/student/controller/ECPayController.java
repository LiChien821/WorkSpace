package com.howhow.student.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

//本控制器用以在接到用戶端(瀏覽器)送出的結帳請求時，利用綠界API產生一個只具有<form>元素的頁面來回應給用戶端，
//用戶端接收該頁面後將自動展開後續的結帳作業。
@Controller
public class ECPayController {	
	private static AllInOne all = new AllInOne("");
	
	//http://localhost:8080/ecpay/ECPayServer
	@RequestMapping(value="/ECPayProcess", produces="text/html;charset=utf-8") //預設response的字元編碼為ISO-8859-1
	@ResponseBody
	public String processPayment(HttpServletRequest request) {		
		String form = genAioCheckOutALL(request);		
		System.out.printf("【ECPayServer.java】產生了讓消費者付款的表單：\n%s\n",form);	
		//輸出內容請參考最下面區段。		
		return form; 		
	}
	
	private String genAioCheckOutALL(HttpServletRequest request ){	
		AioCheckOutALL obj = new AioCheckOutALL();
		//下列三項設定於payment_conf.xml
		//<MerchantID>2000132</MerchantID>
        //<HashKey>5294y06JbISpM5x9</HashKey>
        //<HashIV>v77hoKGq4kWxNNIS</HashIV>
		obj.setMerchantTradeNo(String.format("III%d", new Date().getTime()));	//特店交易編號均為唯一值，不可重複使用。英數字大小寫混合。
		obj.setMerchantTradeDate(String.format("%tY/%<tm/%<td %<tH:%<tM:%<tS", new Date() ) );	//特店交易時間。格式為：yyyy/MM/dd HH:mm:ss。
		
		obj.setTotalAmount( request.getParameter("TotalAmount") );	//交易金額。請帶整數，不可有小數點。僅限新台幣。
		obj.setTradeDesc( request.getParameter("TradeDesc") );		//交易描述。請勿帶入特殊字元。
		obj.setItemName( request.getParameter("ItemName") );		//商品名稱 
																	//1. 如果商品名稱有多筆，需在金流選擇頁一行一行顯示商品名稱的話，商品名稱請以符號#分隔。
																	//2. 商品名稱字數限制為中英數 400 字內，超過此限制系統將自動截斷。		
		obj.setNeedExtraPaidInfo("N");
		
		//***付款結果通知我們伺服端的方式(可二選一)***//
		//A.以Server端(綠界)方式直接回傳付款結果通知
		obj.setReturnURL("https://220.133.103.95/ecpay/ECPayServer2");
			//當消費者付款完成後，綠界會將付款結果參數以幕後(Server POST)回傳到該網址(該網址須是一個固定IP且使用https協定)。
			//必設欄位。但我們可以忽略相關的後續處理作業。
		//B.以Client端(消費者)方式回傳付款結果通知
		obj.setOrderResultURL("http://localhost:8080/ecpay/ECPayServer3"); 
			//當消費者付款完成後，綠界會將付款結果參數以幕前(Client POST)回傳到該網址。			
			//若與下面[ClientBackURL]同時設定，將會以此參數為主。		
		//********************************//
		
		//obj.setClientBackURL("http://localhost:8080/ecpay/ECPayServer3");
			//綠界付款成功畫面會增加「返回商店」按鈕，消費者點選此按鈕後，會將頁面導回到此設定的網址
			//(注意：導回時不會帶付款結果到此網址，只是將頁面(以GET方法)導回而已。)	
		
		String form = all.aioCheckOut(obj, null);
		return form;
	}	
}

//ECPayServer.java 產生了讓消費者付款的表單：
//<form id="allPayAPIForm" action="https://payment-stage.ecPay.com.tw/Cashier/AioCheckOut/V5" method="post">
//<input type="hidden" name="NeedExtraPaidInfo" value="N"><input type="hidden" name="ReturnURL" value="https://220.133.103.95/ecpay/ECPayServer2"><input type="hidden" name="BidingCard" value="">
//<input type="hidden" name="CheckMacValue" value="20CF70D1E6DE43ACF024DF8F579E02EA08EAF1C56D9D54B3A41211D1AAE8CE0D"><input type="hidden" name="StoreExpireDate" value=""><input type="hidden" name="PeriodAmount" value=""><input type="hidden" name="PaymentInfoURL" value=""><input type="hidden" name="ClientRedirectURL" value=""><input type="hidden" name="StoreID" value=""><input type="hidden" name="EncryptType" value="1"><input type="hidden" name="IgnorePayment" value=""><input type="hidden" name="CreditInstallment" value=""><input type="hidden" name="PaymentType" value="aio">
//<input type="hidden" name="OrderResultURL" value="http://220.133.103.95:8080/ecpay/ECPayServer3"><input type="hidden" name="PeriodReturnURL" value=""><input type="hidden" name="PlatformID" value=""><input type="hidden" name="MerchantMemberID" value=""><input type="hidden" name="Frequency" value=""><input type="hidden" name="ExpireDate" value=""><input type="hidden" name="ItemName" value="馬桶刷850元x1#消毒水200元"><input type="hidden" name="InvoiceMark" value="N"><input type="hidden" name="ExecTimes" value=""><input type="hidden" name="ChoosePayment" value="Credit"><input type="hidden" name="MerchantID" value="2000132"><input type="hidden" name="TradeDesc" value="刷兩三下就光亮如新"><input type="hidden" name="ClientBackURL" value=""><input type="hidden" name="PeriodType" value=""><input type="hidden" name="CustomField4" value=""><input type="hidden" name="Desc_4" value=""><input type="hidden" name="TotalAmount" value="1050"><input type="hidden" name="CustomField3" value=""><input type="hidden" name="Desc_3" value=""><input type="hidden" name="CustomField2" value=""><input type="hidden" name="Desc_2" value=""><input type="hidden" name="MerchantTradeDate" value="2021/08/31 11:09:08"><input type="hidden" name="CustomField1" value=""><input type="hidden" name="Desc_1" value=""><input type="hidden" name="ChooseSubPayment" value=""><input type="hidden" name="UnionPay" value=""><input type="hidden" name="InstallmentAmount" value=""><input type="hidden" name="MerchantTradeNo" value="III1630379348465"><input type="hidden" name="ItemURL" value=""><input type="hidden" name="Remark" value=""><input type="hidden" name="DeviceSource" value=""><input type="hidden" name="Language" value=""><input type="hidden" name="Redeem" value="">
//<script language="JavaScript">allPayAPIForm.submit()</script>
//</form>
/*
綠界要求「付款表單」需遵守下列規則：
1.action="https://payment-stage.ecpay.com.tw/Cashier/AioCheckOut/V5" (設定於~.config.EcpayPayment.xml)
2.enctype="application/x-www-form-urlencoded"
3.method="POST"
*/
