package com.java4us.commons.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

public class CacheKeyGenerator implements KeyGenerator {

	private static final String NULL_KEY = "NULLKEY";

	@Override
	public Object generate(Object target, Method method, Object... params) {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(target.getClass().getName().toString());
		stringBuilder.append(method.getName());
		if (params != null) {
			for (Object eachParam : params) {
				if (eachParam != null
						&& StringUtils.isNotBlank(eachParam.toString())) {
					stringBuilder.append(eachParam.toString());
				} else {
					stringBuilder.append(NULL_KEY);
				}
			}
		}
		return stringBuilder.toString().hashCode();

	}

}
