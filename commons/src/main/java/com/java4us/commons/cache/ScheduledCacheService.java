package com.java4us.commons.cache;

import com.couchbase.client.protocol.views.Query;
import com.couchbase.client.protocol.views.ViewResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduledCacheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledCacheService.class);

    @Autowired
    private CacheService cacheService;

    @Qualifier("java4UsCacheService")
    @Autowired
    private CacheService java4UCacheService;

    @Scheduled(cron = "0 0 0 1/1 * ? *")
    public void flushCacheEveryDay() {
        LOGGER.info("Starting Clear Cache .." + LocalDateTime.now());
        cacheService.flushCache();
        LOGGER.info("Finished Clear Cache .." + LocalDateTime.now());
    }


    @Scheduled(cron = "0 0/10 * 1/1 * ? *")
    public void updateFeedMessagesFromCache() {
        Query query = new Query();
        query.setRangeStart("feedMessage_");
        ViewResponse result = java4UCacheService.getCouchbaseClient().query(java4UCacheService.getJava4UsView(), query);
        while (result.iterator().hasNext()) {
            result.iterator().remove();
        }
    }
}
