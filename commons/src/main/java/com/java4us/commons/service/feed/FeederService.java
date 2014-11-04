/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service.feed;

import com.java4us.commons.dao.feed.FeederDao;
import com.java4us.commons.utils.criteria.FeederSearchCriteria;
import com.java4us.domain.Feeder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author turgay
 */
@Service
public class FeederService {

	@Autowired
	private FeederDao feederDao;

	@Transactional(readOnly = true)
	public List<Feeder> findAcceptedFeedersFeeds() {
		return feederDao.findAcceptedFeedersFeeds();
	}

	@Transactional(readOnly = true)
	public List<Feeder> findAcceptedAndroidFeedersFeeds() {
		return feederDao.findAcceptedAndroidFeedersFeeds();
	}

	@Transactional(readOnly = true)
	public List<Feeder> findAcceptedJavaFeedersFeeds() {
		return feederDao.findAcceptedJavaFeedersFeeds();
	}

	@Transactional
	public void save(Feeder feeder) {
		feederDao.persist(feeder);
	}
	
	@Transactional
	public Feeder update(Feeder feeder) {
		return feederDao.merge(feeder);
	}

	@Transactional(readOnly = true)
	public boolean isProperDomain(String domain) {
		return feederDao.findByUnique("domain", domain) == null;
	}

	@Transactional(readOnly = true)
	public List<Feeder> findFeederList(int first, int pageSize,
			String sortField, String sortOrder, FeederSearchCriteria filter) {
		return feederDao.findPartialByFilter(first, pageSize, sortField,
				sortOrder, filter);
	}

	@Transactional(readOnly = true)
	public int getRowCount(FeederSearchCriteria filter) {
		return feederDao.getRowCountFeederList(filter);
	}

}
