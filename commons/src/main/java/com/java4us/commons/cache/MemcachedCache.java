/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.cache;

import java.util.logging.Level;
import net.spy.memcached.internal.OperationFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

/**
 *
 * @author turgay
 */
public class MemcachedCache implements Cache {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemcachedCache.class);

    private final String name;
    private CacheService cacheService;
    private int expiry = 60 * 60;

    public MemcachedCache(String name, CacheService cacheService, int expiry) {
        this.name = name;
        this.cacheService = cacheService;
        this.expiry = expiry;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return cacheService.getCouchbaseClient();
    }

    @Override
    public ValueWrapper get(Object key) {
        try {
            Object value = cacheService.get(keyToString(key));
            if (value == null) {
                return null;
            }
            return new SimpleValueWrapper(value);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MemcachedCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        try {
            OperationFuture<Boolean> operationFuture = cacheService.add(keyToString(key), value, expiry);
            try {
                operationFuture.get();
            } catch (Exception e) {
                LOGGER.warn("Exception occured at operationFuture get()", e);
                operationFuture.cancel();
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MemcachedCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void evict(Object key) {
        try {
            OperationFuture<Boolean> operationFuture = cacheService.remove(keyToString(key));
            try {
                operationFuture.get();
            } catch (Exception e) {
                LOGGER.warn("Exception occured at operationFuture get()", e);
                operationFuture.cancel();
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MemcachedCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void clear() {
        try {
            throw new Exception("Memcached Does not support full cache clear use evict with key");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MemcachedCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String keyToString(Object key) throws Exception {
        if (key == null) {
            throw new Exception("Cache Key cannot be blank!");
        }
        return String.valueOf(key.hashCode());

    }

    public CacheService getCacheService() {
        return cacheService;
    }

    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public int getExpiry() {
        return expiry;
    }

    public void setExpiry(int expiry) {
        this.expiry = expiry;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
