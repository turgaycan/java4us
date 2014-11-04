/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service.feed;

import com.java4us.commons.dao.feed.FeederDao;
import com.java4us.commons.service.feed.FeederService;
import com.java4us.commons.utils.criteria.FeederSearchCriteria;
import com.java4us.domain.Feed;
import com.java4us.domain.Feeder;
import com.java4us.domain.builder.FeedBuilder;
import com.java4us.domain.builder.FeederBuilder;
import com.java4us.domain.builder.utils.TestDateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author turgay
 */
@RunWith(MockitoJUnitRunner.class)
public class FeederServiceTest {

	private FeederSearchCriteria filter = new FeederSearchCriteria();
	
    @InjectMocks
    private FeederService service;

    @Mock
    private FeederDao feederDao;

    @Test
    public void shouldSaveFeederWithoutFeeds() {
        Date currTime = TestDateUtils.toDate("10-10-2010");
        Feeder feeder = new FeederBuilder().createDate(currTime).buildWithId();
        service.save(feeder);
        verify(feederDao, times(1)).persist(feeder);
    }

    @Test
    public void shouldSaveFeederWithFeeds() {
        Date currTime = TestDateUtils.toDate("10-10-2010");
        Feeder feeder = new FeederBuilder().createDate(currTime).buildWithId();
        Feed feed = new FeedBuilder().feeder(feeder).pubDate(currTime).buildWithId();
        List<Feed> entries = new ArrayList<Feed>();
        entries.add(feed);
        feeder.setFeeds(entries);
        service.save(feeder);
        verify(feederDao, times(1)).persist(feeder);
    }
    
    @Test
    public void shouldReturnFeederCountByFilter(){
       when(feederDao.getRowCountFeederList(filter)).thenReturn(3);
       int rowCount = service.getRowCount(filter);
       
       assertEquals(rowCount, 3);
    }
}
