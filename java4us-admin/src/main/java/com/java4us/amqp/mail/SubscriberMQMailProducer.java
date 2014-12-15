package com.java4us.amqp.mail;

import com.java4us.common.model.SubscriberMailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by turgaycan on 12/12/14.
 */
public class SubscriberMQMailProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberMQMailProducer.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void execute(SubscriberMailDTO subscriberMailDTO) {
        LOGGER.info("execute...");
        amqpTemplate.convertAndSend("java4us.routingkey.subscriber.mail", subscriberMailDTO);
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

}
