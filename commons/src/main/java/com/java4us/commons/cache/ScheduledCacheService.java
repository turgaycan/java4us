package com.java4us.commons.cache;

import com.couchbase.client.protocol.views.Query;
import com.couchbase.client.protocol.views.View;
import com.couchbase.client.protocol.views.ViewResponse;
import com.couchbase.client.protocol.views.ViewRow;
import com.java4us.commons.component.utils.FeedMessageSearchConverter;
import com.java4us.commons.service.feed.FeedMessageService;
import com.java4us.commons.service.search.SearchService;
import com.java4us.domain.FeedMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Iterator;

@Component
public class ScheduledCacheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledCacheService.class);

    @Autowired
    private CacheService cacheService;

    @Qualifier("java4UsCacheService")
    @Autowired
    private CacheService java4UCacheService;

    @Autowired
    private FeedMessageService feedMessageService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private FeedMessageSearchConverter feedMessageSearchConverter;

    @Scheduled(cron = "0 0 0 1/1 * ?")
    public void flushCacheEveryDay() {
        LOGGER.info("Starting Clear Cache .." + LocalDateTime.now());
        cacheService.flushCache();
        java4UCacheService.flushCache();
        LOGGER.info("Finished Clear Cache .." + LocalDateTime.now());
    }

    @Scheduled(cron = "0 0/5 * 1/1 * ?")
    public void updateFeedMessagesFromCache() throws Exception {
        Query query = new Query();
        View java4UsView = java4UCacheService.getJava4UsView();
        if (java4UsView != null) {
            query.setReduce(false);
            ViewResponse result = java4UCacheService.getCouchbaseClient().query(java4UsView, query);
            if (result != null && result.getTotalRows() > 0) {
                Iterator<ViewRow> iterator = result.iterator();
                while (iterator.hasNext()) {
                    ViewRow row = iterator.next();
                    String feedMessageId = row.getId();
                    if (feedMessageId.startsWith("feedMessage_")) {
                        FeedMessage feedMessage = java4UCacheService.get(feedMessageId);
                        if(feedMessage == null){
                            continue;
                        }
                        feedMessageService.update(feedMessage);
                        LOGGER.debug("Started index..");
                        searchService.index(feedMessageSearchConverter.convert(feedMessage));
                        LOGGER.debug("Finished index..");
                    }
                }
            }
        }
    }
}
