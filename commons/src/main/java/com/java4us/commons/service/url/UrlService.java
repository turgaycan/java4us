/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service.url;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @author turgay
 */
@Service
public class UrlService {

    private static final String ROOT = "http://www.java4us.net";
    private static final String JAVAROOTCATEGORY = "/java";
    private static final String ANDROIDROOTCATEGORY = "/android";
    private static final String SEARCH = "/ara?q=";
    private static final String SEPARATORCHAR = "/";
    private static final String REGISTER = "/register";
    private static final String RSSDETAIL = "-r";
    private static final String BLANK = " ";
    private static final String URL_FILTER = "[^A-Za-z0-9\\-]*";

    public String getRootUrl() {
        return ROOT;
    }

    public String getJavaRootUrl() {
        return getRootUrl() + JAVAROOTCATEGORY;
    }

    public String getAndroidRootUrl() {
        return getRootUrl() + ANDROIDROOTCATEGORY;
    }

    public String getJavaSearchUrl(String query) {
        return getJavaRootUrl() + SEARCH + query;
    }

    public String getAndroidSearchUrl(String query) {
        return getAndroidRootUrl() + SEARCH + query;
    }

    public String getPaginationUrl(String category, int pageNumber) {
        return getRootUrl().concat(SEPARATORCHAR).concat(category).concat(SEPARATORCHAR).concat(String.valueOf(pageNumber));
    }

    public String getRegisterUrl() {
        return getRootUrl().concat(REGISTER);
    }

    public String getFromUnSubscribeUrl(String url, Long id) {
        return getRootUrl().concat(SEPARATORCHAR).concat(url).concat("-").concat(String.valueOf(id));
    }

    public String getRssDetailPageUrl(String title, Long id) {
        return getRootUrl() + SEPARATORCHAR + buildTitleUrl(generateFilter(title)) + RSSDETAIL + id;
    }

    public String getRssDetailPageUrlWithOutRootPath(String title, Long id) {
        return SEPARATORCHAR + buildTitleUrl(generateFilter(title)) + RSSDETAIL + id;
    }

    public static String generateFilter(String text) {
        text = text.toLowerCase(Locale.ENGLISH).replaceAll("\\s+", " ").replaceAll(" ", "-").replaceAll(URL_FILTER, "").replaceAll("(\\-)+", "-");
        text = StringUtils.strip(text, "-");
        return text;
    }

    private static String buildTitleUrl(String title) {
        return title.contains(BLANK) ? title.replaceAll(BLANK, "-") : title;
    }

}
