/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.enums;

import com.java4us.commons.utils.DateUtils;

import java.util.Date;

/**
 *
 * @author turgay
 */
public enum DateBefore {

    OneWeekBeforeToday(-7);
    private int days;

    private DateBefore(int days) {
        this.days = days;
    }

    public Date getDate() {
    	return DateUtils.addDays(DateUtils.toStartOfToday(), days);
    }
}
