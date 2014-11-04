//package com.java4us.commons.cache;
//
//import java.io.Serializable;
//
//public class CahceRegistryEntry {
//
//	private String key;
//	private Serializable dataToCache;
//	private int ttl;
//
//	public static CahceRegistryEntry newEntry(String key,
//			Serializable dataToCache, int ttl) {
//		CahceRegistryEntry registryEntry = new CahceRegistryEntry();
//		registryEntry.key = key;
//		registryEntry.dataToCache = dataToCache;
//		registryEntry.ttl = ttl;
//		return registryEntry;
//	}
//
//	public String getKey() {
//		return key;
//	}
//
//	public Serializable getDataToCache() {
//		return dataToCache;
//	}
//
//	public int getTtl() {
//		return ttl;
//	}
//
//}
