package com.java4us.commons.cache.base;

import com.java4us.commons.cache.CacheRegistryProcessor;
import com.java4us.commons.cache.CacheService;

import java.io.Serializable;

/**
 * Created by turgaycan on 6/20/15.
 */
public abstract class CachingBaseTemplate {

    private final int cacheTtl;
    private CacheService cacheService;
    private CacheRegistryProcessor cacheRegistryProcessor;
    private boolean byPassCache = false;
    private boolean directCache = false;

    public CachingBaseTemplate(CacheService cacheService, CacheRegistryProcessor cacheRegistryProcessor, int cacheTtl, boolean byPassCache) {
        this.cacheService = cacheService;
        this.cacheRegistryProcessor = cacheRegistryProcessor;
        this.cacheTtl = cacheTtl;
        this.byPassCache = byPassCache;
    }

    public CachingBaseTemplate(CacheService cacheService, int cacheTtl) {
        this.cacheService = cacheService;
        this.cacheTtl = cacheTtl;
    }

    public void register(String cacheKey, Serializable entity) {
        if(entity == null){
            return;
        }
        if (!isByPassCache()) {
            if (directCache) {
                cacheService.put(cacheKey, entity, cacheTtl);
            } else {
                getCacheRegistryProcessor().register(cacheKey, entity, getCacheTtl());
            }
        }
    }

    public Serializable findFromCache(String cacheKey){
        return getCacheService().get(cacheKey);
    }

    public int getCacheTtl() {
        return cacheTtl;
    }

    public CacheService getCacheService() {
        return cacheService;
    }

    public CacheRegistryProcessor getCacheRegistryProcessor() {
        return cacheRegistryProcessor;
    }

    public boolean isByPassCache() {
        return byPassCache;
    }

    public void setDirectCache(boolean directCache) {
        this.directCache = directCache;
    }
}
