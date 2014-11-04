/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service.config;

import com.java4us.commons.dao.ConfigurationDao;
import com.java4us.commons.enums.CacheTypes;
import com.java4us.commons.service.JsonSerializer;
import com.java4us.commons.service.impl.ClearCacheListener;
import com.java4us.domain.Configuration;
import com.java4us.domain.Configuration.ConfigCategory;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 *
 * @author turgay
 */
@Service
public class ConfigurationService implements ClearCacheListener, Serializable {

	private static final long serialVersionUID = 2017733731604522258L;
	private static final String VALUE_SEPARATOR = ";";
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationService.class);

    @Autowired
    private ConfigurationDao configurationDao;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private JsonSerializer jsonSerializer;

    private Map<Configuration.ConfigCategory, Map<String, String>> configMap = new HashMap<Configuration.ConfigCategory, Map<String, String>>();

    @PostConstruct
    public void init() {
        prepareConfigMap(configMap);
    }

    public String getValueAsString(ConfigCategory configCategory, String key) {

        Map<String, String> valueMap = configMap.get(configCategory);
        if (valueMap == null) {
            LOGGER.error("Unknown configCategory " + configCategory + " or null values for " + configCategory);
            return null;
        }
        return valueMap.get(key);
    }

    public Date getValueAsDate(ConfigCategory configCategory, String key) {
        String value = this.getValueAsString(configCategory, key);
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(value);
        } catch (ParseException ex) {
            LOGGER.error("Parsing error: Could not get value as date from config");
        }
        return null;
    }

    public Date getValueAsDateTime(ConfigCategory configCategory, String key) {
        String value = this.getValueAsString(configCategory, key);
        try {
            return new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(value);
        } catch (ParseException ex) {
            LOGGER.error("Parsing error: Could not get value as date time from config");
        }
        return null;
    }

    public Date getValueAsDateTimeWithMilliseconds(ConfigCategory configCategory, String key) {
        String value = this.getValueAsString(configCategory, key);
        try {
            return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.S").parse(value);
        } catch (ParseException ex) {
            LOGGER.error("Parsing error: Could not get value as date time with milliseconds from config");
        }
        return null;
    }

    public List<String> getValueAsList(ConfigCategory configCategory, String key) {
        List<String> result = new ArrayList<String>();
        String valueAsString = getValueAsString(configCategory, key);
        if (StringUtils.isNotEmpty(valueAsString)) {
            for (String value : valueAsString.split(VALUE_SEPARATOR)) {
                value = value.trim();
                if (StringUtils.isNotEmpty(value)) {
                    result.add(value);
                }
            }
        }
        return result;
    }

    public List<Long> getValueAsLongList(ConfigCategory configCategory, String key) {
        List<Long> longList = new ArrayList<Long>();

        List<String> valueAsList = getValueAsList(configCategory, key);
        for (String item : valueAsList) {
            try {
                longList.add(Long.parseLong(item));
            } catch (NumberFormatException e) {
                continue;
            }
        }

        return longList;
    }

    public List<Integer> getValueAsIntegerList(ConfigCategory configCategory, String key) {
        List<Integer> integerList = new ArrayList<Integer>();

        List<String> valueAsList = getValueAsList(configCategory, key);
        for (String item : valueAsList) {
            try {
                integerList.add(Integer.parseInt(item));
            } catch (NumberFormatException e) {
                continue;
            }
        }

        return integerList;
    }

    public JsonNode getValueAsJson(ConfigCategory configCategory, String key) {
        String value = this.getValueAsString(configCategory, key);
        try {
            return jsonSerializer.toJsonNode(value);
        } catch (Exception e) {
            LOGGER.error("JSON parsing error", e);
        }
        return null;
    }

    private void prepareConfigMap(final Map<ConfigCategory, Map<String, String>> configMap) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.execute(new TransactionCallback<Object>() {

            @Override
            public Object doInTransaction(TransactionStatus ts) {
                List<Configuration> configurations = configurationDao.findAll();
                for (Configuration configuration : configurations) {
                    Map<String, String> valueMap = configMap.get(configuration.getCategory());
                    if (valueMap == null) {
                        valueMap = new HashMap<String, String>();
                        configMap.put(configuration.getCategory(), valueMap);
                    }
                    valueMap.put(configuration.getKey(), configuration.getValue());
                }
                return null;
            }
        });

    }

    public void setConfigurationDao(ConfigurationDao configurationDao) {
        this.configurationDao = configurationDao;
    }

    public void setTransactionManager(PlatformTransactionManager platformTransactionManager) {
        transactionManager = platformTransactionManager;
    }

    @Override
    public void refresh(CacheTypes cacheType) {
        if (CacheTypes.CONFIGURATION.equals(cacheType)) {
            HashMap<ConfigCategory, Map<String, String>> newConfigMap = new HashMap<ConfigCategory, Map<String, String>>();
            prepareConfigMap(newConfigMap);
            this.configMap = newConfigMap;
        }

    }

    public int getValueAsInteger(ConfigCategory configCategory, String key) {
        String value = this.getValueAsString(configCategory, key);
        return Integer.parseInt(value);
    }

    public BigDecimal getValueAsBigDecimal(ConfigCategory configCategory, String key) {
        String value = this.getValueAsString(configCategory, key);
        return new BigDecimal(value);
    }

    public Long getValueAsLong(ConfigCategory configCategory, String key) {
        String value = this.getValueAsString(configCategory, key);
        return Long.valueOf(value);
    }

    public Boolean getValueAsBoolean(ConfigCategory configCategory, String key) {
        String value = this.getValueAsString(configCategory, key);
        return Boolean.parseBoolean(value);
    }

    public Double getValueAsDouble(ConfigCategory configCategory, String key) {
        String value = this.getValueAsString(configCategory, key);
        return Double.parseDouble(value);
    }

}
