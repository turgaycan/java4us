/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.domain.builder;

import com.java4us.domain.Feed;
import com.java4us.domain.FeedMessage;
import com.java4us.domain.builder.utils.TestDateUtils;
import com.java4us.domain.common.enums.BaseStatus;
import com.java4us.domain.common.enums.Category;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Date;

/**
 *
 * @author turgay
 */
public class FeedMessageBuilder extends BaseBuilder<FeedMessage, FeedMessageBuilder> {

    private Long id;
    private String title = RandomStringUtils.random(10);
    private String description = RandomStringUtils.random(20);
    private String link = RandomStringUtils.random(20);
    private String author = RandomStringUtils.random(20);
    private String guid = RandomStringUtils.random(20);
    private Feed feed;
    private Date pubdate = TestDateUtils.toDate("10-10-2010");
    private Date createDate = TestDateUtils.toDate("10-10-2010");
    private boolean proceed = false;
    private BaseStatus status = BaseStatus.Active;
    private Category category = Category.JAVA;

    @Override
    protected FeedMessage doBuild() {
        FeedMessage feedMessage = new FeedMessage();
        feedMessage.setId(id);
        feedMessage.setTitle(title);
        feedMessage.setDescription(description);
        feedMessage.setLink(link);
        feedMessage.setAuthor(author);
        feedMessage.setGuid(guid);
        feedMessage.setPubdate(pubdate);
        feedMessage.setCreateDate(createDate);
        feedMessage.setFeed(feed);
        feedMessage.setProceed(proceed);
        feedMessage.setStatus(status);
        feedMessage.setCategory(category);
        return feedMessage;
    }

    @Override
    public FeedMessageBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public FeedMessageBuilder title(String title) {
        this.title = title;
        return this;
    }

    public FeedMessageBuilder description(String description) {
        this.description = description;
        return this;
    }

    public FeedMessageBuilder link(String link) {
        this.link = link;
        return this;
    }

    public FeedMessageBuilder author(String author) {
        this.author = author;
        return this;
    }

    public FeedMessageBuilder guid(String guid) {
        this.guid = guid;
        return this;
    }

    public FeedMessageBuilder pubdate(Date pubdate) {
        this.pubdate = pubdate;
        return this;
    }

    public FeedMessageBuilder feed(Feed feed) {
        this.feed = feed;
        return this;
    }

    public FeedMessageBuilder createDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public FeedMessageBuilder proceed(boolean proceed) {
        this.proceed = proceed;
        return this;
    }

    public FeedMessageBuilder status(BaseStatus status) {
        this.status = status;
        return this;
    }

    public FeedMessageBuilder category(Category category) {
        this.category = category;
        return this;
    }

}
