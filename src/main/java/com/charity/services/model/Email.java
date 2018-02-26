package com.charity.services.model;

public class Email {

  private String messageTo;
  private String subject;
  private String messageBody;

  /**
   * Gets the messageTo.
   *
   * @return value of messageTo
   */
  public String getMessageTo() {
    return messageTo;
  }

  /**
   * Sets the given value to messageTo.
   *
   * @param messageTo value for messageTo.
   */
  public void setMessageTo(final String messageTo) {
    this.messageTo = messageTo;
  }

  /**
   * Gets the subject.
   *
   * @return value of subject
   */
  public String getSubject() {
    return subject;
  }

  /**
   * Sets the given value to subject.
   *
   * @param subject value for subject.
   */
  public void setSubject(final String subject) {
    this.subject = subject;
  }

  /**
   * Gets the messageBody.
   *
   * @return value of messageBody
   */
  public String getMessageBody() {
    return messageBody;
  }

  /**
   * Sets the given value to messageBody.
   *
   * @param messageBody value for messageBody.
   */
  public void setMessageBody(final String messageBody) {
    this.messageBody = messageBody;
  }
}
