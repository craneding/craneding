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
 * Java Mail ��ϰ
 * 
 * @author crane.ding
 * 
 */
public class JavaMailDemo {
	
	// ���±���Ϊ�û������Լ����������
	static final String smtphost = "smtp.***.com"; // �����ʼ�������
	static final String user = "***@***.com"; // �ʼ���������¼�û���
	static final String password = "********"; // �ʼ���������¼����
	static final String from = "***@***.com"; // �������ʼ���ַ
	static final String to = "***@***.com"; // �������ʼ���ַ
	static final String subject = "Java Mail Subject!"; // �ʼ�����
	static final String body = "Java Mail Body!"; // �ʼ�����
	
	/**
	 * @param args
	 * @throws MessagingException 
	 */
	public static void main(String[] args) throws MessagingException {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");// SMTP����
		props.put("mail.smtp.starttls.enable","true");// SMTP����
		props.put("mail.smtp.host", smtphost);// �����ʼ�������
		props.put("mail.smtp.auth","true");// SMTP��֤Э��
		props.put("mail.debug", "true");// ��ʾ������Ϣ
		
		// �������������֤
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		};
		
		Session ssn = Session.getInstance(props, authenticator);

		// �����ʼ���Ϣ
		MimeMessage message = new MimeMessage(ssn);

		// ���������ַ
		InternetAddress fromAddress = new InternetAddress(from);
		message.setFrom(fromAddress);
		
		// ���������ַ
		InternetAddress toAddress = new InternetAddress(to);
		message.addRecipient(Message.RecipientType.TO, toAddress);

		// �ʼ�����
		message.setSubject(subject);
		// �ʼ�����
		// message.setText(body);
		// ��������
		message.setSentDate(new Date());

		// �ʼ�����:�ʼ�����+�ʼ�����
		Multipart multipart = new MimeMultipart();
		
		// �ʼ�����
		MimeBodyPart bodyText = new MimeBodyPart();
		bodyText.setContent(body, "text/html;charset=Gb2312");
		multipart.addBodyPart(bodyText);
		
		// �ʼ�����
		MimeBodyPart bodyFile = new MimeBodyPart();
		bodyFile.setDataHandler(new DataHandler(new FileDataSource("Test File.txt")));
		bodyFile.setFileName("Test File.txt");
		multipart.addBodyPart(bodyFile);
		
		message.setContent(multipart);
		
		// �����ʼ�
		Transport.send(message);
		
		// Transport transport = ssn.getTransport("smtp");
		// transport.connect(smtphost, user, password);
		// transport.sendMessage(message,
		// message.getRecipients(Message.RecipientType.TO));
		// transport.close();
		
		System.out.println("���ͳɹ�!");
	}

}
