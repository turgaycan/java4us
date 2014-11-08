package com.java4us.commons.utils.security;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class XSSCleaner {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(XSSCleaner.class);

	public static final int MAX_BYTE_SIZE = 2000000;
	private static XSSCleaner instance = new XSSCleaner();

	private AntiSamy antiSamyCleaner;
	private Policy ebayPolicy;

	private XSSCleaner() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		try {
			ebayPolicy = Policy.getInstance(loader
					.getResourceAsStream("security/antisamy-1.4.xml"));
			antiSamyCleaner = new AntiSamy(ebayPolicy);
		} catch (PolicyException e) {
			throw new RuntimeException("Cannot create antisamy !!!");
		}
	}

	public static XSSCleaner getInstance() {
		return instance;
	}

	public String cleanText(String text) {
		return getCleanedTextHolder(text).getCleanedText();
	}

	public XSSCleanedTextHolder getCleanedTextHolder(String text) {
		if (StringUtils.isBlank(text)) {
			return new XSSCleanedTextHolder(null, text);
		}

		if (exceedsMaximumByteSize(text)) {
			throw new RuntimeException("Input size cannot exceed "
					+ MAX_BYTE_SIZE + " bytes");
		}

		try {
			return getXssCleanedTextHolder(text);
		} catch (ScanException e) {
			LOGGER.info("Cannot clean text for XSS Attack : " + text);
			throw new RuntimeException("Cannot escape xss for : " + text, e);
		} catch (PolicyException e) {
			throw new RuntimeException("Cannot create antisamy !!!", e);
		}
	}

	private XSSCleanedTextHolder getXssCleanedTextHolder(String text)
			throws ScanException, PolicyException {
		CleanResults cleanResults = antiSamyCleaner.scan(text, ebayPolicy);
		String cleanText = unescapeSpecialCharacters(cleanResults
				.getCleanHTML());
		return new XSSCleanedTextHolder(cleanResults.getErrorMessages(),
				cleanText);
	}

	public boolean exceedsMaximumByteSize(String text) {
		if (StringUtils.isEmpty(text)) {
			return false;
		}

		try {
			byte[] data = text.getBytes("UTF-8");
			return data.length > MAX_BYTE_SIZE;
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Unsupported encoding used in XssCleaner!", e);
		}

		return false;
	}

	private String unescapeSpecialCharacters(String html) {
		String replaceText = html.replace("&amp;", "&");
		replaceText = replaceText.replace("&quot;", "\"");
		replaceText = replaceText.replace("&gt;", "");
		replaceText = replaceText.replace("&lt;", "");
		return replaceText;
	}

}
