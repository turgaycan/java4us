/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service.url;

import org.springframework.stereotype.Service;

/**
 *
 * @author turgay
 */
@Service
public class UrlService {

    private static final String ROOT = "http://www.java4us.net";
    private static final String JAVAROOTCATEGORY = "/java";
    private static final String ANDROIDROOTCATEGORY = "/android";
    private static final String SEARCH = "/ara?q=";
    private static final String SEPARATORCHAR = "/";

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
        return category + SEPARATORCHAR + pageNumber;
    }
}
