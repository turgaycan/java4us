package com.java4us.commons.service;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.java4us.commons.service.feed.Java4usRSSFeedParser;
import com.java4us.domain.Feed;
import com.sun.syndication.io.FeedException;

@RunWith(MockitoJUnitRunner.class)
public class Java4usRSSFeedParserTest {
	//TODO : Internet Connection must!
	
	@InjectMocks
	private Java4usRSSFeedParser java4usRSSFeedParser;
	
	@Ignore
	@Test
	public void shouldParseRssFeed() throws IllegalArgumentException, IOException, FeedException, ParseException{		
		final Feed rssFeed = java4usRSSFeedParser.readFeed("http://feeds.feedburner.com/JavaCodeGeeks");
		assertNotNull(rssFeed);
		final Feed rssFeed2 = java4usRSSFeedParser.readFeed("http://www.vogella.com/article.rss");
		assertNotNull(rssFeed2);				
	}
}
