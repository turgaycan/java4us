package com.java4us.commons.component.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@RunWith(MockitoJUnitRunner.class)
public class EmailUtilityTest {

    @InjectMocks
    private EmailUtility emailUtility;

    @Mock
    private SimpleMailMessage resetEmailTemplate;

    @Mock
    private SimpleMailMessage newFeederEmailTemplate;

    @Mock
    private SimpleMailMessage subscriberEmailTemplate;

    @Mock
    private JavaMailSenderImpl javaMailSender;

    @Test
    public void shouldBla(){
    }
}