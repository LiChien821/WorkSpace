package com.howhow.util;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.howhow.entity.UserAccountMt;

import net.bytebuddy.utility.RandomString;

public class UtilityTool {
	public static String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURI().toString();
		System.out.println("siteURL+getServletPath" + siteURL + "," + request.getServletPath());
		return siteURL.replace(request.getServletPath(), "");

	}

	public static JavaMailSenderImpl prepareMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("fromeggtochicken@gmail.com");
		mailSender.setPassword("rvmqobkcoysopnjd");

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
		String subject = "Please verify your registration to  continue shopping";
		String content = "Dear[[name]],Please click the&nbsp;<div><a href=\"[[URL]]\" target=\"_self\">verify </a><div><font face=\"Comic Sans MS\"></font></div></div>";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("fromeggtochicken@gmail.com", "FromEggToChicken");

		helper.setTo(toAddress);
		helper.setSubject(subject);
		String randomCode = RandomString.make(54);

		acc.getUserstatus().setVerificationcode(randomCode);

		content = content.replace("[[name]]", acc.getAccount());
		String myip = InetAddress.getLocalHost().getHostAddress();

		String verifyURL = myip + UtilityTool.getSiteURL(request) + "/verify?code="
				+ acc.getUserstatus().getVerificationcode() + "&email=" + toAddress;
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

}
