/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.web.controller.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author turgay
 */
public final class RegexUtil {

    private static Pattern pattern;
    private static Matcher matcher;

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String URL_PATTERN = "((https?):((//)|(\\\\\\\\))+[\\\\w\\\\d:#@%/;$()~_?\\\\+-=\\\\\\\\\\\\.&]*)";

    private RegexUtil() {
    }

    public static boolean checkEmail(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isUrl(String str) {
        pattern = Pattern.compile(URL_PATTERN, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(str);
        return matcher.find();
    }
}
