/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.domain.builder;

import com.java4us.domain.Feed;
import com.java4us.domain.Feeder;
import com.java4us.domain.common.enums.FeederStatus;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Date;
import java.util.List;

/**
 *
 * @author turgay
 */
public class FeederBuilder extends BaseBuilder<Feeder, FeederBuilder> {

    private Long id;
    private String domain = RandomStringUtils.random(20);
    private String name = RandomStringUtils.random(20);
    private String surname = RandomStringUtils.random(20);
    private String email = RandomStringUtils.random(20);
    private FeederStatus status = FeederStatus.WAITING;
    private Date createDate;
    private List<Feed> feeds;

    @Override
    protected Feeder doBuild() {
        Feeder feeder = new Feeder();
        feeder.setId(id);
        feeder.setDomain(domain);
        feeder.setName(name);
        feeder.setSurname(surname);
        feeder.setEmail(email);
        feeder.setStatus(status);
        feeder.setCreateDate(createDate);
        feeder.setFeeds(feeds);
        return feeder;
    }

    @Override
    public FeederBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public FeederBuilder domain(String domain) {
        this.domain = domain;
        return this;
    }

    public FeederBuilder name(String name) {
        this.name = name;
        return this;
    }

    public FeederBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public FeederBuilder email(String email) {
        this.email = email;
        return this;
    }

    public FeederBuilder status(FeederStatus status) {
        this.status = status;
        return this;
    }

    public FeederBuilder createDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }
    
    public FeederBuilder feeds(List<Feed> feeds) {
        this.feeds = feeds;
        return this;
    }

}
