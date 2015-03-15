/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service.feed;

import com.java4us.commons.cache.CacheService;
import com.java4us.commons.dao.feed.FeedMessageDao;
import com.java4us.commons.utils.Clock;
import com.java4us.commons.utils.DateUtils;
import com.java4us.commons.utils.criteria.FeedMessageSearchCriteria;
import com.java4us.domain.FeedMessage;
import com.java4us.domain.common.enums.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author turgay
 */
@Service
public class FeedMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedMessageService.class);

    private static final int WEEKLFEEDMESSAGESIZE = 10;
    private static final int PAGESIZE = 30;
    private static final String WEEKLY_MESSAGES = "weeklyFeedMessages_";
    private static final String JAVAPAGINGCACHEPREFIX = "javaPaging_";
    private static final String ANDROIDPAGINGCACHEPREFIX = "androidPaging_";

    @Autowired
    private FeedMessageDao feedMessageDao;

    @Autowired
    private CacheService cacheService;

    @Transactional
    public void save(FeedMessage feedMessage) {
        feedMessageDao.persist(feedMessage);
    }

    @Transactional
    public FeedMessage update(FeedMessage feedMessage) {
        return feedMessageDao.merge(feedMessage);
    }

    @Transactional(readOnly = true)
    public FeedMessage findById(Long id) {
        return feedMessageDao.findBy(id);
    }

    @Transactional(readOnly = true)
    public FeedMessage findByLink(String feedMessageLink) {
        return feedMessageDao.findByUnique("link", feedMessageLink);
    }

    @Transactional(readOnly = true)
    public List<FeedMessage> findFeedMessageList(int first, int pageSize,
                                                 String sortField, String sortOrder, FeedMessageSearchCriteria filter) {
        return feedMessageDao.findPartialByFilter(first, pageSize, sortField,
                sortOrder, filter);
    }

    @Transactional(readOnly = true)
    public int getRowCount(FeedMessageSearchCriteria filter) {
        return feedMessageDao.getRowCountFeedMessageList(filter);
    }

    @Transactional(readOnly = true)
    public List<String> findAllProceedMessagesLinks() {
        return feedMessageDao.findAllProceedMessagesLinks();
    }

    @Transactional(readOnly = true)
    public List<String> findAllAndroidProceedMessagesLinks() {
        return feedMessageDao.findAllAndroidProceedMessagesLinks();
    }

    @Transactional(readOnly = true)
    public List<String> findAllJavaProceedMessagesLinks() {
        return feedMessageDao.findAllJavaProceedMessagesLinks();
    }

    @Transactional(readOnly = true)
    public LinkedList<FeedMessage> findWeeklyFeedMessages() {
        LinkedList<FeedMessage> feedMessages = null;
        String cacheKey = getCacheKey();
        try {
            feedMessages = cacheService.get(cacheKey);
        } catch (Exception e) {
            LOGGER.debug("Weekly Feed Messages {} ", e);
        }
        if (feedMessages == null) {
            feedMessages = new LinkedList<>(getFeedWeeklyMessages());
            addDTOsToCache(cacheKey, feedMessages, CacheService.SIX_DAYS);
            return feedMessages;
        } else {
            return feedMessages;
        }
    }

    private List<FeedMessage> getFeedWeeklyMessages() {
        Date now = Clock.getTime();
        Date week = DateUtils.addDays(now, -7);
        List<FeedMessage> weeklyFeedMessages = feedMessageDao.findWeeklyFeedMessages(now, week);
        if (weeklyFeedMessages.size() > getWeeklfeedmessagesize()) {
            List<FeedMessage> feedMessages = weeklyFeedMessages.subList(0, getWeeklfeedmessagesize());
            return feedMessages;
        }
        return weeklyFeedMessages;
    }

    private String getCacheKey() {
        return WEEKLY_MESSAGES + LocalDate.now().format(DateTimeFormatter.ISO_DATE);
    }

    private void addDTOsToCache(String cacheKey, List<FeedMessage> feedMessages, int TTL) {
        cacheService.add(cacheKey, feedMessages, TTL);
    }

    public static int getWeeklfeedmessagesize() {
        return WEEKLFEEDMESSAGESIZE;
    }

    @Transactional(readOnly = true)
    public LinkedList<FeedMessage> findPagingFeedMessages(Category category, int pageNumber) {
        LinkedList<FeedMessage> feedMessages = null;
        String pagingCacheKey = getPagingCacheKey(category, pageNumber);
        try {
            feedMessages = cacheService.get(pagingCacheKey);
        } catch (Exception e) {
            LOGGER.debug("Paging feed messages from cache {}", e);
        }
        if (feedMessages == null) {
            FeedMessageSearchCriteria filter = prepareFilter(category);
            feedMessages = new LinkedList<>(findFeedMessageList(pageNumber * PAGESIZE, PAGESIZE, "", "", filter));
            addDTOsToCache(pagingCacheKey, feedMessages, CacheService.ONE_HOUR);
            return feedMessages;
        }
        return feedMessages;
    }

    private FeedMessageSearchCriteria prepareFilter(Category category) {
        FeedMessageSearchCriteria filter = new FeedMessageSearchCriteria();
        filter.setViewCount(true);
        filter.setProceed(true);
        filter.setCategory(category);
        return filter;
    }

    private String getPagingCacheKey(Category category, int pageNum) {
        return getCategoryPrefix(category) + pageNum;
    }

    private String getCategoryPrefix(Category category) {
        return category == Category.ANDROID ? ANDROIDPAGINGCACHEPREFIX : JAVAPAGINGCACHEPREFIX;
    }

    public static int getPagesize() {
        return PAGESIZE;
    }
}
