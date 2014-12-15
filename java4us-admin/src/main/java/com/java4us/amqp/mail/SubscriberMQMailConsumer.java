package com.java4us.amqp.mail;

import com.java4us.common.model.SubscriberMailDTO;
import com.java4us.commons.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by turgaycan on 12/12/14.
 */
public class SubscriberMQMailConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberMQMailConsumer.class);

    @Autowired
    private EmailService emailService;

    public void handleSubscriber(SubscriberMailDTO subscriberMailDTO) {
        LOGGER.info("Subscriber sending to mail {}" + subscriberMailDTO.getSubscriber().getEmail());
        emailService.sendMailToSubscriber(subscriberMailDTO);
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}
