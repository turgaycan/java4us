/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.java4us.commons.dao.feed;

import com.java4us.commons.utils.criteria.FeedSearchCriteria;
import com.java4us.commons.utils.criteria.SearchCriteriaUtil;
import com.java4us.commons.dao.core.BaseDao;
import com.java4us.domain.Feed;
import com.java4us.domain.common.enums.BaseStatus;
import com.java4us.domain.common.enums.Category;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

/**
 *
 * @author turgay
 */
@Repository
public class FeedDao extends BaseDao<Feed> {

	public FeedDao() {
		super(Feed.class);
	}

	@SuppressWarnings("unchecked")
	public List<Feed> findActiveFeeds() {
		return getStatusActiveCriteria().list();
	}

	@SuppressWarnings("unchecked")
	public List<Feed> findPartialByFilter(int first, int pageSize,
			String sortField, String sortOrder, FeedSearchCriteria filter) {
		Criteria criteria = preparePartialFilter(first, pageSize, sortField,
				sortOrder, filter);
		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);
		return criteria == null ? Collections.EMPTY_LIST : criteria.list();
	}

	private Criteria preparePartialFilter(int first, int pageSize,
			String sortField, String sortOrder, FeedSearchCriteria filter) {
		Criteria criteria = SearchCriteriaUtil.restoreListStateBy(filter,
				getCurrentSession());
		if (criteria == null) {
			criteria = getCriteria();
			filter.pushList(criteria);
		}
		if (criteria != null) {
			prepareFeedCriteria(criteria, filter);
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

	public int getRowCountFeedList(FeedSearchCriteria filter) {
		Criteria criteria = SearchCriteriaUtil.restoreCountStateBy(filter,
				getCurrentSession());
		if (criteria == null) {
			criteria = getCriteria();			
			filter.pushCount(criteria);
		} 
		criteria = prepareFeedCriteria(getCriteria(), filter);
		return getRowCount(criteria);
	}

	private Criteria prepareFeedCriteria(Criteria criteria,
			FeedSearchCriteria filter) {
		if (filter == null) {
			return criteria;
		}

		if (filter.getStatus() != null) {
			appendFeedStatusToCriteria(criteria, filter.getStatus());
		}

		if (StringUtils.isNotBlank(filter.getLink())) {
			appendFeedLinkToCriteria(criteria, filter.getLink());
		}

		appendFeederToCriteria(criteria);
		
		if (StringUtils.isNotBlank(filter.getFeederEmail())) {
			appendFeedEmailToCriteria(criteria, filter.getFeederEmail());
		}
		
		if (filter.getCategory() != null
				&& StringUtils.isNotBlank(filter.getCategory().name())) {			
			appendFeedCategoryToFeed(criteria, filter.getCategory());
		}
		return criteria;
	}

	private void appendFeederToCriteria(Criteria criteria) {
		criteria.createAlias("feeder", "_feeder", JoinType.LEFT_OUTER_JOIN);
	}

	private void appendFeedEmailToCriteria(Criteria criteria, String email) {		
		criteria.add(Restrictions.eq("_feeder.email", email));
	}

	private void appendFeedCategoryToFeed(Criteria criteria, Category category) {
		criteria.add(Restrictions.eq("category", category));
	}

	private void appendFeedLinkToCriteria(Criteria criteria, String domain) {
		criteria.add(Restrictions.eq("domain", domain));
	}

	private void appendFeedStatusToCriteria(Criteria criteria, BaseStatus status) {
		criteria.add(Restrictions.eq("status", status));
	}
}
