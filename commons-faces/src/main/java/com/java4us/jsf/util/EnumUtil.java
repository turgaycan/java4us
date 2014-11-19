/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.jsf.util;

import com.java4us.commons.service.MessageResourceService;
import com.java4us.domain.common.enums.LabeledEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.Locale;

/**
 * @author turgay
 */
@ManagedBean
@ApplicationScoped
public class EnumUtil implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnumUtil.class);

    private static final String DB_ENUM_CATEGORY_NAME = "EnumLabels";

    @ManagedProperty(value = "#{messageResourceService}")
    private transient MessageResourceService messageResourceService;

    public String getEnumLabel(Enum<? extends LabeledEnum> value, Locale locale) {

        String messageKey = getEnumClassLabel(value);
        String messageFromDB = getStringFromDB(messageKey, locale);
        if (StringUtils.isEmpty(messageFromDB)) {
            throw new IllegalStateException();
        }
        return messageFromDB;
    }

    public String getEnumClassLabel(Enum<? extends LabeledEnum> value) {
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

    private String getStringFromDB(String messageKey, Locale locale) {

        if (StringUtils.isBlank(messageKey)) {
            LOGGER.error("couldn't find the messageKey name : {}", messageKey);
            return "???" + messageKey + "???";
        }
        String message = messageResourceService.getString(DB_ENUM_CATEGORY_NAME, messageKey, locale.getLanguage());

        if (StringUtils.isEmpty(message)) {
            LOGGER.info("couldn't find the value for {} in category {} ( From DB )", messageKey, DB_ENUM_CATEGORY_NAME);
        }

        return message;
    }

    public void setMessageResourceService(MessageResourceService messageResourceService) {
        this.messageResourceService = messageResourceService;
    }
}
