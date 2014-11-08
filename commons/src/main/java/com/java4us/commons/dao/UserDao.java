/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.dao;

import com.java4us.commons.utils.criteria.SearchCriteriaUtil;
import com.java4us.commons.utils.criteria.UserSearchCriteria;
import com.java4us.commons.dao.core.BaseDao;
import com.java4us.domain.User;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author turgay
 */
@Repository
public class UserDao extends BaseDao<User> {

	public UserDao() {
		super(User.class);
	}

	public User findUser(String username, String password) {
		return (User) getCriteria()
				.add(Restrictions.eq("email", username).ignoreCase())
				.add(Restrictions.eq("password", password)).uniqueResult();
	}

	public User findByUserName(String username) {
		return (User) getStatusActiveCriteria().add(
				Restrictions.eq("email", username).ignoreCase()).uniqueResult();
	}

	public User findDeniedUser(String username) {
		return (User) getStatusDeactiveCriteria().add(
				Restrictions.eq("email", username)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> findDeniedUsers() {
		return getStatusDeactiveCriteria().list();
	}

	@SuppressWarnings("unchecked")
	public List<User> findActiveUsers() {
		return getStatusActiveCriteria().list();
	}

	public boolean isProperEmail(User user) {
		User fromDB = findByUnique("email", user.getEmail());
		if (user.isPersisted()) {
			return fromDB == null || user.getId().equals(fromDB.getId());
		}
		return fromDB == null;
	}

	public boolean isProperUserId(User user) {
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("email", user.getEmail()).ignoreCase());
		User fromDB = (User) criteria.uniqueResult();
		if (user.isPersisted()) {
			return fromDB == null || user.getId().equals(fromDB.getId());
		}
		return fromDB == null;
	}

	public User findUserForLogin(String email) {
		return (User) getCriteria()
				.add(Restrictions.eq("email", email).ignoreCase())
				.add(Restrictions.eq("status", false)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> findPartialByFilter(int first, int pageSize,
			String sortField, String sortOrder, UserSearchCriteria filter) {
		Criteria criteria = preparePartialFilter(first, pageSize, sortField,
				sortOrder, filter);
		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);
		return criteria == null ? Collections.EMPTY_LIST : criteria.list();
	}

	private Criteria preparePartialFilter(int first, int pageSize,
			String sortField, String sortOrder, UserSearchCriteria filter) {
		Criteria criteria = SearchCriteriaUtil.restoreListStateBy(filter,
				getCurrentSession());
		if (criteria == null) {
			criteria = getCriteria();
			filter.pushList(criteria);
		}
		if (criteria != null) {
			prepareCriteria(criteria, filter);
			setupSortCriteria(criteria, getSortField(sortField),
					getSortOrder(sortOrder));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return criteria;
		} else {
			return null;
		}
	}

	private void prepareCriteria(Criteria criteria, UserSearchCriteria filter) {
		if (StringUtils.isNotBlank(filter.getEmail())) {
			criteria.add(Restrictions.eq("email", filter.getEmail()));
		}
	}

	private String getSortField(String sortField) {
		return StringUtils.isNotBlank(sortField) ? sortField : "createDate";
	}

	private String getSortOrder(String sortOrder) {
		return StringUtils.isNotBlank(sortOrder) ? sortOrder : "desc";
	}
	
	public int getRowCountUserList(UserSearchCriteria filter) {
		Criteria criteria = SearchCriteriaUtil.restoreCountStateBy(filter,
				getCurrentSession());
		if (criteria == null) {
			criteria = getCriteria();			
			filter.pushCount(criteria);
		} 
		prepareCriteria(criteria, filter);
		return getRowCount(criteria);
	}
}
