/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.web.controller.util;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author turgay
 */
public final class Java4UsUtils {
	private static final String HASHSTRING = "dox0WX1efyzq3ABCDaGHI4bcRS5prstu6NOP7VYZ8n2vTEF9JKLMhijklmQU";
	private static Random random = new Random();

	private Java4UsUtils() {

	}

	public static String getNullCheckTrim(String email) {
		return (StringUtils.isNotEmpty(email)) ? email.trim() : email;
	}

	public static String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(HASHSTRING.charAt(random.nextInt(HASHSTRING.length())));
		return sb.toString();
	}
}
