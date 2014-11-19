/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.domain.builder;

import com.java4us.domain.Feed;
import com.java4us.domain.FeedMessage;
import com.java4us.domain.Feeder;
import com.java4us.domain.builder.utils.TestDateUtils;
import com.java4us.domain.common.enums.BaseStatus;
import com.java4us.domain.common.enums.Category;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Date;
import java.util.List;

/**
 *
 * @author turgay
 */
public class FeedBuilder extends BaseBuilder<Feed, FeedBuilder> {

    private Long id;
    private String link = RandomStringUtils.random(10, "UTF-8");
    private String language = RandomStringUtils.random(10, "UTF-8");
    private boolean copyright = false;
    private Date pubDate;
    private Date createDate =  TestDateUtils.toDate("10-10-2010");
    private Feeder feeder;
    private BaseStatus status = BaseStatus.Active;
    private Category category = Category.JAVA;
    private List<FeedMessage> entries;
    
    @Override
    protected Feed doBuild() {
        Feed feed = new Feed();
        feed.setId(id);
        feed.setLink(link);
        feed.setLang(language);
        feed.setCopyright(copyright);
        feed.setPubDate(pubDate);
        feed.setCreateDate(createDate);
        feed.setFeeder(feeder);
        feed.setStatus(status);
        feed.setCategory(category);
        feed.setEntries(entries);
        return feed;
    }

    @Override
    public FeedBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public FeedBuilder link(String link) {
        this.link = link;
        return this;
    }

    public FeedBuilder language(String language) {
        this.language = language;
        return this;
    }

    public FeedBuilder copyright(boolean copyright) {
        this.copyright = copyright;
        return this;
    }

    public FeedBuilder pubDate(Date pubDate) {
        this.pubDate = pubDate;
        return this;
    }
    
    public FeedBuilder createDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public FeedBuilder feeder(Feeder feeder) {
        this.feeder = feeder;
        return this;
    }

    public FeedBuilder status(BaseStatus status) {
        this.status = status;
        return this;
    }
    
    public FeedBuilder category(Category category) {
        this.category = category;
        return this;
    }
    
    public FeedBuilder entries(List<FeedMessage> entries) {
        this.entries = entries;
        return this;
    }

}
