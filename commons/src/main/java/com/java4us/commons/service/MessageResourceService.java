/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service;

import com.java4us.commons.dao.MessageResourceDao;
import com.java4us.commons.enums.CacheTypes;
import com.java4us.commons.service.impl.ClearCacheListener;
import com.java4us.domain.MessageResource;
import com.java4us.domain.common.enums.LabeledEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author turgay
 */

@Service
public class MessageResourceService implements ClearCacheListener {

    private static final String EN = "EN";
    private static final String TR = "TR";

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private MessageResourceDao resourceDao;

    private Map<String, Map<String, MessageResource>> messageResourceMap = new HashMap<String, Map<String, MessageResource>>();

    @PostConstruct
    public void init() {
        prepareMessageResourceMap(messageResourceMap);
    }

    private void prepareMessageResourceMap(final Map<String, Map<String, MessageResource>> messageResourceMap) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.execute(new TransactionCallback<Object>() {

            @Override
            public Object doInTransaction(TransactionStatus status) {
                List<MessageResource> allMessageResources = resourceDao.findAll();
                for (MessageResource eachResource : allMessageResources) {
                    Map<String, MessageResource> resourceMap = messageResourceMap.get(eachResource.getCategory());
                    if (resourceMap == null) {
                        resourceMap = new HashMap<String, MessageResource>();
                        messageResourceMap.put(eachResource.getCategory(), resourceMap);
                    }
                    resourceMap.put(eachResource.getKey(), eachResource);
                }
                return null;
            }
        });

    }

    public MessageResource getUniqueResource(String category, String key) {
        Map<String, MessageResource> resourceMap = messageResourceMap.get(category);
        if (resourceMap == null) {
            return null;
        }

        return resourceMap.get(key);
    }

    public String getString(String category, String key, String lang) {
        if (lang == null) {
            return null;
        }

        MessageResource r = getUniqueResource(category, key);
        if (r != null) {
            if (lang.equalsIgnoreCase(TR)) {
                return r.getTrValue();
            } else if (lang.equalsIgnoreCase(EN)) {
                return r.getEnValue();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public String getString(String category, String key) {
        return getString(category, key, EN);
    }

    public String getEnumLabel(String key) {
        return getString("EnumLabels", key, TR);
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public String getString(String category, String key, Object... args) {
        String format = this.getString(category, key);
        return MessageFormat.format(format, args);
    }

    public String getString(String combinedKey, Object... args) {
        String category = StringUtils.split(combinedKey, '.')[0];
        String key = StringUtils.split(combinedKey, '.')[1];

        String format = this.getString(category, key);
        return MessageFormat.format(format, args);
    }

    public void setResourceDao(MessageResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

    @Override
    public void refresh(CacheTypes cacheType) {
        if (CacheTypes.MESSAGE_RESOURCE.equals(cacheType)) {
            HashMap<String, Map<String, MessageResource>> newMessageResourceMap = new HashMap<String, Map<String, MessageResource>>();
            prepareMessageResourceMap(newMessageResourceMap);
            this.messageResourceMap = newMessageResourceMap;
        }
    }

    public String getEnumLabel(Enum<? extends LabeledEnum> labeledEnum) {
        String key = getEnumClassLabel(labeledEnum);
        return getString("EnumLabels", key, TR);
    }

    private String getEnumClassLabel(Enum<? extends LabeledEnum> value) {
        String className = getClassName(value);
        return className + "." + value.name();
    }

    private String getClassName(Enum<? extends LabeledEnum> value) {
        String className = value.getClass().getName();
        if (className.contains("$")) {
            className = className.substring(0, className.indexOf('$'));
        }
        return className;
    }

}
