/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.component.utils;

import com.java4us.commons.service.url.UrlService;
import com.java4us.domain.Subscriber;
import com.java4us.domain.model.SubscriberMailDTO;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.DateTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private UrlService urlService;

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
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, ENCODING);

            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject(emailSubject);
            helper.setText(emailBody);

            javaMailSender.send(mimeMessage);
            LOGGER.info("Mail sent successfully.");
        } catch (MessagingException e) {
            LOGGER.error(" Email send error {} to {}" + toEmail + " " + e.getMessage());
        }
    }

    public void sendSubscriberMail(SubscriberMailDTO subscriberMailDTO) throws Throwable {
        Subscriber subscriber = subscriberMailDTO.getSubscriber();
        byte[] bytesEncoded = Base64.encodeBase64(String.valueOf(subscriber.getId()).getBytes());
        String encodedId = new String(bytesEncoded);
        String rootUrl = urlService.getRootUrl();
        Map<String, Object> params = new HashMap<>();
        params.put("subscriber", subscriber);
        params.put("rootUrl", rootUrl);
        params.put("encodedId", encodedId);
        params.put("weeklyFeedMessages", subscriberMailDTO.getFeedMessageList());
        LocalDate localDate = LocalDateTime.now().toLocalDate();
        params.put("currentDate", localDate);
        sendEmailMessage(subscriberEmailTemplate.getFrom(), new String[]{(subscriber.getEmail())}, subscriberEmailTemplate.getSubject(),  subscriberEmailTemplate.getText(), params);

    }

    public void sendEmailMessage(String from, String[] to, String subject, String htmlBody, Map<String, Object> params) throws Throwable {

        MimeMessage msg = javaMailSender.createMimeMessage();

        msg.setFrom(new InternetAddress(from));
        msg.setSubject(subject);
        msg.setRecipients(Message.RecipientType.TO, getInternetAddresses(to));

        MimeMultipart content = new MimeMultipart("alternative");

        if (!StringUtils.isEmpty(htmlBody)) {
            MimeBodyPart html = new MimeBodyPart();
            html.setContent(mergeTemplate(htmlBody, params), "text/html");
            content.addBodyPart(html);
        }

        msg.setContent(content);
        msg.saveChanges();

        try {
            javaMailSender.send(msg);
        } catch (MailException ex) {
            LOGGER.info("Mail exception " + to[0], ex);
        }
    }

    public String mergeTemplate(String template, Map<String, Object> macros) throws Throwable {

        if (StringUtils.isEmpty(template)) {
            return StringUtils.EMPTY;
        }

        String answer = null;

        VelocityContext context = new VelocityContext();

        context.put("dateTool", new DateTool());

        for (String key : macros.keySet()) {
            context.put(key, macros.get(key));
        }
        StringWriter writer = new StringWriter();

        Velocity.init();

        if (Velocity.evaluate(context, writer, "LOG", template)) {
            IOUtils.closeQuietly(writer);
            answer = writer.toString();
        }
        return answer;
    }

    private InternetAddress[] getInternetAddresses(String... emails) throws Throwable {
        List<InternetAddress> addresses = new ArrayList<>();
        for (String email : emails) {
            addresses.add(new InternetAddress(email));
        }
        return addresses.toArray(new InternetAddress[0]);
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
