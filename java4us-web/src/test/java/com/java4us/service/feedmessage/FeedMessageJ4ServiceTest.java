package com.java4us.service.feedmessage;

import com.java4us.commons.cache.CacheService;
import com.java4us.commons.service.feed.FeedMessageService;
import com.java4us.domain.FeedMessage;
import com.java4us.domain.builder.FeedMessageBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by turgaycan on 6/21/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class FeedMessageJ4ServiceTest {

    @InjectMocks
    private FeedMessageJ4Service service;

    @Mock
    private FeedMessageService feedMessageService;

    @Mock
    private CacheService java4UsCacheService;

    @Test
    public void shouldReturnFeedMessageFromCacheAndApply() {
        FeedMessage feedMessage = new FeedMessageBuilder().id(1l).viewCount(10).build();
        when(java4UsCacheService.get("feedMessage_1")).thenReturn(feedMessage);

        FeedMessage fromCache = service.findFeedMessageById(1l);

        assertThat(fromCache.getViewCount(), is(11));
        verifyZeroInteractions(feedMessageService);
    }

    @Test
    public void shouldReturnFeedMessageFromDB() {
        FeedMessage feedMessage = new FeedMessageBuilder().id(1l).viewCount(10).build();
        when(java4UsCacheService.get("feedMessage_1")).thenReturn(null);
        when(feedMessageService.findById(1l)).thenReturn(feedMessage);

        FeedMessage actualFeedMessage = service.findFeedMessageById(1l);

        assertThat(actualFeedMessage, is(feedMessage));
        assertThat(actualFeedMessage.getViewCount(), is(11));
    }

    @Test
    public void shouldReturnNullAndNotPutInCacheIfCacheAndDBNotHasValue() {
        when(java4UsCacheService.get("feedMessage_1")).thenReturn(null);
        when(feedMessageService.findById(1l)).thenReturn(null);

        FeedMessage actualFeedMessage = service.findFeedMessageById(1l);

        assertThat(actualFeedMessage, nullValue());
    }

    @Test
    public void shouldUpdateStatisticFeedMessageFromCacheAndApply() {
        FeedMessage feedMessage = new FeedMessageBuilder().id(1l).viewCount(10).gotoLinkCount(10).build();
        when(java4UsCacheService.get("feedMessage_1")).thenReturn(feedMessage);

        service.updateCountStatistics(1l);

        assertThat(feedMessage.getGoToLinkCount(), is(11));
        assertThat(feedMessage.getViewCount(), is(10));
        verifyZeroInteractions(feedMessageService);
    }

    @Test
    public void shouldUpdateStatisticFeedMessageFromDB() {
        FeedMessage feedMessage = new FeedMessageBuilder().id(1l).viewCount(10).gotoLinkCount(10).build();
        when(java4UsCacheService.get("feedMessage_1")).thenReturn(null);
        when(feedMessageService.findById(1l)).thenReturn(feedMessage);

        service.updateCountStatistics(1l);

        assertThat(feedMessage.getGoToLinkCount(), is(11));
        assertThat(feedMessage.getViewCount(), is(10));
    }

}