/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service.feed;

import com.java4us.commons.dao.feed.FeedDao;
import com.java4us.commons.service.feed.FeedService;
import com.java4us.domain.Feed;
import com.java4us.domain.FeedMessage;
import com.java4us.domain.Feeder;
import com.java4us.domain.builder.FeedBuilder;
import com.java4us.domain.builder.FeedMessageBuilder;
import com.java4us.domain.builder.FeederBuilder;
import com.java4us.domain.builder.utils.TestDateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author turgay
 */
@RunWith(MockitoJUnitRunner.class)
public class FeedServiceTest {

    @InjectMocks
    private FeedService feedService;

    @Mock
    private FeedDao feedDao;
        
    @Test
    public void shouldSaveFeedWithFeedMessages() {
        Date currTime = TestDateUtils.toDate("10-10-2010");
        Feeder feeder = new FeederBuilder().createDate(currTime).buildWithId();
        Feed feed = new FeedBuilder().feeder(feeder).pubDate(currTime).buildWithId();
        List<Feed> feeds = new ArrayList<>();
        feeds.add(feed);
        feeder.setFeeds(feeds);
        FeedMessage feedMessage = new FeedMessageBuilder().feed(feed).pubdate(currTime).createDate(currTime).buildWithId();
        List<FeedMessage> entries = new ArrayList<>();
        entries.add(feedMessage);
        feed.setEntries(entries);
        
        feedService.save(feed);
        
        ArgumentCaptor<Feed> captor = ArgumentCaptor.forClass(Feed.class);
        
        verify(feedDao).persist(captor.capture());
        
        assertThat(captor.getValue(), equalTo(feed));
    }

    @Test
    public void shouldSaveFeedWithoutFeedMessages() {
        Date currTime = TestDateUtils.toDate("10-10-2010");
        Feeder feeder1 = new FeederBuilder().id(new Long(1)).createDate(currTime).build();
        Feed feed1 = new FeedBuilder().id(new Long(1)).feeder(feeder1).entries(null).pubDate(currTime).build();
        feedService.save(feed1);
        
        ArgumentCaptor<Feed> captor = ArgumentCaptor.forClass(Feed.class);
        
        verify(feedDao).persist(captor.capture());
        
        assertThat(captor.getValue(), equalTo(feed1));
    }
}
