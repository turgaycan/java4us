package com.java4us.commons.service.feed;

import com.java4us.common.model.FeedMessageDTO;
import com.java4us.domain.FeedMessage;
import org.springframework.stereotype.Component;

/**
 * Created by turgaycan on 11/23/14.
 */
@Component
public class FeedMessageDTOConverter {

    public FeedMessageDTO convert(FeedMessage feedMessage) {
        return new FeedMessageDTO(feedMessage.getId(), feedMessage.getTitle(), feedMessage.getLink(), feedMessage.getPubdate(),
                feedMessage.getCreateDate(), feedMessage.getCategory(), feedMessage.getStatus());
    }
}
