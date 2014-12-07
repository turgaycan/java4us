/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.cache;

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.protocol.views.View;
import com.couchbase.client.vbucket.config.Bucket;
import net.spy.memcached.internal.CheckedOperationTimeoutException;
import net.spy.memcached.internal.OperationFuture;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author turgay
 */
public class CacheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheService.class);

    private static final int MAXKEYSIZE = 250;

    public static final int ONE_MINUTE = 60;

    public static final int ONE_HOUR = 60 * 60;

    public static final int SIX_HOURS = ONE_HOUR * 6;

    public static final int TWELVEHOURS = ONE_HOUR * 12;

    public static final int ONE_DAY = 24 * 60 * 60;

    public static final int ONE_WEEK = ONE_DAY * 7;

    public static final int ONE_MONTH = ONE_DAY * 30;

    private CouchbaseClient couchbaseClient;

    private Bucket bucket;

    private View java4UsView;

    @SuppressWarnings("unchecked")
    public <T extends Serializable> T get(String key) throws Exception {
        T t;
        try {
            if (StringUtils.isNotBlank(key) && key.length() > MAXKEYSIZE) {
                return null;
            }
            t = (T) couchbaseClient.get(key);
        } catch (Exception e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            if (rootCause instanceof CheckedOperationTimeoutException) {
                t = (T) couchbaseClient.get(key);
            } else {
                throw new Exception(e);
            }
        }
        return t;
    }

    public <T extends Serializable> T getNullIfException(String key) {
        try {
            return get(key);
        } catch (Exception ex) {
            LOGGER.error("Get from cache throws exception, null returned.", ex);
            return null;
        }
    }

    public <T extends Serializable> void put(String key, T value, int ttlSeconds) {
        couchbaseClient.set(key, ttlSeconds, value);
    }

    public OperationFuture<Boolean> add(String key, Object value, int ttlSeconds) {
        return couchbaseClient.add(key, ttlSeconds, value);
    }

    public <T extends Serializable> void replace(String key, T value, int ttlSeconds) {
        couchbaseClient.replace(key, ttlSeconds, value);
    }

    public <T extends Serializable> void putForOneMonth(String key, T value) {
        couchbaseClient.set(key, ONE_MONTH, value);
    }

    public long increment(String key, int by, int def, int ttlSeconds) {
        return couchbaseClient.incr(key, by, def, ttlSeconds);
    }

    public OperationFuture<Boolean> remove(String key) {
        return couchbaseClient.delete(key);
    }

    public void asyncIncr(String cacheKey) {
        couchbaseClient.asyncIncr(cacheKey, 1);
    }

    public void decrementCounter(String cacheKey, int by) {
        couchbaseClient.decr(cacheKey, by);
    }

    public OperationFuture<Boolean> flushCache() {
        LOGGER.debug("Flushing all the cache !");
        return couchbaseClient.flush();
    }

    public CouchbaseClient getCouchbaseClient() {
        return couchbaseClient;
    }

    public void setCouchbaseClient(CouchbaseClient couchbaseClient) {
        this.couchbaseClient = couchbaseClient;
    }

    public View getJava4UsView() {
        return java4UsView = getCouchbaseClient().getView("java4us", "java4us");
    }

    public void setJava4UsView(View java4UsView) {
        this.java4UsView = java4UsView;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }
}
