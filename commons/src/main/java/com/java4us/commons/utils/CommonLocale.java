/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.utils;

import java.util.Locale;

/**
 *
 * @author turgay
 */
public final class CommonLocale {

    private CommonLocale() {
    }

    private static final Locale TR = new Locale("tr", "TR");
    private static final Locale EN = Locale.ENGLISH;

    public static Locale getTR() {
        return TR;
    }

    public static Locale getEN() {
        return EN;
    }
}
