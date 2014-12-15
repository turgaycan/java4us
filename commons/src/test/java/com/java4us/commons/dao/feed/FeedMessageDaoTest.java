/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.dao.feed;

import com.java4us.commons.dao.core.AbstractDataAccessTest;
import com.java4us.commons.utils.Clock;
import com.java4us.commons.utils.DateUtils;
import com.java4us.commons.utils.criteria.FeedMessageSearchCriteria;
import com.java4us.domain.Feed;
import com.java4us.domain.FeedMessage;
import com.java4us.domain.Feeder;
import com.java4us.domain.builder.FeedBuilder;
import com.java4us.domain.builder.FeedMessageBuilder;
import com.java4us.domain.builder.FeederBuilder;
import com.java4us.domain.builder.utils.TestDateUtils;
import com.java4us.domain.common.enums.BaseStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author turgay
 */
public class FeedMessageDaoTest extends AbstractDataAccessTest {

    @Autowired
    private FeedMessageDao dao;

    private FeedMessageSearchCriteria filter = new FeedMessageSearchCriteria();

    @Test
    public void shouldFindPartialFeedMessages() {
        Clock.freeze();
        filter.setProceed(true);
        Date currTime = TestDateUtils.toDate("10-10-2010");
        Feeder feeder1 = new FeederBuilder().id(new Long(1)).createDate(currTime).persist(getSession());
        Feed feed = new FeedBuilder().id(new Long(1)).feeder(feeder1).pubDate(currTime).persist(getSession());
        FeedMessage feedMessage1 = new FeedMessageBuilder().id(new Long(1)).feed(feed).createDate(currTime).proceed(true).pubdate(currTime).persist(getSession());
        FeedMessage feedMessage2 = new FeedMessageBuilder().id(new Long(2)).feed(feed).createDate(currTime).proceed(true).pubdate(currTime).persist(getSession());
        FeedMessage feedMessage3 = new FeedMessageBuilder().id(new Long(3)).feed(feed).createDate(currTime).proceed(true).pubdate(currTime).persist(getSession());

        flushAndClear();

        List<FeedMessage> list = dao.findPartialByFilter(0, 10, "", "", filter);

        assertThat(list.isEmpty(), equalTo(false));
        assertThat(list.size(), equalTo(3));
        assertThat(list, hasItems(feedMessage1, feedMessage2, feedMessage3));
        Clock.unfreeze();
    }

    @Test
    public void shouldFindPartialFeedMessagesOrderByCreateDateDesc() {
        Clock.freeze();
        Date currTime = TestDateUtils.toDate("10-10-2010");
        Feeder feeder1 = new FeederBuilder().id(new Long(1)).createDate(currTime).persist(getSession());
        Feed feed = new FeedBuilder().id(new Long(1)).feeder(feeder1).pubDate(TestDateUtils.toDateTimeWithSeconds("01-03-2014 02:19:26")).persist(getSession());
        FeedMessage feedMessage1 = new FeedMessageBuilder().id(new Long(1)).title("Üçüncü").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:26")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:26")).persist(getSession());
        FeedMessage feedMessage2 = new FeedMessageBuilder().id(new Long(2)).title("İkinci").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:27")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:27")).persist(getSession());
        FeedMessage feedMessage3 = new FeedMessageBuilder().id(new Long(3)).title("Dördüncü").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:21")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:21")).persist(getSession());
        FeedMessage feedMessage4 = new FeedMessageBuilder().id(new Long(4)).title("Beşinci").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:18:21")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:18:21")).persist(getSession());
        FeedMessage feedMessage5 = new FeedMessageBuilder().id(new Long(5)).title("Birinci").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:20:21")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:20:21")).persist(getSession());
        flushAndClear();
        List<FeedMessage> list = dao.findPartialByFilter(0, 10, "createDate", null, filter);
        List<FeedMessage> orderedList = new ArrayList<>(Arrays.asList(feedMessage5, feedMessage2, feedMessage1, feedMessage3, feedMessage4));
        assertThat(list.isEmpty(), equalTo(false));
        assertThat(list.size(), equalTo(5));
        assertThat(list, hasItems(feedMessage5, feedMessage2, feedMessage1, feedMessage3, feedMessage4));
        assertEquals(list, orderedList);
        Clock.unfreeze();
    }

    @Test
    public void shouldFindNoneProceedFeedMessages() {
        Clock.freeze();
        Date currTime = TestDateUtils.toDate("10-10-2010");
        Feeder feeder1 = new FeederBuilder().id(new Long(1)).createDate(currTime).persist(getSession());
        Feed feed = new FeedBuilder().id(new Long(1)).feeder(feeder1).pubDate(TestDateUtils.toDateTimeWithSeconds("01-03-2014 02:19:26")).persist(getSession());
        FeedMessage feedMessage1 = new FeedMessageBuilder().id(new Long(1)).title("Üçüncü").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:26")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:26")).proceed(true).persist(getSession());
        FeedMessage feedMessage2 = new FeedMessageBuilder().id(new Long(2)).title("İkinci").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:27")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:27")).proceed(false).persist(getSession());
        FeedMessage feedMessage3 = new FeedMessageBuilder().id(new Long(3)).title("Dördüncü").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:21")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:21")).proceed(true).persist(getSession());
        FeedMessage feedMessage4 = new FeedMessageBuilder().id(new Long(4)).title("Beşinci").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:18:21")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:18:21")).proceed(true).persist(getSession());
        FeedMessage feedMessage5 = new FeedMessageBuilder().id(new Long(5)).title("Birinci").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:20:21")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:20:21")).proceed(false).persist(getSession());
        flushAndClear();
        List<FeedMessage> list = dao.findNoneProceedMessages();
        assertThat(list, hasItems(feedMessage5, feedMessage2));
        Clock.unfreeze();
    }

    @Test
    public void shouldFindProceedFeedMessages() {
        Clock.freeze();
        Date currTime = TestDateUtils.toDate("10-10-2010");
        filter.setProceed(true);
        Feeder feeder1 = new FeederBuilder().id(new Long(1)).createDate(currTime).persist(getSession());
        Feed feed = new FeedBuilder().id(new Long(1)).feeder(feeder1).pubDate(TestDateUtils.toDateTimeWithSeconds("01-03-2014 02:19:26")).persist(getSession());
        FeedMessage feedMessage1 = new FeedMessageBuilder().id(new Long(1)).title("Üçüncü").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:26")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:26")).proceed(true).persist(getSession());
        FeedMessage feedMessage2 = new FeedMessageBuilder().id(new Long(2)).title("İkinci").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:27")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:27")).proceed(false).persist(getSession());
        FeedMessage feedMessage3 = new FeedMessageBuilder().id(new Long(3)).title("Dördüncü").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:21")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:21")).proceed(true).persist(getSession());
        FeedMessage feedMessage4 = new FeedMessageBuilder().id(new Long(4)).title("Beşinci").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:18:21")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:18:21")).proceed(true).persist(getSession());
        FeedMessage feedMessage5 = new FeedMessageBuilder().id(new Long(5)).title("Birinci").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:20:21")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:20:21")).proceed(false).persist(getSession());
        flushAndClear();
        List<FeedMessage> list = dao.findPartialByFilter(0, 10, "", "", filter);
        assertThat(list, hasItems(feedMessage1, feedMessage3, feedMessage4));
        Clock.unfreeze();
    }

    @Test
    public void shouldFindProceedFeedMessagesLinks() {
        Clock.freeze();
        Date currTime = TestDateUtils.toDate("10-10-2010");
        Feeder feeder1 = new FeederBuilder().id(new Long(1)).createDate(currTime).persist(getSession());
        Feed feed = new FeedBuilder().id(new Long(1)).feeder(feeder1).pubDate(TestDateUtils.toDateTimeWithSeconds("01-03-2014 02:19:26")).persist(getSession());
        FeedMessage feedMessage1 = new FeedMessageBuilder().id(new Long(1)).title("Üçüncü").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:26")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:26")).proceed(true).persist(getSession());
        FeedMessage feedMessage2 = new FeedMessageBuilder().id(new Long(2)).title("İkinci").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:27")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:27")).proceed(false).persist(getSession());
        FeedMessage feedMessage3 = new FeedMessageBuilder().id(new Long(3)).title("Dördüncü").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:21")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:21")).proceed(true).persist(getSession());
        FeedMessage feedMessage4 = new FeedMessageBuilder().id(new Long(4)).title("Beşinci").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:18:21")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:18:21")).proceed(true).persist(getSession());
        FeedMessage feedMessage5 = new FeedMessageBuilder().id(new Long(5)).title("Birinci").feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:20:21")).pubdate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:20:21")).proceed(false).persist(getSession());
        flushAndClear();
        List<String> list = dao.findAllProceedMessagesLinks();
        assertThat(list.size(), equalTo(3));
        Clock.unfreeze();
    }

    @Test
    public void shouldFindProceedWeeklyFeedMessages() {
        Clock.freeze();
        Date currTime = TestDateUtils.toDate("10-03-2014");
        Feeder feeder1 = new FeederBuilder().id(new Long(1)).createDate(currTime).persist(getSession());
        Feed feed = new FeedBuilder().id(new Long(1)).feeder(feeder1).pubDate(TestDateUtils.toDate("01-10-2010")).persist(getSession());

        FeedMessage feedMessage1 = new FeedMessageBuilder().id(new Long(1)).viewCount(10).title("1").status(BaseStatus.Active).feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("01-03-2014 02:19:26")).proceed(true).persist(getSession());
        FeedMessage feedMessage2 = new FeedMessageBuilder().id(new Long(2)).viewCount(46).title("2").status(BaseStatus.Active).feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("04-03-2014 02:19:27")).proceed(false).persist(getSession());
        FeedMessage feedMessage3 = new FeedMessageBuilder().id(new Long(3)).viewCount(55).title("3").status(BaseStatus.Active).feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:21")).proceed(true).persist(getSession());
        FeedMessage feedMessage4 = new FeedMessageBuilder().id(new Long(4)).viewCount(23).title("4").status(BaseStatus.Active).feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("08-03-2014 02:18:21")).proceed(true).persist(getSession());
        FeedMessage feedMessage5 = new FeedMessageBuilder().id(new Long(5)).viewCount(10).title("5").status(BaseStatus.Active).feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("05-03-2014 02:20:21")).proceed(true).persist(getSession());
        FeedMessage feedMessage6 = new FeedMessageBuilder().id(new Long(6)).viewCount(33).title("6").status(BaseStatus.Active).feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("03-03-2014 02:19:26")).proceed(true).persist(getSession());
        FeedMessage feedMessage7 = new FeedMessageBuilder().id(new Long(7)).viewCount(34).title("7").status(BaseStatus.Active).feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("06-03-2014 02:19:27")).proceed(true).persist(getSession());
        FeedMessage feedMessage8 = new FeedMessageBuilder().id(new Long(8)).viewCount(1).title("8").status(BaseStatus.Deactive).feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("07-03-2014 02:19:21")).proceed(true).persist(getSession());
        FeedMessage feedMessage9 = new FeedMessageBuilder().id(new Long(9)).viewCount(49).title("9").status(BaseStatus.Active).feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("11-03-2014 02:18:21")).proceed(true).persist(getSession());
        FeedMessage feedMessage10 = new FeedMessageBuilder().id(new Long(10)).viewCount(90).title("10").status(BaseStatus.Active).feed(feed).createDate(TestDateUtils.toDateTimeWithSeconds("08-03-2014 02:20:21")).proceed(false).persist(getSession());

        flushAndClear();

        List<FeedMessage> feedMessages = dao.findWeeklyFeedMessages(currTime, DateUtils.addDays(currTime, -7));
        assertThat(feedMessages.size(), equalTo(5));
        assertThat(feedMessages, hasItems(feedMessage4, feedMessage5, feedMessage6, feedMessage7, feedMessage3));
        Clock.unfreeze();
    }

}
