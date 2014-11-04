/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service;

import com.java4us.commons.component.utils.EmailUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author turgay
 */
@Service
public class EmailService {

    @Autowired
    private EmailUtility emailUtility;

    public void registerUser(String userName) {
        System.out.println("User registration for   {}" + userName + " complete");
        emailUtility.sendMail(userName);
        System.out.println("Registration Complete. Mail will be send after 5 seconds ");
    }
}
