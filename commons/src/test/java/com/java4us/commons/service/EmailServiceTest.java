/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service;

import com.java4us.commons.component.utils.EmailUtility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 *
 * @author turgay
 */
@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private EmailUtility emailUtility;
    
    @Test
    public void shouldSendMailIfHasParameterWhenUserRegister() {
        emailService.registerUserWelcomeMail("Turgay Can", "turgay@can.com");
        verify(emailUtility, times(1)).sendNewFeederWelcomeMail("Turgay Can", "turgay@can.com");
    }
}
