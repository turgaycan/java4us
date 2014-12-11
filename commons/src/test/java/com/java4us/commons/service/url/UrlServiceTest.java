/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.java4us.commons.service.url;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 *
 * @author turgay
 */
@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {
    
    @InjectMocks
    private UrlService service;
    
    @Test
    public void shouldReturnPaginationUrl(){
       String pageUrl =  service.getPaginationUrl("java", 1);
       assertThat(pageUrl, equalTo("http://www.java4us.net/java/1"));
    }

    @Test
    public void shouldReturnRssDetailUrl(){
        String pageUrl =  service.getRssDetailPageUrl("java", 1L);
        assertThat(pageUrl, equalTo("http://www.java4us.net/java-r1"));
    }

    @Test
    public void shouldReturnRssDetailUrlIfTitleHasBlank(){
        String pageUrl =  service.getRssDetailPageUrl("java  go", 1L);
        assertThat(pageUrl, equalTo("http://www.java4us.net/java-go-r1"));
    }

    @Test
    public void shouldReturnRssDetailUrlIfTitleHasBlankAndIllegalChar(){
        String pageUrl =  service.getRssDetailPageUrl("java  syncronized -keyword", 1L);
        assertThat(pageUrl, equalTo("http://www.java4us.net/java-syncronized-keyword-r1"));
    }
}
