package com.java4us.commons.service.feed;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.java4us.commons.utils.Clock;
import com.java4us.commons.utils.DateUtils;
import com.java4us.domain.Feed;
import com.java4us.domain.FeedMessage;
import com.java4us.domain.common.enums.BaseStatus;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Component
public class Java4usRSSFeedParser {

	private static final int MAXDESCSIZE = 4000;
	private URL url;

	public Feed readFeed(String feedUrl) throws IOException,
			IllegalArgumentException, FeedException, ParseException {
		url = new URL(feedUrl);
		Feed feed = new Feed(feedUrl, "TR", false, BaseStatus.Active,
				Clock.getTime(), Clock.getTime());
		HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed syncFeed = input.build(new XmlReader(httpcon));
		List<?> entries = syncFeed.getEntries();
		Iterator<?> itEntries = entries.iterator();
		while (itEntries.hasNext()) {
			SyndEntry entry = (SyndEntry) itEntries.next();
			FeedMessage message = new FeedMessage();
			message.setAuthor(entry.getAuthor());
			SyndContent description = entry.getDescription();
			message.setDescription(description != null ? (description
					.getValue().length() > MAXDESCSIZE ? description.getValue()
					.substring(0, MAXDESCSIZE) : description.getValue()) : "");
			message.setGuid("");
			message.setLink(entry.getLink());
			message.setTitle(entry.getTitle());
			message.setStatus(BaseStatus.Active);
			if (entry.getPublishedDate() == null) {
				message.setPubdate(Clock.getTime());
			} else {
				message.setPubdate(DateUtils.toLongFormatDate(entry
						.getPublishedDate()));
			}
			message.setCreateDate(Clock.getTime());
			feed.getEntries().add(message);
		}
		return feed;
	}
}
