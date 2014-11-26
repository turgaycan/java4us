/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service;

import com.java4us.commons.component.utils.EmailUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author turgay
 */
@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    private EmailUtility emailUtility;

    public void registerUserWelcomeMail(String fullname, String email) {
        LOGGER.info("User registration for   {}" + email + " complete");
        emailUtility.sendNewFeederWelcomeMail(fullname, email);
    }

    public void resetPasswordMail(String fullname, String newPassword, String email) {
        LOGGER.info("User reset password for   {}" + fullname + " complete");
        emailUtility.sendResetPasswordMail(fullname, newPassword, email);
    }

}
