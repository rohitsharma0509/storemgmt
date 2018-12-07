package com.app.myproject.util;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Inject
	private JavaMailSender javaMailSender;

	public void sendEmail(String[] to, String[] cc, String[] bcc, String subject, String body) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			if(null != to && to.length>0){
				helper.setTo(to);
			}
			if(null != cc && cc.length>0){
				helper.setCc(cc);
			}
			if(null != bcc && bcc.length>0){
				helper.setBcc(bcc);
			}
			helper.setTo(String.join(",", to));
			helper.setText(body, true);
			helper.setSubject(subject);
			//helper.addAttachment("cat.jpg", file);
			javaMailSender.send(message);
			System.out.println("Mail sent successfully");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
