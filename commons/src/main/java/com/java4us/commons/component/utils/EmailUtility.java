/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.component.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author turgay
 */
@Component
public class EmailUtility {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtility.class);

    @Autowired
    private SimpleMailMessage resetEmailTemplate;

    @Autowired
    private SimpleMailMessage newFeederEmailTemplate;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public void sendResetPasswordMail(String dear, String content, String toEmail) {

        String fromEmail = resetEmailTemplate.getFrom();
        String emailSubject = resetEmailTemplate.getSubject();
        String emailBody = String
                .format(resetEmailTemplate.getText(), dear, content);

        sendMail(toEmail, fromEmail, emailSubject, emailBody);

    }

    public void sendNewFeederWelcomeMail(String dear, String toEmail) {

        String fromEmail = newFeederEmailTemplate.getFrom();
        String emailSubject = newFeederEmailTemplate.getSubject();
        String emailBody = String
                .format(newFeederEmailTemplate.getText(), dear);

        sendMail(toEmail, fromEmail, emailSubject, emailBody);

    }

    private void sendMail(String toEmail, String fromEmail, String emailSubject, String emailBody) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject(emailSubject);
            helper.setText(emailBody);
            /*
              uncomment the following lines for attachment FileSystemResource
			  file = new FileSystemResource("attachment.jpg");
			  helper.addAttachment(file.getFilename(), file);
			 */
            javaMailSender.send(mimeMessage);
            LOGGER.info("Mail sent successfully.");
        } catch (MessagingException e) {
            LOGGER.error(" Email send error {} to {}" + toEmail + " " + e.getMessage());
        }
    }

    public void setResetEmailTemplate(SimpleMailMessage resetEmailTemplate) {
        this.resetEmailTemplate = resetEmailTemplate;
    }

    public void setNewFeederEmailTemplate(SimpleMailMessage newFeederEmailTemplate) {
        this.newFeederEmailTemplate = newFeederEmailTemplate;
    }

    public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

}
