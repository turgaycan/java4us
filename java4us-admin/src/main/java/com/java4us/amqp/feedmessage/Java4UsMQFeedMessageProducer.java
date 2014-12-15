/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.amqp.feedmessage;

import com.java4us.domain.FeedMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author turgay
 */
public class Java4UsMQFeedMessageProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Java4UsMQFeedMessageProducer.class);

	@Autowired
    private AmqpTemplate amqpTemplate;

    public void execute(FeedMessage feedMessage) {
        LOGGER.info("execute...");
        amqpTemplate.convertAndSend("java4us.routingkey.feedMessage", feedMessage);
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

}
