/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.domain.builder;

import com.java4us.domain.User;
import com.java4us.domain.UserRoles;
import com.java4us.domain.common.enums.BaseStatus;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author turgay
 */
public class UserBuilder extends BaseBuilder<User, UserBuilder> {

    private Long id;
    private String email = RandomStringUtils.random(5);
    private String password = RandomStringUtils.random(5);
    private Date createDate;
    private String login = RandomStringUtils.random(5);
    private BaseStatus status = BaseStatus.Active;
    private Set<UserRoles> roles = new HashSet<>();

    @Override
    protected User doBuild() {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setStatus(status);
        user.setCreateDate(createDate);
        user.setLogin(login);
        user.setUserRoles(roles);            
        return user;
    }

    @Override
    public UserBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder status(BaseStatus status) {
        this.status = status;
        return this;
    }

    public UserBuilder createDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public UserBuilder login(String login) {
        this.login = login;
        return this;
    }
    
    public UserBuilder roles(Set<UserRoles> roles) {
        this.roles = roles;
        return this;
    }
    

}
