/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.domain.builder;

import com.java4us.domain.User;
import com.java4us.domain.UserRoles;
import com.java4us.domain.common.enums.UserType;

/**
 *
 * @author turgay
 */
public class UserRolesBuilder extends BaseBuilder<UserRoles, UserRolesBuilder> {

    private Long id;
    private UserType role = UserType.ROLE_USER;
    private User userId;

    @Override
    protected UserRoles doBuild() {
        UserRoles userRoles = new UserRoles();
        userRoles.setId(id);
        userRoles.setRole(role);
        userRoles.setUserId(userId);
        return userRoles;
    }

    @Override
    public UserRolesBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UserRolesBuilder role(UserType role) {
        this.role = role;
        return this;
    }

    public UserRolesBuilder userId(User userId) {
        this.userId = userId;
        return this;
    }

}
