/**
 * 
 */
package com.craneding.javamail;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Java Mail 练习
 * 
 * @author crane.ding
 * 
 */
public class JavaMailDemo {
	
	// 以下变量为用户根据自己的情况设置
	static final String smtphost = "smtp.***.com"; // 发送邮件服务器
	static final String user = "***@***.com"; // 邮件服务器登录用户名
	static final String password = "********"; // 邮件服务器登录密码
	static final String from = "***@***.com"; // 发送人邮件地址
	static final String to = "***@***.com"; // 接受人邮件地址
	static final String subject = "Java Mail Subject!"; // 邮件标题
	static final String body = "Java Mail Body!"; // 邮件内容
	
	/**
	 * @param args
	 * @throws MessagingException 
	 */
	public static void main(String[] args) throws MessagingException {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");// SMTP服务
		props.put("mail.smtp.starttls.enable","true");// SMTP加密
		props.put("mail.smtp.host", smtphost);// 发送邮件服务器
		props.put("mail.smtp.auth","true");// SMTP认证协议
		props.put("mail.debug", "true");// 显示调试信息
		
		// 网络连接身份验证
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		};
		
		Session ssn = Session.getInstance(props, authenticator);

		// 电子邮件信息
		MimeMessage message = new MimeMessage(ssn);

		// 发送邮箱地址
		InternetAddress fromAddress = new InternetAddress(from);
		message.setFrom(fromAddress);
		
		// 接收邮箱地址
		InternetAddress toAddress = new InternetAddress(to);
		message.addRecipient(Message.RecipientType.TO, toAddress);

		// 邮件主题
		message.setSubject(subject);
		// 邮件内容
		// message.setText(body);
		// 发送日期
		message.setSentDate(new Date());

		// 邮件内容:邮件正文+邮件附件
		Multipart multipart = new MimeMultipart();
		
		// 邮件正文
		MimeBodyPart bodyText = new MimeBodyPart();
		bodyText.setContent(body, "text/html;charset=Gb2312");
		multipart.addBodyPart(bodyText);
		
		// 邮件附件
		MimeBodyPart bodyFile = new MimeBodyPart();
		bodyFile.setDataHandler(new DataHandler(new FileDataSource("Test File.txt")));
		bodyFile.setFileName("Test File.txt");
		multipart.addBodyPart(bodyFile);
		
		message.setContent(multipart);
		
		// 发送邮件
		Transport.send(message);
		
		// Transport transport = ssn.getTransport("smtp");
		// transport.connect(smtphost, user, password);
		// transport.sendMessage(message,
		// message.getRecipients(Message.RecipientType.TO));
		// transport.close();
		
		System.out.println("发送成功!");
	}

}
