package com.java4us.dao.statistics;

import com.java4us.commons.utils.Clock;
import com.java4us.dao.core.AbstractDataAccessTest;
import com.java4us.dao.statistic.StatisticsDao;
import com.java4us.domain.Feed;
import com.java4us.domain.Feeder;
import com.java4us.domain.builder.FeedBuilder;
import com.java4us.domain.builder.FeedMessageBuilder;
import com.java4us.domain.builder.FeederBuilder;
import com.java4us.domain.builder.utils.TestDateUtils;
import com.java4us.domain.common.enums.FeederStatus;
import com.java4us.web.model.StatisticModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class StatisticsDaoTest extends AbstractDataAccessTest{

	@Autowired
	private StatisticsDao dao;
	
	@Test
	public void shouldGetStatistics(){
		Clock.freeze();
        Date currTime = TestDateUtils.toDate("10-10-2010");
        Feeder feeder1 = new FeederBuilder().id(new Long(1)).status(FeederStatus.ACCEPTED).createDate(currTime).persist(getSession());
        Feed feed = new FeedBuilder().id(new Long(1)).feeder(feeder1).pubDate(currTime).persist(getSession());
        new FeedMessageBuilder().id(new Long(1)).feed(feed).createDate(currTime).proceed(true).pubdate(currTime).persist(getSession());
        new FeedMessageBuilder().id(new Long(2)).feed(feed).createDate(currTime).proceed(true).pubdate(currTime).persist(getSession());
        new FeedMessageBuilder().id(new Long(3)).feed(feed).createDate(currTime).proceed(true).pubdate(currTime).persist(getSession());
     
        flushAndClear();
        
        StatisticModel model = dao.getJavaStatisticsModel();
        
        assertEquals(model.getFeedersCount(), Long.valueOf(1));
        assertEquals(model.getFeedsCount(), Long.valueOf(1));
        assertEquals(model.getFeedMessagesCount(), Long.valueOf(3));
	}
}
