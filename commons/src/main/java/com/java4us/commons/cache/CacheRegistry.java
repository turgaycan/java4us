package com.java4us.commons.cache;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CacheRegistry {

	private List<CahceRegistryEntry> allEntries = new LinkedList<>();

	public void register(String cacheKey, Serializable data, int ttl) {
		CahceRegistryEntry cahceRegistryEntry = CahceRegistryEntry.newEntry(
				cacheKey, data, ttl);
		allEntries.add(cahceRegistryEntry);
	}

	public List<CahceRegistryEntry> getAllEntries() {
		return Collections.unmodifiableList(allEntries);
	}

	public void clearRegistry() {
		allEntries.clear();
	}
}
