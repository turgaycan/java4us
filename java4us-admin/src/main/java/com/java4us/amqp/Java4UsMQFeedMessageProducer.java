/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.amqp;

import com.java4us.domain.FeedMessage;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author turgay
 */
public class Java4UsMQFeedMessageProducer {

	@Autowired
    private AmqpTemplate amqpTemplate;

    public void execute(FeedMessage feedMessage) {
        System.out.println("execute...");
        amqpTemplate.convertAndSend("java4us.routingkey.feedMessage", feedMessage);
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

}
