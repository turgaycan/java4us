/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.domain.builder;

import com.java4us.domain.MessageResource;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author turgay
 */
public class MessageResourceBuilder extends BaseBuilder<MessageResource, MessageResourceBuilder> {

    private Long id;
    private String category = RandomStringUtils.random(5);
    private String key = RandomStringUtils.random(5);
    private String trValue = RandomStringUtils.random(5);
    private String enValue = RandomStringUtils.random(5);

    @Override
    protected MessageResource doBuild() {
        MessageResource messageResource = new MessageResource();
        messageResource.setId(id);
        messageResource.setCategory(category);
        messageResource.setKey(key);
        messageResource.setTrValue(trValue);
        messageResource.setEnValue(enValue);
        return messageResource;
    }

    @Override
    public MessageResourceBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public MessageResourceBuilder category(String category) {
        this.category = category;
        return this;
    }

    public MessageResourceBuilder key(String key) {
        this.key = key;
        return this;
    }

    public MessageResourceBuilder trValue(String trValue) {
        this.trValue = trValue;
        return this;
    }

    public MessageResourceBuilder enValue(String enValue) {
        this.enValue = enValue;
        return this;
    }

}
