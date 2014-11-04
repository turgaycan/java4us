/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.java4us.commons.service.feed;

import com.java4us.commons.dao.feed.FeedDao;
import com.java4us.commons.utils.criteria.FeedSearchCriteria;
import com.java4us.domain.Feed;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author turgay
 */
@Service
public class FeedService {

	@Autowired
	private FeedDao feedDao;

	@Transactional
	public void save(Feed feed) {
		feedDao.persist(feed);
	}

	@Transactional
	public Feed update(Feed feed) {
		return feedDao.merge(feed);
	}

	@Transactional(readOnly = true)
	public List<Feed> findAll() {
		return feedDao.findAll();
	}

	@Transactional(readOnly = true)
	public List<Feed> findActiveFeeds() {
		return feedDao.findActiveFeeds();
	}

	@Transactional(readOnly = true)
	public List<Feed> findFeedList(int first, int pageSize, String sortField,
			String sortOrder, FeedSearchCriteria filter) {
		return feedDao.findPartialByFilter(first, pageSize, sortField,
				sortOrder, filter);
	}

	@Transactional(readOnly = true)
	public int getRowCount(FeedSearchCriteria filter) {
		return feedDao.getRowCountFeedList(filter);
	}

}
