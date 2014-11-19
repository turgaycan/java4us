/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service.feed;

import com.java4us.commons.dao.feed.FeedMessageDao;
import com.java4us.commons.utils.criteria.FeedMessageSearchCriteria;
import com.java4us.domain.FeedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author turgay
 */
@Service
public class FeedMessageService {

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

}
