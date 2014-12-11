package com.java4us.service.seo;

import com.java4us.commons.service.MessageResourceService;
import com.java4us.commons.service.url.UrlService;
import com.java4us.domain.FeedMessage;
import com.java4us.web.model.SeoMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeoMetaDataService {

    private static final String SEO = "Seo";
    private static final String DOMAIN = "java4us.net";
    private static final String RSSDETAIL = "article";

    @Autowired
    private MessageResourceService messageResourceService;

    @Autowired
    private UrlService urlService;

    public SeoMetaData prepareHomePage(){
        SeoMetaData seoMetaData = SeoMetaData.getInstance();
        seoMetaData.setTitle(getString("homepage.title", null));
        seoMetaData.setDescription(getString("homepage.desc", null));
        seoMetaData.setCanonical(urlService.getRootUrl());
        return seoMetaData;
    }

    public SeoMetaData prepareRegisterPage() {
        SeoMetaData seoMetaData = SeoMetaData.getInstance();
        seoMetaData.setTitle(getString("feeder.registration.title", null));
        seoMetaData.setDescription(getString("feeder.registration.desc", null));
        seoMetaData.setCanonical(urlService.getRegisterUrl());
        return seoMetaData;
    }

    public SeoMetaData prepareRssDetailPage(FeedMessage feedMessage) {
        SeoMetaData seoMetaData = SeoMetaData.getInstance();
        seoMetaData.setTitle(feedMessage.getTitle() + " " + feedMessage.getId() + " - " + DOMAIN);
        seoMetaData.setDescription(feedMessage.getTitle() + ", " + RSSDETAIL);
        seoMetaData.setCanonical(urlService.getRssDetailPageUrl(feedMessage.getTitle(), feedMessage.getId()));
        return seoMetaData;
    }


    public String getString(String key, Object... params) {
        return messageResourceService.getString(SEO, key, params);
    }
}
