/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.utils;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author turgay
 */
public final class Clock {

    private Clock() {

    }
    private static boolean isFrozen;
    private static Date timeSet;

    public static void freeze() {
        isFrozen = true;
    }

    public static void unfreeze() {
        isFrozen = false;
        timeSet = null;
    }

    public static Date getTime() {
        Calendar calendar = Calendar.getInstance();
        if (isFrozen) {
            if (timeSet == null) {
                timeSet = calendar.getTime();
            }
            return timeSet;
        }
        return calendar.getTime();
    }

    public static void setTime(Date date) {
        timeSet = date;
    }
}
