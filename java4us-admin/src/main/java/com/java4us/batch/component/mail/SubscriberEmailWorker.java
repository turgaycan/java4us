package com.java4us.batch.component.mail;

import com.java4us.amqp.mail.SubscriberMQMailProducer;
import com.java4us.batch.component.Worker;
import com.java4us.common.model.SubscriberMailDTO;
import com.java4us.commons.service.feed.FeedMessageService;
import com.java4us.commons.service.member.SubscriberService;
import com.java4us.domain.FeedMessage;
import com.java4us.domain.Subscriber;
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

    @Override
    public void work() {
        LinkedList<SubscriberMailDTO> subscriberMailDTOs = prepareSubsrcibersMails();
        for (SubscriberMailDTO subscriberMailDTO : subscriberMailDTOs){
            subscriberMQMailProducer.execute(subscriberMailDTO);
        }

    }

    private LinkedList<SubscriberMailDTO> prepareSubsrcibersMails() {
        List<Subscriber> subscribers = subscriberService.findAllowSubscriberList();
        LinkedList<FeedMessage> feedMessages = new LinkedList<>(feedMessageService.findWeeklyFeedMessages());
        LinkedList<SubscriberMailDTO> subscriberMailDTOs = new LinkedList<>();
        for(Subscriber subscriber : subscribers){
            subscriberMailDTOs.add(SubscriberMailDTO.buildSubscriberMailDTO(subscriber, feedMessages));
        }
        return subscriberMailDTOs;
    }
}
