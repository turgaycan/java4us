/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.domain.builder;

import com.java4us.domain.Subscriber;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author turgay
 */
public class SubscriberBuilder extends BaseBuilder<Subscriber, SubscriberBuilder> {

    private Long id;
    private String name = RandomStringUtils.random(10);
    private String surname = RandomStringUtils.random(10);
    private String email = RandomStringUtils.random(10);

    @Override
    protected Subscriber doBuild() {
        Subscriber subscriber = new Subscriber();
        subscriber.setId(id);
        subscriber.setName(name);
        subscriber.setSurname(surname);
        subscriber.setEmail(email);
        return subscriber;
    }

    @Override
    public SubscriberBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public SubscriberBuilder name(String name) {
        this.name = name;
        return this;
    }

    public SubscriberBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public SubscriberBuilder email(String email) {
        this.email = email;
        return this;
    }

}
