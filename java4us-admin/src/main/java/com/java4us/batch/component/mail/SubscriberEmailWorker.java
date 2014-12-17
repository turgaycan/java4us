package com.java4us.batch.component.mail;

import com.java4us.amqp.mail.SubscriberMQMailProducer;
import com.java4us.batch.component.Worker;
import com.java4us.commons.service.feed.FeedMessageDTOConverter;
import com.java4us.commons.service.feed.FeedMessageService;
import com.java4us.commons.service.member.SubscriberService;
import com.java4us.domain.FeedMessage;
import com.java4us.domain.Subscriber;
import com.java4us.domain.model.FeedMessageDTO;
import com.java4us.domain.model.SubscriberMailDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by turgaycan on 12/12/14.
 */
public class SubscriberEmailWorker implements Worker {

    protected static Logger LOGGER = Logger.getLogger("subscriberEmailWorker");

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private FeedMessageService feedMessageService;

    @Autowired
    private SubscriberMQMailProducer subscriberMQMailProducer;

    @Autowired
    private FeedMessageDTOConverter feedMessageDTOConverter;

    @Override
    public void work() {
        LinkedList<SubscriberMailDTO> subscriberMailDTOs = prepareSubsrcibersMails();
        for (SubscriberMailDTO subscriberMailDTO : subscriberMailDTOs) {
            subscriberMQMailProducer.execute(subscriberMailDTO);
        }

    }

    private LinkedList<SubscriberMailDTO> prepareSubsrcibersMails() {
        List<Subscriber> subscribers = subscriberService.findAllowSubscriberList();
        LinkedList<FeedMessageDTO> feedMessageDTOs = buildFeedMessages();
        LinkedList<SubscriberMailDTO> subscriberMailDTOs = new LinkedList<>();
        for (Subscriber subscriber : subscribers) {
            subscriberMailDTOs.add(SubscriberMailDTO.buildSubscriberMailDTO(subscriber, feedMessageDTOs));
        }
        return subscriberMailDTOs;
    }

    private LinkedList<FeedMessageDTO> buildFeedMessages() {
        LinkedList<FeedMessageDTO> feedMessageDTOs = new LinkedList<>();
        for (FeedMessage feedMessage : feedMessageService.findWeeklyFeedMessages()) {
            feedMessageDTOs.add(feedMessageDTOConverter.convertFor(feedMessage));
        }
        return feedMessageDTOs;
    }
}
