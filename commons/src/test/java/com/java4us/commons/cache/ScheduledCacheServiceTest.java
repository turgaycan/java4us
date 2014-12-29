package com.java4us.commons.cache;

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.protocol.views.View;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ScheduledCacheServiceTest {

    @InjectMocks
    private ScheduledCacheService service;

    @Mock
    private CacheService cacheService;

    @Qualifier("java4UsCacheService")
    @Mock
    private CacheService java4UCacheService;

    @Mock
    private View view;

    @Mock
    private CouchbaseClient couchbaseClient;

    @Test
    public void shouldClearAllCache() {
        service.flushCacheEveryDay();
        verify(cacheService, times(1)).flushCache();
    }

    @Test
    public void shouldClearFeedMessageFromView() throws Exception {
        when(java4UCacheService.getJava4UsView()).thenReturn(view);
        when(java4UCacheService.getCouchbaseClient()).thenReturn(couchbaseClient);
        service.updateFeedMessagesFromCache();

        verify(java4UCacheService, times(1)).getJava4UsView();
        verify(java4UCacheService, times(1)).getCouchbaseClient();
    }
}