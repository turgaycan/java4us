/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.dao;

import com.java4us.commons.dao.core.AbstractDataAccessTest;
import com.java4us.domain.User;
import com.java4us.domain.builder.UserBuilder;
import com.java4us.domain.builder.UserRolesBuilder;
import com.java4us.domain.builder.utils.TestDateUtils;
import com.java4us.domain.common.enums.UserType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 *
 * @author turgay
 */
public class UserDaoTest extends AbstractDataAccessTest {

    @Autowired
    private UserDao dao;

    @Test
    public void shouldInsertAdmin() {
        User user = new UserBuilder().id(new Long(1)).email("test@java4us.net").password("pass").deleted(false).createDate(TestDateUtils.toDate("10-10-2010")).persist(getSession());
        new UserRolesBuilder().id(1L).userId(user).role(UserType.ROLE_ADMIN).persist(getSession());
        
        flushAndClear();
        
        User currentAdmin = dao.findBy(user.getId());
        Assert.assertThat(currentAdmin, equalTo(user));
    }

}
