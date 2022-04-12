package com.howhow.util;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import com.howhow.account.service.AccountService;
import com.howhow.entity.UserAccountDt;
import com.howhow.entity.UserAccountMt;
import com.howhow.websecurity.AccountUserDetails;

import net.bytebuddy.utility.RandomString;

public class UtilityTool {

	@Autowired
	private AccountService service;
	
	
	public static String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURI().toString();
		System.out.println("siteURL+getServletPath" + siteURL + "," + request.getServletPath());
		return siteURL.replace(request.getServletPath(), "");

	}

	public static JavaMailSenderImpl prepareMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("howhowweb@gmail.com");
		mailSender.setPassword("sfjdvwbkiwxsyrow");

		Properties mailProperties = new Properties();
		mailProperties.setProperty("mail.smtp.auth", "true");
		mailProperties.setProperty("mail.smtp.starttls.enable", "true");

		mailSender.setJavaMailProperties(mailProperties);

		return mailSender;
	}

	public static void sendVerificationEmail(UserAccountMt acc, HttpServletRequest request)
			throws UnsupportedEncodingException, MessagingException, UnknownHostException {

		JavaMailSenderImpl mailSender = UtilityTool.prepareMailSender();

		String toAddress = acc.getUserAccountDt().getEmail();
		String subject = "好好學會員註冊認證信";
		String content = "Dear[[name]],Please click the&nbsp;<div><a href=\"[[URL]]\" target=\"_self\">verify </a><div><font face=\"Comic Sans MS\"></font></div></div>";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("howhowweb@gmail.com", "好好學");

		helper.setTo(toAddress);
		helper.setSubject(subject);
		String randomCode = RandomString.make(54);

		acc.setVerificationcode(randomCode);

		content = content.replace("[[name]]", acc.getAccount());
		String myip = "52.139.157.137";
		String azureUrl="https://howhowproject.azurewebsites.net";
		String verifyURL = myip + "/verify?code=" + acc.getVerificationcode()
				+ "&email=" + toAddress;
		System.out.println("/verify?code=" + randomCode + "&email=" + toAddress);
		content = content.replace("[[URL]]", verifyURL);

		helper.setText(content, true);
		mailSender.send(message);

	}

	public static String getSysTime() {
		DateTimeFormatter currentTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String strSysTime = currentTime.format(LocalDateTime.now());
		return strSysTime;
	}

	public static String getTokenEmail() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = "";
		if (principal instanceof AccountUserDetails) { // 帳號密碼登入
			email = ((AccountUserDetails) principal).getEmail();
		} else if (principal instanceof DefaultOidcUser) { // GOOGLE登入
			email = ((DefaultOidcUser) principal).getEmail();
		}

		return email;

	}

}