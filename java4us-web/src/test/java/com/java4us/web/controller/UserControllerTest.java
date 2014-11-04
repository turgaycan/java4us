/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.web.controller;

import com.java4us.domain.User;
import com.java4us.commons.service.member.UserService;
import com.java4us.domain.common.enums.BaseStatus;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author turgay
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService userService;

    @Test
    public void shouldAdminViewContainAdmin() {
        User admin = new User(1L, "test@java4us.net", "login", "pass", BaseStatus.Active);
        when(userService.findById(admin.getId())).thenReturn(admin);
        ModelAndView mav = controller.listAdmin();
        assertThat(mav.getViewName(), equalTo("admin"));
        assertThat((User) (mav.getModelMap().get("admin")), equalTo(admin));
    }
}
