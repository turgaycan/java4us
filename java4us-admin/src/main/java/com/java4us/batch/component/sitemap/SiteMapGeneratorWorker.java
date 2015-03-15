package com.java4us.batch.component.sitemap;

import com.java4us.batch.component.Worker;
import com.java4us.commons.service.config.ConfigurationService;
import com.java4us.commons.service.feed.FeedMessageService;
import com.java4us.commons.service.file.FileService;
import com.java4us.domain.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by turgaycan on 12/12/14.
 */
public class SiteMapGeneratorWorker implements Worker {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteMapGeneratorWorker.class);

    private static final String HEAD_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n";
    private static final String CONTENT_TEMPLATE_START = "<url>\n<loc>";
    private static final String CONTENT_TEMPLATE_END = "</loc>\n</url>\n";
    private static final String END_XML = "</urlset>";

    @Autowired
    private FeedMessageService feedMessageService;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private FileService fileService;

    @Override
    public void work() {
        String filePath = configurationService.getValueAsString(Configuration.ConfigCategory.Batch, "sitem.map.path");
        if(StringUtils.isBlank(filePath)){
            LOGGER.error("File path null..");
            return;
        }

        final List<String> proceedMessagesLinks = feedMessageService.findAllProceedMessagesLinks();
        final String xmlFileAsString = buildSiteMapXmlTemplate(proceedMessagesLinks);
        fileService.write(xmlFileAsString, filePath);

    }

    private String buildSiteMapXmlTemplate(List<String> proceedMessagesLinks){
        StringBuilder siteMapAsString = new StringBuilder(HEAD_XML);
        proceedMessagesLinks.forEach(each -> siteMapAsString.append(CONTENT_TEMPLATE_START + each + END_XML));
        siteMapAsString.append(END_XML);
        return siteMapAsString.toString();
    }


}
