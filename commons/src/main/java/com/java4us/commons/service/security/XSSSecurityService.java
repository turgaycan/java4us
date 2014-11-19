package com.java4us.commons.service.security;

import com.java4us.commons.utils.security.XSSCleaner;
import com.java4us.domain.FeedMessage;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class XSSSecurityService {

	private static final String TAGS = "<.+?>";

	private static final String ANCHOR_TAG = "a[href]";
	private static final String ANCHOR_HREF = "href";
	private static final String YOUTUBE_URL = "youtube.com";
	private static final String VIMEO_URL = "vimeo.com";
	private static final String VIKI_URL = "wikipedia.org";
	private static final String URL_TARGET = "target";
	private static final String URL_TARGET_VAL = "_blank";
	private static final String HREF = "href";
	private static final String HTTP = "http://";

	public void cleanFeedMessageForXSS(FeedMessage feedMessage) {
		feedMessage
				.setDescription(cleanTextForXSS(feedMessage.getDescription()));
	}

	public String cleanTextForXSS(String text) {
		text = cleanText(text);
		if (StringUtils.isNotEmpty(text)) {
			Document document = clearAndFormatLinks(text);
			return document.body().html();
		} else {
			return text;
		}
	}

	private Document clearAndFormatLinks(String prodDesc) {
		Document document = Jsoup.parse(prodDesc);
		Elements links = document.select(ANCHOR_TAG);
		for (Element link : links) {
			String linkUrl = link.attr(ANCHOR_HREF);
			if (StringUtils.isNotEmpty(linkUrl)) {
				if (!checkUrlHaveProtocol(linkUrl)) {
					addHttpToUrls(link, linkUrl);
				}
				if (checkUrlsTargetable(linkUrl)) {
					link.attr(URL_TARGET, URL_TARGET_VAL);
				}
			}
		}
		return document;
	}

	public String clearAllTags(String text) {
		if (StringUtils.isBlank(text)) {
			return "";
		}
		return text.replaceAll(TAGS, "");
	}

	private boolean checkUrlHaveProtocol(String linkUrl) {
		return linkUrl.startsWith(HTTP);
	}

	private boolean checkUrlsTargetable(String linkUrl) {
		return linkUrl.contains(YOUTUBE_URL) || linkUrl.contains(VIKI_URL)
				|| linkUrl.contains(VIMEO_URL);
	}

	private void addHttpToUrls(Element link, String linkUrl) {
		linkUrl = HTTP + linkUrl;
		link.attr(HREF, linkUrl);
	}

	private String cleanText(String text) {
		return getCleaner().cleanText(text);
	}

	private XSSCleaner getCleaner() {
		return XSSCleaner.getInstance();
	}

}
