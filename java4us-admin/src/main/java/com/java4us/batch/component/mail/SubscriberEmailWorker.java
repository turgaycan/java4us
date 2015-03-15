package com.java4us.batch.component.mail;

import com.java4us.amqp.mail.SubscriberMQMailProducer;
import com.java4us.batch.component.Worker;
import com.java4us.commons.service.feed.FeedMessageDTOConverter;
import com.java4us.commons.service.feed.FeedMessageService;
import com.java4us.commons.service.member.SubscriberService;
import com.java4us.domain.Subscriber;
import com.java4us.domain.model.FeedMessageDTO;
import com.java4us.domain.model.SubscriberMailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by turgaycan on 12/12/14.
 */
public class SubscriberEmailWorker implements Worker {

    private static Logger LOGGER = LoggerFactory.getLogger(SubscriberEmailWorker.class);

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
        subscriberMailDTOs.forEach(subscriberMQMailProducer::execute);
    }

    private LinkedList<SubscriberMailDTO> prepareSubsrcibersMails() {
        List<Subscriber> subscribers = subscriberService.findAllowSubscriberList();
        LinkedList<FeedMessageDTO> feedMessageDTOs = buildFeedMessages();
        return subscribers.stream().map(subscriber -> SubscriberMailDTO.buildSubscriberMailDTO(subscriber, feedMessageDTOs)).collect(Collectors.toCollection(() -> new LinkedList<>()));
    }

    private LinkedList<FeedMessageDTO> buildFeedMessages() {
        return feedMessageService.findWeeklyFeedMessages().stream().map(feedMessageDTOConverter::convertFor).collect(Collectors.toCollection(() -> new LinkedList<>()));
    }
}
