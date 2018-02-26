package com.charity.services.controller;

import com.charity.services.model.Email;
import com.charity.services.service.EmailService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

  private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

  @Autowired
  EmailService emailService;

  @RequestMapping(value = "/charity/services/v1/email", method = RequestMethod.POST)
  public ResponseEntity sendEmail(@RequestBody @Valid final Email email) {
    LOGGER.info("Calling Email service to send email");
    emailService.sendEmail(email);
    return ResponseEntity.ok().build();
  }
}
