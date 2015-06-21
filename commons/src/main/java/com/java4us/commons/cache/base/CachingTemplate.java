package com.java4us.commons.cache.base;

import com.java4us.commons.cache.CacheRegistryProcessor;
import com.java4us.commons.cache.CacheService;

import java.io.Serializable;

/**
 * Created by turgaycan on 6/20/15.
 */
public abstract class CachingTemplate<T, F> extends CachingBaseTemplate {

    public CachingTemplate(CacheService cacheService, CacheRegistryProcessor cacheRegistryProcessor, int cacheTtl, boolean byPassCache) {
        super(cacheService, cacheRegistryProcessor, cacheTtl, byPassCache);
    }

    public CachingTemplate(CacheService cacheService, int cacheTtl) {
        super(cacheService, cacheTtl);
    }

    protected void apply(Object entity) {
    }

    protected abstract T query(F queryParam);

    public <T extends Serializable> T findBy(F queryParam, String cacheKey) {

        if (!isByPassCache()) {
            T fromCache = (T) findFromCache(cacheKey);

            if (fromCache != null) {
                apply(fromCache);
                return fromCache;
            }
        }

        T entity = (T) query(queryParam);
        register(cacheKey, entity);
        apply(entity);
        return entity;
    }

    public <T extends Serializable> T findByIdWithDirectCacheKey(String cacheKey) {
        setDirectCache(true);
        return findBy(null, cacheKey);
    }

}