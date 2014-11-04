/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service.member;

import com.java4us.commons.dao.SubscriberDao;
import com.java4us.commons.utils.criteria.SubscriberSearchCriteria;
import com.java4us.domain.Subscriber;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author turgay
 */
@Service
public class SubscriberService {

	@Autowired
	private SubscriberDao subscriberDao;

	public List<Subscriber> findAll() {
		return subscriberDao.findAll();
	}

	@Transactional(readOnly = true)
	public boolean isProperEmail(String email) {
		return subscriberDao.findByUnique("email", email) == null;
	}

	@Transactional
	public void save(Subscriber subscriber) {
		subscriberDao.persist(subscriber);
	}
	
	@Transactional
	public Subscriber update(Subscriber subscriber) {
		return subscriberDao.merge(subscriber);
	}

	@Transactional(readOnly = true)
	public List<Subscriber> findSubscriberList(int first, int pageSize,
			String sortField, String sortOrder, SubscriberSearchCriteria filter) {
		return subscriberDao.findPartialByFilter(first, pageSize, sortField,
				sortOrder, filter);
	}

	@Transactional(readOnly = true)
	public int getRowCount(SubscriberSearchCriteria filter) {
		return subscriberDao.getRowCountSubscriberList(filter);
	}
}
