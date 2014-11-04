/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author turgay
 */
public final class TestDateUtils {

    private TestDateUtils() {
    }

    public static Date toDateFromFormat(String date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date convertedDate;
        try {
            convertedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }

        return convertedDate;
    }

    public static Date toDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return parseDate(date, dateFormat);
    }

    public static Date toDateTime(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return parseDate(date, dateFormat);
    }

    public static Date toDateTimeWithSeconds(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return parseDate(date, dateFormat);
    }

    public static Date toDateTimeWithMiliseconds(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S");
        return parseDate(date, dateFormat);
    }

    public static Date toDateTimeWithMilliSecondsWith(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss:SSS");
        return parseDate(date, dateFormat);
    }

    public static Date toDateMonth(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return parseDate(date, dateFormat);
    }

    private static Date parseDate(String date, SimpleDateFormat dateFormat) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
