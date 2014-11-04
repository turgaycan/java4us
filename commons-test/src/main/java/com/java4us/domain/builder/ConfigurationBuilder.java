/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.domain.builder;

import com.java4us.domain.Configuration;
import com.java4us.domain.Configuration.ConfigCategory;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author turgay
 */
public class ConfigurationBuilder extends BaseBuilder<Configuration, ConfigurationBuilder> {

    private Long id;
    private ConfigCategory category;
    private String key = RandomStringUtils.random(10);
    private String value = RandomStringUtils.random(10);

    @Override
    protected Configuration doBuild() {
        Configuration configuration = new Configuration();
        configuration.setId(id);
        configuration.setCategory(category);
        configuration.setKey(key);
        configuration.setValue(value);
        return configuration;
    }

    @Override
    public ConfigurationBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ConfigurationBuilder category(ConfigCategory category) {
        this.category = category;
        return this;
    }

    public ConfigurationBuilder key(String key) {
        this.key = key;
        return this;
    }

    public ConfigurationBuilder value(String value) {
        this.value = value;
        return this;
    }

}
