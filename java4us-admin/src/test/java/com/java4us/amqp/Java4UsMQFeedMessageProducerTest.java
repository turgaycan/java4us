/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.amqp;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author turgay
 */
public class Java4UsMQFeedMessageProducerTest {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private final AtomicInteger counter = new AtomicInteger();

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
                "/rabbitmq-context-test.xml");
    }

    @Scheduled(fixedRate = 1000)
    public void execute() {
        System.out.println("execute...");
        amqpTemplate.convertAndSend("java4us.routingkey.feedMessage", "hello " + counter.incrementAndGet());
    }
}
