/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service.member;

import com.java4us.commons.model.DeniedUsersModel;
import com.java4us.commons.cache.CacheService;
import com.java4us.commons.dao.UserDao;
import com.java4us.commons.utils.criteria.UserSearchCriteria;
import com.java4us.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author turgay
 */
@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserService.class);

	private static final String DENIEDUSERS_KEY = "deniedUsers";

	@Autowired
	private UserDao userDao;

	@Qualifier("java4UsCacheService")
	@Autowired
	private CacheService cacheService;
	
	@Transactional
	public void saveOrUpdate(User user) throws Exception {
		if (!userDao.isProperEmail(user)) {
			LOGGER.info("Existing email {}" + user.getEmail());
			throw new Exception("existingEmail");
		} else if (!userDao.isProperUserId(user)) {
			LOGGER.info("User Email is not proper {}" + user.getEmail());
			throw new Exception("existingId");
		}
		userDao.merge(user);
	}

	@Transactional(readOnly = true)
	public User findById(Long id) {
		return userDao.findBy(id);
	}

	@Transactional(readOnly = true)
	public String findDeniedUser(String email) {
		User findDeniedUser = userDao.findDeniedUser(email);
		if (findDeniedUser == null) {
			return null;
		}
		return findDeniedUser.getEmail();
	}

	@Transactional(readOnly = true)
	public DeniedUsersModel findDeniedUsers() throws Exception {
		DeniedUsersModel deniedUsersModel = cacheService.get(DENIEDUSERS_KEY);
		if (deniedUsersModel != null) {
			return deniedUsersModel;
		}
		List<String> deniedUsers = new ArrayList<>();
		for (User user : userDao.findDeniedUsers()) {
			deniedUsers.add(user.getEmail());
		}
		deniedUsersModel = new DeniedUsersModel();
		deniedUsersModel.setDeniedUserList(deniedUsers);
		cacheService.put(DENIEDUSERS_KEY, deniedUsersModel,
				CacheService.ONE_DAY);
		return deniedUsersModel;
	}

	@Transactional(readOnly = true)
	public List<String> findActiveUsers() {
		List<String> activeUsers = new ArrayList<>();
		for (User user : userDao.findActiveUsers()) {
			activeUsers.add(user.getEmail());
		}
		return activeUsers;
	}

	@Transactional(readOnly = true)
	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}

	@Transactional(readOnly = true)
	public List<User> findUserList(int first, int pageSize, String sortField,
			String sortOrder, UserSearchCriteria filter) {
		return userDao.findPartialByFilter(first, pageSize, sortField,
				sortOrder, filter);
	}

	@Transactional(readOnly = true)
	public int getRowCount(UserSearchCriteria filter) {
		return userDao.getRowCountUserList(filter);
	}
}
