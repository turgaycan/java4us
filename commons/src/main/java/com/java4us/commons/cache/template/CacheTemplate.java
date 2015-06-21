package com.java4us.commons.cache.template;

import com.java4us.commons.cache.CacheRegistryProcessor;
import com.java4us.commons.cache.CacheService;
import com.java4us.commons.cache.base.CachingTemplate;

import java.io.Serializable;

/**
 * Created by turgaycan on 6/20/15.
 */
public abstract class CacheTemplate<T> extends CachingTemplate<T, Long> {

    public CacheTemplate(CacheService cacheService, CacheRegistryProcessor cacheRegistryProcessor, int cacheTtl, boolean byPassCache) {
        super(cacheService, cacheRegistryProcessor, cacheTtl, byPassCache);
    }

    public CacheTemplate(CacheService cacheService, int cacheTtl) {
        super(cacheService, cacheTtl);
    }

    public <T extends Serializable> T findById(Long entityId, String cacheKey) {
        return findBy(entityId, cacheKey);
    }


    public <T extends Serializable> T findByIdWithDirectCache(Long entityId, String cacheKey) {
        setDirectCache(true);
        return findBy(entityId, cacheKey);
    }

}
