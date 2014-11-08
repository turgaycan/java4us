package com.java4us.commons.cache;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CacheRegistryProcessor {

	@Autowired
	@Qualifier("cacheService")
	private CacheService cacheService;

	@Autowired
	private CacheRegistry cacheRegistry;

	public void register(String cacheKey, Serializable data, int ttlInSeconds) {
		cacheRegistry.register(cacheKey, data, ttlInSeconds);
	}

	public void slufhToCache() {
		for (CahceRegistryEntry entry : cacheRegistry.getAllEntries()) {
			if (entry.getDataToCache() != null) {
				cacheService.put(entry.getKey(), entry.getDataToCache(),
						entry.getTtl());
			}
		}
		clearRegistry();
	}

	private void clearRegistry() {
		cacheRegistry.clearRegistry();
	}

}
