package com.java4us.commons.component.utils;

import com.java4us.documents.domain.FeedMessageSearch;
import com.java4us.documents.domain.builder.FeedMessageSearchBuilder;
import com.java4us.domain.FeedMessage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by turgaycan on 12/30/14.
 */
@Component
public class FeedMessageSearchConverter {

    public List<FeedMessageSearch> convertAll(List<FeedMessage> feedMessageList) {
        return feedMessageList.stream().map(this::convert).collect(Collectors.toList());
    }


    public FeedMessageSearch convert(FeedMessage feedMessage) {
        return new FeedMessageSearchBuilder().id(feedMessage.getId().toString()).title(feedMessage.getTitle())
                .category(feedMessage.getCategory()).link(feedMessage.getLink()).pubDate(feedMessage.getPubdate()).viewCount(0)
                .gotoLinkCount(0).build();
    }
}
