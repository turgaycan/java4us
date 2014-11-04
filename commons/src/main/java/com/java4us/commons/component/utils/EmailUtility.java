/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.component.utils;

import org.springframework.stereotype.Component;

/**
 *
 * @author turgay
 */
@Component
public class EmailUtility {

    public void sendMail(String name) {
        System.out.println(" I Will be formatting html mail and sending it  ");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Asynchronous method call of send email — Complete");
    }
}
