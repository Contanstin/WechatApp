package com.hpmont.bean;

import com.sun.net.ssl.internal.ssl.Provider;

import javax.mail.*;
import javax.mail.internet.*;
import java.security.Security;
import java.util.Properties;

public class SendEmail {
	private static String TO = "zhangyongchao@hpmont.com";	//收件人地址
	private static String FROM = "zhangyongchao@hpmont.com";//发件人地址
	private static String HOST = "smtp.hpmont.com";			//发件人主机
	
	public static void sendRegisterMail(String tempPhone)
	{
		//设置SSL链接
		Security.addProvider(new Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		//获取属性
		Properties properties = System.getProperties();
		//设置邮件服务器
		properties.put("mail.smtp.host", HOST);									//设置smtp
		properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);	//设置SSL
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");
		properties.setProperty("mail.smtp.port", "465");
		properties.setProperty("mail.smtp.socketFactory.port", "465");
		properties.setProperty("mail.smtp.auth", "true");
		
		
		//建立邮件会话
		Session session = Session.getDefaultInstance(properties,new Authenticator()
				{
					public PasswordAuthentication getPasswordAuthentication()
					{//身份认证
						return new PasswordAuthentication(FROM, "Zyc270635144");
					} 
				}
		);
		
		//建立邮件对象
		try
		{
			//设置邮件的发件人、收件人、主题
	        MimeMessage message = new MimeMessage(session);
	        //设置 From: 发件人
	        message.setFrom(new InternetAddress(FROM));
	        //设置 To: 收件人
	        message.addRecipient(Message.RecipientType.TO,new InternetAddress(TO));
	        //设置 Subject: 主题
	        message.setSubject("Mont手机用户注册");
	        
	        //现在设置实际消息
	        String tempMsg = "新用户注册。用户手机号码为：";
	        tempMsg = tempMsg+tempPhone+"。";
	        message.setText(tempMsg);
	        
	        // 发送消息
	        Transport.send(message);
		}
		catch(MessagingException mex)
		{
			mex.printStackTrace();
		}
	}
	
	public static boolean sendMessageMail(String tempMail, String tempMess)
	{
		boolean tempBoolSend=false;
		//设置SSL链接
		Security.addProvider(new Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		//获取属性
		Properties properties = System.getProperties();
		//设置邮件服务器
		properties.put("mail.smtp.host", HOST);									//设置smtp
		properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);	//设置SSL
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");
		properties.setProperty("mail.smtp.port", "465");
		properties.setProperty("mail.smtp.socketFactory.port", "465");
		properties.setProperty("mail.smtp.auth", "true");
		
		//建立邮件会话
		Session session = Session.getDefaultInstance(properties,new Authenticator()
				{
					public PasswordAuthentication getPasswordAuthentication()
					{//身份认证
						return new PasswordAuthentication(FROM, "Zyc270635144");
					} 
				}
		);
		
		//建立邮件对象
		try
		{
			//设置邮件的发件人、收件人、主题
	         MimeMessage message = new MimeMessage(session);
	         // 设置 From:  发件人
	         message.setFrom(new InternetAddress(FROM));
	         // 设置 To: 收件人
	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(tempMail));
	         // 设置 Subject: 主题
	         message.setSubject("Hpmont authorization Status");
	         
	         //现在设置实际消息
	         String tempMsg = tempMess;
	         message.setText(tempMsg);
	         
	         //发送消息
	         Transport.send(message);
	         //
	         tempBoolSend = true;
		}
		catch(MessagingException mex)
		{
			mex.printStackTrace();
		}
		return tempBoolSend;
	}
}
