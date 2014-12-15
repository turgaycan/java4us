/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.component.utils;

import com.java4us.common.model.SubscriberMailDTO;
import com.java4us.domain.Subscriber;
import org.apache.commons.codec.binary.Base64;
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

    private static final String ENCODING = "UTF-8";

    @Autowired
    private SimpleMailMessage resetEmailTemplate;

    @Autowired
    private SimpleMailMessage newFeederEmailTemplate;

    @Autowired
    private SimpleMailMessage subscriberEmailTemplate;

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

    public void sendSubscriberMail(SubscriberMailDTO subscriberMailDTO) {

        String fromEmail = subscriberEmailTemplate.getFrom();
        String emailSubject = subscriberEmailTemplate.getSubject();
        Subscriber subscriber = subscriberMailDTO.getSubscriber();
        byte[]   bytesEncoded = Base64.encodeBase64(String.valueOf(subscriber.getId()).getBytes());
        String encodedId = new String(bytesEncoded);
        String content = "";
        String emailBody = String
                .format(subscriberEmailTemplate.getText(), subscriber.fullName(), content, encodedId);

        sendMail(subscriber.getEmail(), fromEmail, emailSubject, emailBody);

    }

    private void sendMail(String toEmail, String fromEmail, String emailSubject, String emailBody) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, ENCODING);

            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject(emailSubject);
            helper.setText(emailBody);
//            LocalDate localDate = LocalDateTime.now().toLocalDate();
//            helper.setSentDate(localDate);
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

    public void setSubscriberEmailTemplate(SimpleMailMessage subscriberEmailTemplate) {
        this.subscriberEmailTemplate = subscriberEmailTemplate;
    }

    public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

}
