package com.java4us.amqp.mail;

import com.java4us.common.model.SubscriberMailDTO;
import com.java4us.domain.Subscriber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.LinkedList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SubscriberMQMailProducerTest {

    @InjectMocks
    private SubscriberMQMailProducer producer;

    @Mock
    private AmqpTemplate amqpTemplate;


    @Test
    public void shouldSendMessageToQueue(){
        SubscriberMailDTO subscriberMailDTO = SubscriberMailDTO.buildSubscriberMailDTO(new Subscriber(), new LinkedList<>());

        producer.execute(subscriberMailDTO);

        verify(amqpTemplate, times(1)).convertAndSend("java4us.routingkey.subscriber.mail", subscriberMailDTO);
    }
}