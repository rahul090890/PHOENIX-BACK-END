package com.concretepage.email.builder;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class HREmailSender {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String senderEmailId = "hr@pheonixetech.com";
	
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private IMailContentBuilder mailContentBuilder;

	public void prepareAndSend(String recipient, String subject, Map<String,String> dynamicFields, String mailTemplate) {
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
				messageHelper.setFrom(senderEmailId);
				messageHelper.setTo(recipient);
				messageHelper.setSubject(subject);
				String content = mailContentBuilder.build(dynamicFields, mailTemplate);
				log.info("The email content is " + content);
				messageHelper.setText(content, true);
			}
			
		};
		
		 mailSender.send(messagePreparator);
	}
}
