/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.dao.feed;

import com.java4us.domain.common.enums.BaseStatus;
import com.java4us.domain.builder.FeedBuilder;
import com.java4us.domain.builder.FeederBuilder;
import com.java4us.commons.utils.criteria.FeedSearchCriteria;
import com.java4us.commons.dao.core.AbstractDataAccessTest;
import com.java4us.commons.dao.feed.FeedDao;
import com.java4us.commons.utils.Clock;
import com.java4us.domain.Feed;
import com.java4us.domain.Feeder;
import com.java4us.domain.builder.utils.TestDateUtils;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author turgay
 */
public class FeedDaoTest extends AbstractDataAccessTest {

    @Autowired
    private FeedDao dao;

    private final FeedSearchCriteria filter = new FeedSearchCriteria();

    @Test
    public void shoulFindPartialFeeds() {
        Clock.freeze();
        Date currTime = TestDateUtils.toDate("10-10-2010");
        Feeder feeder1 = new FeederBuilder().id(new Long(1)).createDate(currTime).persist(getSession());
        Feeder feeder2 = new FeederBuilder().id(new Long(2)).createDate(currTime).persist(getSession());
        Feed feed1 = new FeedBuilder().id(new Long(1)).feeder(feeder1).pubDate(currTime).persist(getSession());
        Feed feed2 = new FeedBuilder().id(new Long(2)).feeder(feeder1).pubDate(currTime).persist(getSession());
        Feed feed3 = new FeedBuilder().id(new Long(3)).feeder(feeder1).pubDate(currTime).persist(getSession());
        Feed feed4 = new FeedBuilder().id(new Long(4)).feeder(feeder2).pubDate(currTime).persist(getSession());
        Feed feed5 = new FeedBuilder().id(new Long(5)).feeder(feeder2).pubDate(currTime).persist(getSession());
        Feed feed6 = new FeedBuilder().id(new Long(6)).feeder(feeder2).pubDate(currTime).persist(getSession());
        Feed feed7 = new FeedBuilder().id(new Long(7)).feeder(feeder2).pubDate(currTime).persist(getSession());

        flushAndClear();

        List<Feed> feedList = dao.findPartialByFilter(0, 10, "pubDate", "pubDate", filter);

        assertThat(feedList.size(), equalTo(7));
        assertThat(feedList, hasItems(feed1, feed2, feed3, feed4, feed5, feed6, feed7));
        Clock.unfreeze();
    }

    @Test
    public void shouldFindActiveFeeds() {
        Date currTime = TestDateUtils.toDate("10-10-2010");
        Feeder feeder1 = new FeederBuilder().id(new Long(1)).createDate(currTime).persist(getSession());
        Feeder feeder2 = new FeederBuilder().id(new Long(2)).createDate(currTime).persist(getSession());
        Feed feed1 = new FeedBuilder().id(new Long(1)).feeder(feeder1).pubDate(currTime).persist(getSession());
        Feed feed2 = new FeedBuilder().id(new Long(2)).feeder(feeder1).pubDate(currTime).status(BaseStatus.Deactive).persist(getSession());
        Feed feed3 = new FeedBuilder().id(new Long(3)).feeder(feeder1).pubDate(currTime).persist(getSession());
        Feed feed4 = new FeedBuilder().id(new Long(4)).feeder(feeder2).pubDate(currTime).persist(getSession());
        Feed feed5 = new FeedBuilder().id(new Long(5)).feeder(feeder2).pubDate(currTime).status(BaseStatus.Deactive).persist(getSession());
        Feed feed6 = new FeedBuilder().id(new Long(6)).feeder(feeder2).pubDate(currTime).status(BaseStatus.Deactive).persist(getSession());
        Feed feed7 = new FeedBuilder().id(new Long(7)).feeder(feeder2).pubDate(currTime).persist(getSession());

        flushAndClear();

        List<Feed> feedList = dao.findActiveFeeds();

        assertThat(feedList.size(), equalTo(4));
        assertThat(feedList, hasItems(feed1, feed3, feed4, feed7));

    }
    
    @Test
    public void shoulFindPartialFeedsByFeederEmail() {
        Clock.freeze();        
        filter.setFeederEmail("abc@a.com");
        Date currTime = TestDateUtils.toDate("10-10-2010");
        Feeder feeder1 = new FeederBuilder().id(new Long(1)).email("abc@a.com").createDate(currTime).persist(getSession());
        Feeder feeder2 = new FeederBuilder().id(new Long(2)).email("abc@c.com").createDate(currTime).persist(getSession());
        Feed feed1 = new FeedBuilder().id(new Long(1)).feeder(feeder1).pubDate(currTime).persist(getSession());
        Feed feed2 = new FeedBuilder().id(new Long(2)).feeder(feeder1).pubDate(currTime).persist(getSession());
        Feed feed3 = new FeedBuilder().id(new Long(3)).feeder(feeder1).pubDate(currTime).persist(getSession());
        Feed feed4 = new FeedBuilder().id(new Long(4)).feeder(feeder2).pubDate(currTime).persist(getSession());
        Feed feed5 = new FeedBuilder().id(new Long(5)).feeder(feeder2).pubDate(currTime).persist(getSession());
        Feed feed6 = new FeedBuilder().id(new Long(6)).feeder(feeder2).pubDate(currTime).persist(getSession());
        Feed feed7 = new FeedBuilder().id(new Long(7)).feeder(feeder2).pubDate(currTime).persist(getSession());        
        
        flushAndClear();

        List<Feed> feedList = dao.findPartialByFilter(0, 10, "pubDate", "pubDate", filter);

        assertThat(feedList.size(), equalTo(3));
        assertThat(feedList, hasItems(feed1, feed2, feed3));
        Clock.unfreeze();
    }

}
