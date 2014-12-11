package com.java4us.service.seo;

import com.java4us.commons.service.MessageResourceService;
import com.java4us.commons.service.url.UrlService;
import com.java4us.domain.FeedMessage;
import com.java4us.domain.builder.FeedMessageBuilder;
import com.java4us.domain.common.enums.Category;
import com.java4us.web.model.SeoMetaData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SeoMetaDataServiceTest {

    @InjectMocks
    private SeoMetaDataService service;

    @Mock
    private MessageResourceService messageResourceService;

    @Mock
    private UrlService urlService;

    @Test
    public void shouldReturnHomePageSeoMetaData(){
        when(messageResourceService.getString("Seo", "homepage.title", (Object[]) null)).thenReturn("title");
        when(messageResourceService.getString("Seo", "homepage.desc", (Object[]) null)).thenReturn("desc");
        when(urlService.getRootUrl()).thenReturn("http://www.java4us.net");

        SeoMetaData seoMetaData = service.prepareHomePage();

        assertThat(seoMetaData.getCanonical(), equalTo("http://www.java4us.net"));
        assertThat(seoMetaData.getDescription(), equalTo("desc"));
        assertThat(seoMetaData.getTitle(), equalTo("title"));
    }

    @Test
    public void shouldReturnRegisterPageSeoMetaData(){
        when(messageResourceService.getString("Seo", "feeder.registration.title", (Object[]) null)).thenReturn("title");
        when(messageResourceService.getString("Seo", "feeder.registration.desc", (Object[]) null)).thenReturn("desc");
        when(urlService.getRegisterUrl()).thenReturn("http://www.java4us.net/register");

        SeoMetaData seoMetaData = service.prepareRegisterPage();

        assertThat(seoMetaData.getCanonical(), equalTo("http://www.java4us.net/register"));
        assertThat(seoMetaData.getDescription(), equalTo("desc"));
        assertThat(seoMetaData.getTitle(), equalTo("title"));
    }

    @Test
    public void shouldReturnRSSDetailPageSeoMetaData(){
        FeedMessage feedMessage = new FeedMessageBuilder().title("java  syncronized -keyword").category(Category.JAVA).build();
        feedMessage.setId(123l);
        when(urlService.getRssDetailPageUrl(feedMessage.getTitle(), feedMessage.getId())).thenReturn("http://www.java4us.net/java-syncronized-keyword-r123");

        SeoMetaData seoMetaData = service.prepareRssDetailPage(feedMessage);

        assertThat(seoMetaData.getCanonical(), equalTo("http://www.java4us.net/java-syncronized-keyword-r123"));
        assertThat(seoMetaData.getDescription(), equalTo("java  syncronized -keyword, article"));
        assertThat(seoMetaData.getTitle(), equalTo("java  syncronized -keyword 123 - java4us.net"));
    }
}