/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.amqp;

import com.java4us.commons.service.feed.FeedMessageService;
import com.java4us.domain.FeedMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author turgay
 */
public class Java4UsMQFeedMessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Java4UsMQFeedMessageConsumer.class);

    @Autowired
    private FeedMessageService feedMessageService;

    public void handleFeedMessage(FeedMessage feedMessage) {
    	LOGGER.info("Feed Message start to persist {}" + feedMessage.getLink());
        feedMessageService.save(feedMessage);
        LOGGER.info("Feed Message persisted {}" + feedMessage.getLink());
    }

    public void setFeedMessageService(FeedMessageService feedMessageService) {
        this.feedMessageService = feedMessageService;
    }

}
