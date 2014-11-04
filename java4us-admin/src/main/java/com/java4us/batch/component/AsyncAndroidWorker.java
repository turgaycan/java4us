/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.batch.component;

import java.io.IOException;
import java.text.ParseException;

import com.java4us.amqp.Java4UsMQFeedMessageProducer;
import com.java4us.commons.service.feed.FeedMessageService;
import com.java4us.commons.service.feed.FeederService;
import com.java4us.commons.service.feed.Java4usRSSFeedParser;
import com.java4us.domain.Feed;
import com.java4us.domain.FeedMessage;
import com.java4us.domain.Feeder;
import com.java4us.domain.common.enums.Category;
import com.sun.syndication.io.FeedException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author turgay
 */
public class AsyncAndroidWorker implements Worker {

	protected static Logger LOGGER = Logger.getLogger("asyncAndroidWorker");

	@Autowired
	private FeederService feederService;

	@Autowired
	private FeedMessageService feedMessageService;

	@Autowired
	private Java4usRSSFeedParser java4usRSSFeedParser;

	@Autowired
	private Java4UsMQFeedMessageProducer feedMessageProducer;

	public void work() {
		String threadName = Thread.currentThread().getName();
		LOGGER.info("   " + threadName + " has began working.");
		for (Feeder feeder : feederService.findAcceptedAndroidFeedersFeeds()) {
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
				for (FeedMessage feedMessage : currentFeed.getEntries()) {
					if (checkFeedMessageExists(feedMessage)) {
						LOGGER.info(feedMessage.getTitle());
						feedMessage.setCategory(Category.ANDROID);
						feedMessage.setFeed(feed);
						feedMessageProducer.execute(feedMessage);
					}
				}
			}
		}
		LOGGER.debug("   " + threadName + " has completed work.");
	}

	private boolean checkFeedMessageExists(FeedMessage feedMessage) {
		return feedMessageService.findByLink(feedMessage.getLink()) == null;
	}
}
