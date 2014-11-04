///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.java4us.amqp;
//
//import java.util.Date;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//
///**
// *
// * @author turgay
// */
//@ContextConfiguration({"/rabbitmq-consumer-test.xml"})
//public class Java4UsMQFeedMessageConsumerTest {
//
//    public static void main(String[] args) throws Exception {
//        ApplicationContext context = new ClassPathXmlApplicationContext("rabbitmq-consumer-test.xml");//loading beans
//        AmqpTemplate aTemplate = (AmqpTemplate) context.getBean("amqpTemplate");// getting a reference to the sender bean
//        for (int i = 0; i < 10000; i++) {
//            aTemplate.convertAndSend("java4us.routingkey.feedMessage", "Message # " + i + " on " + new Date());// send
//        }
//    }
//}
