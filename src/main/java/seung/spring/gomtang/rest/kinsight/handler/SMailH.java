package seung.spring.gomtang.rest.kinsight.handler;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component("sMailH")
public class SMailH {

	public void send(
			String host
			, String port
			, String userName
			, String password
			, String address
			, String personal
			, String to
			, String cc
			, String subject
			, String content
			) {
		
		try {
			
			Properties properties = new Properties();
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", port);
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.ssl.enable", "true");
			
			JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
			javaMailSenderImpl.setHost(properties.getProperty("mail.smtp.host"));
			javaMailSenderImpl.setPort(Integer.parseInt(properties.getProperty("mail.smtp.port")));
			javaMailSenderImpl.setUsername(userName);
			javaMailSenderImpl.setPassword(password);
			javaMailSenderImpl.setJavaMailProperties(properties);
			
			MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
			
			mimeMessage.setFrom(new InternetAddress(address, personal, "UTF-8"));
			
			if(to.contains("<")) {
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(
						to.split("<")[1].replaceAll(">", "").trim()
						, to.split("<")[0].trim()
						, "UTF-8"
						));
			} else {
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(
						to.trim()
						, ""
						, "UTF-8"
						));
			}
			if(cc != null) {
				for(String receiver : cc.split(",")) {
					if(cc.contains("<")) {
						mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(
								receiver.split("<")[1].replaceAll(">", "").trim()
								, receiver.split("<")[0].trim()
								, "UTF-8"
								));
					} else {
						mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(
								receiver.trim()
								, ""
								, "UTF-8"
								));
					}
				}
			}
			mimeMessage.setSubject(subject);
			mimeMessage.setContent(content, "text/html; charset=utf-8");
			JavaMailSender javaMailSender = javaMailSenderImpl;
			javaMailSender.send(mimeMessage);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
