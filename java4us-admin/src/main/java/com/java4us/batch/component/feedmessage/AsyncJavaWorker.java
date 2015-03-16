/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.batch.component.feedmessage;

import com.java4us.amqp.feedmessage.Java4UsMQFeedMessageProducer;
import com.java4us.batch.component.Worker;
import com.java4us.commons.cache.CacheService;
import com.java4us.commons.component.utils.FeedMessageSearchConverter;
import com.java4us.commons.service.feed.FeedMessageService;
import com.java4us.commons.service.feed.FeederService;
import com.java4us.commons.service.feed.Java4usRSSFeedParser;
import com.java4us.commons.service.search.SearchService;
import com.java4us.commons.service.security.XSSSecurityService;
import com.java4us.domain.Feed;
import com.java4us.domain.FeedMessage;
import com.java4us.domain.Feeder;
import com.java4us.domain.common.enums.Category;
import com.sun.syndication.io.FeedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author turgay
 */
public class AsyncJavaWorker implements Worker {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncJavaWorker.class);

    @Autowired
    private FeederService feederService;

    @Autowired
    private FeedMessageService feedMessageService;

    @Autowired
    private Java4usRSSFeedParser java4usRSSFeedParser;

    @Autowired
    private Java4UsMQFeedMessageProducer feedMessageProducer;

    @Autowired
    private XSSSecurityService xSSSecurityService;

    @Autowired
    private CacheService cacheService;

    @Qualifier("java4UsCacheService")
    @Autowired
    private CacheService java4UsCacheService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private FeedMessageSearchConverter feedMessageSearchConverter;

    public void work() {
        String threadName = Thread.currentThread().getName();
        LOGGER.info("   " + threadName + " has began working.");
        List<FeedMessage> feedMessageList = new ArrayList<>();
        for (Feeder feeder : feederService.findAcceptedJavaFeedersFeeds()) {
            LOGGER.info(feeder.getDomain());
            for (Feed feed : feeder.getFeeds()) {
                Feed currentFeed = null;
                try {
                    currentFeed = java4usRSSFeedParser
                            .readFeed(feed.getLink());
                } catch (IllegalArgumentException | IOException | FeedException
                        | ParseException e) {
                    LOGGER.info("Java4us Parser error {}" + e.getMessage());
                }
                if (currentFeed != null) {
                    currentFeed.getEntries().stream().filter(feedMessage -> checkFeedMessageExists(feedMessage)).forEach(feedMessage -> {
                        LOGGER.info(feedMessage.getTitle());
                        feedMessage.setCategory(Category.JAVA);
                        feedMessage.setFeed(feed);
                        xSSSecurityService.cleanFeedMessageForXSS(feedMessage);
                        String description = feedMessage.getDescription();
                        feedMessage.setDescription(description.length() > 4000 ? description.substring(0, 3999) : description);
                        feedMessageProducer.execute(feedMessage);
                        feedMessageList.add(feedMessage);
                    });
                }
            }
        }
        LOGGER.debug("   " + threadName + " has completed work.");
        clearCache();
        if (!feedMessageList.isEmpty()) {
            searchService.bulkIndexFeedMessages(feedMessageSearchConverter.convertAll(feedMessageList));
        }

    }

    private void clearCache() {
        cacheService.flushCache();
        java4UsCacheService.flushCache();
    }

    private synchronized boolean checkFeedMessageExists(FeedMessage feedMessage) {
        return feedMessageService.findByLink(feedMessage.getLink().trim()) == null;
    }
}
