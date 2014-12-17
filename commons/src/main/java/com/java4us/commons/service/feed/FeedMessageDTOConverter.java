package com.java4us.commons.service.feed;

import com.java4us.commons.service.url.UrlService;
import com.java4us.domain.model.FeedMessageDTO;
import com.java4us.domain.FeedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by turgaycan on 11/23/14.
 */
@Component
public class FeedMessageDTOConverter {

    @Autowired
    private UrlService urlService;

    public FeedMessageDTO convert(FeedMessage feedMessage) {
        return new FeedMessageDTO(feedMessage.getId(), feedMessage.getTitle(), feedMessage.getLink(), feedMessage.getPubdate(),
                feedMessage.getCreateDate(), feedMessage.getCategory(), feedMessage.getStatus());
    }

    public FeedMessageDTO convertFor(FeedMessage feedMessage) {
        return new FeedMessageDTO(feedMessage.getId(), feedMessage.getTitle(), urlService.getRssDetailPageUrl(feedMessage.getTitle(), feedMessage.getId()), feedMessage.getPubdate(),
                feedMessage.getCreateDate(), feedMessage.getCategory(), feedMessage.getStatus());
    }
}
