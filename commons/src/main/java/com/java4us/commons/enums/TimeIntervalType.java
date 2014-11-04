/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.enums;

import com.java4us.domain.common.enums.LabeledEnum;

/**
 *
 * @author turgay
 */
public enum TimeIntervalType implements LabeledEnum {

    TODAY("today"),
    YESTERDAY("yesterday"),
    THIS_WEEK("thisWeek"),
    LAST_WEEK("lastWeek"),
    ONE_WEEK_BEFORE("oneWeekBefore"),
    TWO_WEEKS_BEFORE("twoWeekBefore"),
    THREE_WEEKS_BEFORE("threeWeeksBefore"),
    THIS_MONTH("thisMonth"),
    SINCE_LAST_MONTH("sinceLastMonth"),
    LAST_SECOND_MONTH("lastSecondMonth"),
    LAST_MONTH("lastMonth"),
    LAST_2_MONTHS("last2Months"),
    LAST_3_MONTHS("last3Months"),
    LAST_6_MONTHS("last6Months"),
    BEFORE_THIS_MONTH("beforeThisMonth");

    TimeIntervalType(String id) {
        this.id = id;
    }

    private String id;

    public String getId() {
        return this.id;
    }

    public static TimeIntervalType[] getCommonIntervalTypesForMallfront() {
        return new TimeIntervalType[]{
            TODAY,
            ONE_WEEK_BEFORE,
            SINCE_LAST_MONTH,
            THIS_MONTH,
            LAST_MONTH,
            LAST_SECOND_MONTH
        };
    }

    public static TimeIntervalType[] getTimeIntervalTypesForSelectMenu() {
        return new TimeIntervalType[]{
            TODAY,
            YESTERDAY,
            THIS_WEEK,
            LAST_WEEK,
            THIS_MONTH,
            LAST_MONTH,
            LAST_3_MONTHS
        };
    }

}
