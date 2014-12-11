package com.java4us.web.model;


import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

public class SeoMetaData implements Serializable{

    private static final long serialVersionUID = -4782855455788377686L;

    private String description = StringUtils.EMPTY;
    private String title = StringUtils.EMPTY;
    private String canonical = StringUtils.EMPTY;
    private String robots = StringUtils.EMPTY;
    private boolean noIndex = false;

    public static SeoMetaData getInstance(){
        return new SeoMetaData();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCanonical() {
        return canonical;
    }

    public void setCanonical(String canonical) {
        this.canonical = canonical;
    }

    public String getRobots() {
        return robots;
    }

    public void setRobots(String robots) {
        this.robots = robots;
    }

    public boolean isNoIndex() {
        return noIndex;
    }

    public void setNoIndex(boolean noIndex) {
        this.noIndex = noIndex;
    }
}
