package com.java4us.commons.service.feed;

import com.java4us.commons.dao.feed.FeedMessageDao;
import com.java4us.domain.FeedMessage;
import com.java4us.domain.builder.FeedMessageBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FeedMessageService.class)
public class FeedMessageServiceTest {

    @InjectMocks
    private FeedMessageService service;

    @Mock
    private FeedMessageDao feedMessageDao;

    @Test
    public void shouldReturnFindWeeklyFeedMessages(){
        mockStatic(FeedMessageService.class);
        when(FeedMessageService.getWeeklfeedmessagesize()).thenReturn(3);
        FeedMessage feedMessage1 = new FeedMessageBuilder().buildWithId();
        FeedMessage feedMessage2 = new FeedMessageBuilder().buildWithId();
        FeedMessage feedMessage3 = new FeedMessageBuilder().buildWithId();
        FeedMessage feedMessage4 = new FeedMessageBuilder().buildWithId();
        FeedMessage feedMessage5 = new FeedMessageBuilder().buildWithId();
        List<FeedMessage> feedMessageList = Arrays.asList(feedMessage1, feedMessage2, feedMessage3, feedMessage4, feedMessage5);
        when(feedMessageDao.findWeeklyFeedMessages(any(), any())).thenReturn(feedMessageList);

        LinkedList<FeedMessage> weeklyFeedMessages = service.findWeeklyFeedMessages();

        assertThat(weeklyFeedMessages.size(), equalTo(3));
        assertThat(weeklyFeedMessages, hasItems(feedMessage1, feedMessage2, feedMessage3));
    }
}