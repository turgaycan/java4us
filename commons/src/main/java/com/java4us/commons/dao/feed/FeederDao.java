/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.dao.feed;

import com.java4us.commons.utils.criteria.FeederSearchCriteria;
import com.java4us.commons.utils.criteria.SearchCriteriaUtil;
import com.java4us.commons.dao.core.BaseDao;
import com.java4us.domain.Feeder;
import com.java4us.domain.common.enums.Category;
import com.java4us.domain.common.enums.FeederStatus;

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
public class FeederDao extends BaseDao<Feeder> {

	public FeederDao() {
		super(Feeder.class);
	}

	@SuppressWarnings("unchecked")
	public List<Feeder> findAcceptedFeedersFeeds() {
		Criteria criteria = getCriteria().add(
				Restrictions.eq("status", FeederStatus.ACCEPTED));
		criteria.createAlias("feeds", "_feeds", JoinType.LEFT_OUTER_JOIN);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Feeder> findAcceptedAndroidFeedersFeeds() {
		Criteria criteria = getCriteria().add(
				Restrictions.eq("status", FeederStatus.ACCEPTED));
		criteria.createAlias("feeds", "_feeds", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("_feeds.category", Category.ANDROID));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Feeder> findAcceptedJavaFeedersFeeds() {
		Criteria criteria = getCriteria().add(
				Restrictions.eq("status", FeederStatus.ACCEPTED));
		criteria.createAlias("feeds", "_feeds", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("_feeds.category", Category.JAVA));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Feeder> findAcceptedFeeders() {
		return getCriteria().add(
				Restrictions.eq("status", FeederStatus.ACCEPTED)).list();
	}

	@SuppressWarnings("unchecked")
	public List<Feeder> findPartialByFilter(int first, int pageSize,
			String sortField, String sortOrder, FeederSearchCriteria filter) {
		Criteria criteria = preparePartialFilter(first, pageSize, sortField,
				sortOrder, filter);
		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);
		return criteria == null ? Collections.EMPTY_LIST : criteria.list();
	}

	private Criteria preparePartialFilter(int first, int pageSize,
			String sortField, String sortOrder, FeederSearchCriteria filter) {
		Criteria criteria = SearchCriteriaUtil.restoreListStateBy(filter,
				getCurrentSession());
		if (criteria == null) {
			criteria = getCriteria();
			filter.pushList(criteria);
		}
		if (criteria != null) {
			prepareFeederCriteria(criteria, filter);
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

	public int getRowCountFeederList(FeederSearchCriteria filter) {
		Criteria criteria = SearchCriteriaUtil.restoreCountStateBy(filter,
				getCurrentSession());
		if (criteria == null) {
			criteria = getCriteria();
			filter.pushCount(criteria);
		} 
		criteria = prepareFeederCriteria(getCriteria(), filter);
		return getRowCount(criteria);
	}

	private Criteria prepareFeederCriteria(Criteria criteria,
			FeederSearchCriteria filter) {
		if (filter == null) {
			return criteria;
		}

		if (filter.getFeederStatus() != null) {
			appendFeederStatusToCriteria(criteria, filter.getFeederStatus());
		}

		if (StringUtils.isNotBlank(filter.getDomain())) {
			appendFeederDomainToCriteria(criteria, filter.getDomain());
		}

		if (StringUtils.isNotBlank(filter.getEmail())) {
			appendFeederEmailToCriteria(criteria, filter.getEmail());
		}
		return criteria;
	}

	private void appendFeederEmailToCriteria(Criteria criteria, String email) {
		criteria.add(Restrictions.eq("email", email));
	}

	private void appendFeederDomainToCriteria(Criteria criteria, String domain) {
		criteria.add(Restrictions.eq("domain", domain));
	}

	private void appendFeederStatusToCriteria(Criteria criteria,
			FeederStatus status) {
		criteria.add(Restrictions.eq("status", status));
	}

}
