/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 *
 * @author turgay
 */
@Service
public class JsonSerializer {

    private ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonSerializer.class);

    @SuppressWarnings("deprecation")
	@PostConstruct
    public void init() {
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true)
                .setVisibilityChecker(objectMapper.getSerializationConfig()
                        .getDefaultVisibilityChecker()
                        .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                        .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                        .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                        .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));

        objectMapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
    }

    public String toJson(Object objectToSerialize, String... ignore) {
        StringWriter writer = new StringWriter();
        this.writeJson(objectToSerialize, writer, ignore);
        return writer.toString();
    }

    public void writeJson(Object objectToSerialize, Writer writer, String... ignore) {
        try {
            objectMapper.writeValue(writer, objectToSerialize);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                writer.close();
                writer.flush();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

    }

    @SuppressWarnings("deprecation")
	public void prettyPrintWriteJson(Object objectToSerialize, Writer writer) {
        try {
            objectMapper.defaultPrettyPrintingWriter().writeValue(writer, objectToSerialize);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                writer.close();
                writer.flush();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

    }

    @SuppressWarnings("unchecked")
    public <T> T fromJson(String jsonString, Class<?> clazz) {
        try {
            return (T) objectMapper.readValue(jsonString, clazz);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public Map<String, String> fromJsonToMap(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public JsonNode toJsonNode(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, JsonNode.class);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

}
