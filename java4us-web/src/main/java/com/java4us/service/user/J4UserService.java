/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.service.user;

import com.java4us.common.model.DeniedUsersModel;
import com.java4us.commons.cache.CacheService;
import com.java4us.commons.dao.UserDao;
import com.java4us.component.utils.PasswordUtils;
import com.java4us.domain.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author TCAN
 */
@Service
public class J4UserService {

    private static final String DENIEDUSERS_KEY = "deniedUsers";

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordUtils passwordUtils;

    @Qualifier("java4UsCacheService")
    @Autowired
    private CacheService cacheService;

    @Transactional
    public void saveWithPasswordEncrypting(User user) throws Exception {
    	String password = user.getPassword();
        user.setPassword("1234");
        userDao.persist(user);
        user.setPassword(passwordUtils.encodePassword(user, password));
        userDao.saveEntity(user);
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
        userDao.findDeniedUsers().stream().forEach((user) -> {
            deniedUsers.add(user.getEmail());
        });
        deniedUsersModel = new DeniedUsersModel();
        deniedUsersModel.setDeniedUserList(deniedUsers);
        cacheService.put(DENIEDUSERS_KEY, deniedUsersModel, CacheService.ONE_DAY);
        return deniedUsersModel;
    }

    @Transactional(readOnly = true)
    public List<String> findActiveUsers() {
        List<String> activeUsers = new ArrayList<>();
        userDao.findActiveUsers().stream().forEach((user) -> {
            activeUsers.add(user.getEmail());
        });
        return activeUsers;
    }

    @Transactional(readOnly = true)
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }
}
