package com.charity.services.service.impl;

import com.charity.services.model.Email;
import com.charity.services.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

  private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

  @Autowired
  JavaMailSender javaMailSender;

  @Override
  public void sendEmail(final Email email) {
    LOGGER.debug("Sending email use GMAIL SMTP");
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(email.getMessageTo());
    message.setSubject(email.getSubject());
    message.setText(email.getMessageBody());
    try {
      javaMailSender.send(message);
      LOGGER.info("Email sent successfully");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
