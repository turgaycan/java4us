package com.java4us.common.model;

import com.java4us.domain.FeedMessage;
import com.java4us.domain.Subscriber;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by turgaycan on 12/12/14.
 */
public class SubscriberMailDTO implements Serializable {

    private static final long serialVersionUID = 3533750194456036314L;
    private Subscriber subscriber;
    private LinkedList<FeedMessage> feedMessageList;

    public SubscriberMailDTO() {
    }

    public static SubscriberMailDTO buildSubscriberMailDTO(Subscriber subscriber, LinkedList<FeedMessage> feedMessageList) {
        SubscriberMailDTO subscriberMailDTO = new SubscriberMailDTO();
        subscriberMailDTO.setSubscriber(subscriber);
        subscriberMailDTO.setFeedMessageList(feedMessageList);
        return subscriberMailDTO;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public LinkedList<FeedMessage> getFeedMessageList() {
        return feedMessageList;
    }

    public void setFeedMessageList(LinkedList<FeedMessage> feedMessageList) {
        this.feedMessageList = feedMessageList;
    }
}
