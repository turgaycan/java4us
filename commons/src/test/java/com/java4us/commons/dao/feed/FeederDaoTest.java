/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.dao.feed;

import com.java4us.commons.dao.core.AbstractDataAccessTest;
import com.java4us.commons.utils.Clock;
import com.java4us.commons.utils.criteria.FeederSearchCriteria;
import com.java4us.domain.Feed;
import com.java4us.domain.Feeder;
import com.java4us.domain.builder.FeedBuilder;
import com.java4us.domain.builder.FeederBuilder;
import com.java4us.domain.builder.utils.TestDateUtils;
import com.java4us.domain.common.enums.FeederStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 *
 * @author turgay
 */
public class FeederDaoTest extends AbstractDataAccessTest {

    @Autowired
    private FeederDao dao;

    private FeederSearchCriteria filter = new FeederSearchCriteria();

    @Test
    public void shouldFindPartialFeeders() {
        Clock.freeze();
        Date currTime = TestDateUtils.toDate("10-10-2010");
        Feeder feeder1 = new FeederBuilder().id(new Long(1)).createDate(currTime).persist(getSession());
        Feeder feeder2 = new FeederBuilder().id(new Long(2)).createDate(currTime).persist(getSession());
        Feeder feeder3 = new FeederBuilder().id(new Long(3)).createDate(currTime).persist(getSession());
        Feeder feeder4 = new FeederBuilder().id(new Long(4)).createDate(currTime).persist(getSession());
        Feeder feeder5 = new FeederBuilder().id(new Long(5)).createDate(currTime).persist(getSession());
        flushAndClear();
        
        List<Feeder> list = dao.findPartialByFilter(0, 10, "createDate", "createDate", filter);
        
        assertThat(list.isEmpty(), equalTo(false));
        assertThat(list.size(), equalTo(5));
        assertThat(list, hasItems(feeder1, feeder2, feeder3, feeder4, feeder5));
        Clock.unfreeze();
    }

    @Test
    public void shouldFindActiveSubscribers() {
        Clock.freeze();
        Date currTime = TestDateUtils.toDate("10-10-2010");
        Feeder feeder1 = new FeederBuilder().id(new Long(1)).createDate(currTime).status(FeederStatus.ACCEPTED).persist(getSession());
        Feeder feeder2 = new FeederBuilder().id(new Long(2)).createDate(currTime).status(FeederStatus.WAITING).persist(getSession());
        Feeder feeder3 = new FeederBuilder().id(new Long(3)).createDate(currTime).status(FeederStatus.ACCEPTED).persist(getSession());
        Feeder feeder4 = new FeederBuilder().id(new Long(4)).createDate(currTime).status(FeederStatus.ACCEPTED).persist(getSession());
        Feeder feeder5 = new FeederBuilder().id(new Long(5)).createDate(currTime).status(FeederStatus.WAITING).persist(getSession());

        flushAndClear();
        List<Feeder> list = dao.findAcceptedFeeders();
        assertThat(list.isEmpty(), equalTo(false));
        assertThat(list.size(), equalTo(3));
        assertThat(list, hasItems(feeder1, feeder3, feeder4));
        Clock.unfreeze();
    }

    @Test
    public void shouldFindSubscribersFeeds() {
        Clock.freeze();
        Date currTime = TestDateUtils.toDate("10-10-2010");
        Feeder feeder1 = new FeederBuilder().id(new Long(1)).createDate(currTime).status(FeederStatus.ACCEPTED).persist(getSession());
        Feeder feeder2 = new FeederBuilder().id(new Long(2)).createDate(currTime).status(FeederStatus.WAITING).persist(getSession());
        Feeder feeder3 = new FeederBuilder().id(new Long(3)).createDate(currTime).status(FeederStatus.ACCEPTED).persist(getSession());
        Feeder feeder4 = new FeederBuilder().id(new Long(4)).createDate(currTime).status(FeederStatus.ACCEPTED).persist(getSession());
        Feeder feeder5 = new FeederBuilder().id(new Long(5)).createDate(currTime).status(FeederStatus.WAITING).persist(getSession());
        
        Feed feed1 = new FeedBuilder().id(new Long(1)).feeder(feeder1).pubDate(currTime).persist(getSession());
        Feed feed2 = new FeedBuilder().id(new Long(2)).feeder(feeder2).pubDate(currTime).persist(getSession());
        Feed feed3 = new FeedBuilder().id(new Long(3)).feeder(feeder1).pubDate(currTime).persist(getSession());
        Feed feed4 = new FeedBuilder().id(new Long(4)).feeder(feeder3).pubDate(currTime).persist(getSession());
        Feed feed5 = new FeedBuilder().id(new Long(5)).feeder(feeder4).pubDate(currTime).persist(getSession());
        Feed feed6 = new FeedBuilder().id(new Long(6)).feeder(feeder4).pubDate(currTime).persist(getSession());
        Feed feed7 = new FeedBuilder().id(new Long(7)).feeder(feeder5).pubDate(currTime).persist(getSession());

        flushAndClear();

        List<Feeder> list = dao.findAcceptedFeedersFeeds();
        assertFalse(list.isEmpty());
        assertThat(list.size(), equalTo(3));
        assertThat(list, hasItems(feeder1, feeder3, feeder4));
        assertThat(list.get(0).getFeeds().size(), equalTo(2));
        assertThat(list.get(0).getFeeds(), hasItems(feed1, feed3));
        assertThat(list.get(1).getFeeds().size(), equalTo(1));
        assertThat(list.get(1).getFeeds(), hasItems(feed4));
        assertThat(list.get(2).getFeeds().size(), equalTo(2));
        assertThat(list.get(2).getFeeds(), hasItems(feed5, feed6));
        Clock.unfreeze();
    }
    
    @Test
    public void shouldReturnFeederListCountByEmail(){
    	Date currTime = TestDateUtils.toDate("10-10-2010");
    	filter.setEmail("abc@g.com");
    	new FeederBuilder().id(new Long(1)).email("abc@g.com").domain("ab.com").createDate(currTime).status(FeederStatus.ACCEPTED).persist(getSession());
        new FeederBuilder().id(new Long(2)).email("ed@g.com").domain("de.com").createDate(currTime).status(FeederStatus.WAITING).persist(getSession());
        new FeederBuilder().id(new Long(3)).email("abc@g.com").domain("ef.com").createDate(currTime).status(FeederStatus.ACCEPTED).persist(getSession());
      
        flushAndClear();
        
        int rowCountFeederList = dao.getRowCountFeederList(filter);
        
        assertEquals(rowCountFeederList, 2);
    }
}
