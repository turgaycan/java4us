/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service;

import com.java4us.commons.dao.UserDao;
import com.java4us.commons.service.member.UserService;
import com.java4us.domain.User;
import com.java4us.domain.builder.UserBuilder;
import com.java4us.domain.common.enums.BaseStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 *
 * @author turgay
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserDao adminDao;

    @Test
    public void shouldFindAdminById() {
        User admin = new UserBuilder().id(123L).email("test@java4us.net").password("pass").status(BaseStatus.Active).deleted(false).build();
        when(adminDao.findBy(admin.getId())).thenReturn(admin);
        User currentAdmin = service.findById(admin.getId());
        assertThat(currentAdmin.getStatus(), equalTo(BaseStatus.Active));
        assertThat(currentAdmin, equalTo(admin));
    }
}
