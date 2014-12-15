/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service.feed;

import com.java4us.commons.dao.feed.FeedMessageDao;
import com.java4us.commons.utils.Clock;
import com.java4us.commons.utils.DateUtils;
import com.java4us.commons.utils.criteria.FeedMessageSearchCriteria;
import com.java4us.domain.FeedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *
 * @author turgay
 */
@Service
public class FeedMessageService {

    private static final int WEEKLFEEDMESSAGESIZE = 10;

	@Autowired
	private FeedMessageDao feedMessageDao;

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
	public List<String> getFindAllProceedMessagesLinks() {
		return feedMessageDao.findAllProceedMessagesLinks();
	}

	@Transactional(readOnly = true)
	public List<String> getFindAllAndroidProceedMessagesLinks() {
		return feedMessageDao.findAllAndroidProceedMessagesLinks();
	}

	@Transactional(readOnly = true)
	public List<String> getFindAllJavaProceedMessagesLinks() {
		return feedMessageDao.findAllJavaProceedMessagesLinks();
	}

    @Transactional(readOnly = true)
    public List<FeedMessage> findWeeklyFeedMessages() {
        Date now = Clock.getTime();
        Date week = DateUtils.addDays(now, -7);
        List<FeedMessage> weeklyFeedMessages = feedMessageDao.findWeeklyFeedMessages(now, week);
        if(weeklyFeedMessages.size() > getWeeklfeedmessagesize()){
            return weeklyFeedMessages.subList(0, getWeeklfeedmessagesize());
        }
        return weeklyFeedMessages;
    }

    public static int getWeeklfeedmessagesize() {
        return WEEKLFEEDMESSAGESIZE;
    }
}
