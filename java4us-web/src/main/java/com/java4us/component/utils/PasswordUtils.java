/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.component.utils;

import com.java4us.commons.dao.core.EntityDao;
import com.java4us.domain.User;
import com.java4us.domain.core.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author TCAN
 */
@Component
public class PasswordUtils {

    @Autowired
    private Md5PasswordEncoder passwordEncoder;

    @Autowired
    private SaltSource saltSource;

    @Autowired
    private EntityDao entityDao;

    public String encodePassword(UserDetails userDetails, String encodePassword) {
        Object saltValue = saltSource.getSalt(userDetails);
        return passwordEncoder.encodePassword(encodePassword, saltValue);
    }

    public String encodePassword(String encodePassword, String saltValue) {
        return passwordEncoder.encodePassword(encodePassword, saltValue);
    }

    public boolean changePassword(User currentUser, String oldPassword, String newPassword) {
        String encodedPassword = encodePassword((UserDetails) currentUser, oldPassword);
        if (currentUser.getPassword().equals(encodedPassword)) {
            currentUser.setPassword(encodePassword((UserDetails) currentUser, newPassword));
            entityDao.merge((BaseEntity) currentUser);
            return true;
        } else {
            return false;
        }
    }

    public boolean isPasswordValid(UserDetails userDetails, String password, String credentials) {
        Object salt = saltSource.getSalt(userDetails);
        return passwordEncoder.isPasswordValid(password, credentials, salt);
    }

    public void setPasswordEncoder(Md5PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void setSaltSource(SaltSource saltSource) {
        this.saltSource = saltSource;
    }

    public void setEntityDao(EntityDao entityDao) {
        this.entityDao = entityDao;
    }

}
