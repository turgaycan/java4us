/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service;

import com.java4us.commons.service.feed.RSSFeedParserService;
import com.java4us.domain.Feed;
import com.java4us.domain.FeedMessage;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;


/**
 *
 * @author turgay
 */
@RunWith(MockitoJUnitRunner.class)
public class RSSFeedParserServiceTest {

    @InjectMocks
    private RSSFeedParserService service;
     //TODO : Internet Connection must!

    @Ignore
    @Test
    public void shouldParseFeedMessage() {
        Feed feed = service.readFeed("http://www.vogella.com/article.rss");
        List<FeedMessage> feedMessages = new ArrayList<>();
        for (FeedMessage message : feed.getEntries()) {
            feedMessages.add(message);
        }
        assertThat(feedMessages.isEmpty(), equalTo(false));
    }
    
    @Ignore
    @Test
    public void shouldParseFeedMessageContent() {
        Feed feed = service.readFeed("http://www.kodlapaylas.com/kprss/kpallrss.rss");
        List<FeedMessage> feedMessages = new ArrayList<>();
        for (FeedMessage message : feed.getEntries()) {
            feedMessages.add(message);
        }
        assertNotNull(feedMessages.get(0).getPubdate());
        assertNotNull(feedMessages.get(0).getCreateDate());
    }
    
}
