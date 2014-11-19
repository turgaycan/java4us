/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.java4us.commons.dao;

import com.java4us.commons.dao.core.BaseDao;
import com.java4us.commons.utils.criteria.SearchCriteriaUtil;
import com.java4us.commons.utils.criteria.SubscriberSearchCriteria;
import com.java4us.domain.Subscriber;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author turgay
 */
@Repository
public class SubscriberDao extends BaseDao<Subscriber>{

    public SubscriberDao() {
        super(Subscriber.class);
    }
    
	@SuppressWarnings("unchecked")
	public List<Subscriber> findPartialByFilter(int first, int pageSize,
			String sortField, String sortOrder, SubscriberSearchCriteria filter) {
		Criteria criteria = preparePartialFilter(first, pageSize, sortField,
				sortOrder, filter);
		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);
		return criteria == null ? Collections.EMPTY_LIST : criteria.list();
	}

	private Criteria preparePartialFilter(int first, int pageSize,
			String sortField, String sortOrder, SubscriberSearchCriteria filter) {
		Criteria criteria = SearchCriteriaUtil.restoreListStateBy(filter,
				getCurrentSession());
		if (criteria == null) {
			criteria = getCriteria();
			filter.pushList(criteria);
		}
		if (criteria != null) {
			prepareSubscriberCriteria(criteria, filter);
			setupSortCriteria(criteria, getSortField(sortField),
					getSortOrder(sortOrder));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return criteria;
		} else {
			return null;
		}
	}

	private String getSortField(String sortField) {
		return StringUtils.isNotBlank(sortField) ? sortField : "createDate";
	}

	private String getSortOrder(String sortOrder) {
		return StringUtils.isNotBlank(sortOrder) ? sortOrder : "desc";
	}

	public int getRowCountSubscriberList(SubscriberSearchCriteria filter) {
		Criteria criteria = SearchCriteriaUtil.restoreCountStateBy(filter,
				getCurrentSession());
		if (criteria == null) {
			criteria = getCriteria();	
			filter.pushCount(criteria);
		} 
		criteria = prepareSubscriberCriteria(criteria, filter);		
		return getRowCount(criteria);
	}

	private Criteria prepareSubscriberCriteria(Criteria criteria,
			SubscriberSearchCriteria filter) {
		if (filter == null) {
			return criteria;
		}

		if (StringUtils.isNotBlank(filter.getName())) {
			appendSubscriberNameToCriteria(criteria, filter.getName());
		}

		if (StringUtils.isNotBlank(filter.getSurname())) {
			appendSubscriberSurnameToCriteria(criteria, filter.getSurname());
		}
		
		if (StringUtils.isNotBlank(filter.getEmail())) {
			appendSubscriberEmailToCriteria(criteria, filter.getEmail());
		}

		return criteria;
	}

	private void appendSubscriberNameToCriteria(Criteria criteria, String name) {		
		criteria.add(Restrictions.eq("namel", name));
	}

	private void appendSubscriberSurnameToCriteria(Criteria criteria, String surname) {
		criteria.add(Restrictions.eq("surname", surname));
	}

	private void appendSubscriberEmailToCriteria(Criteria criteria, String email) {
		criteria.add(Restrictions.eq("domain", email));
	}

}
