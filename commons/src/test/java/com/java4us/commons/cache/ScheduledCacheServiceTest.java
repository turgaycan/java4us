package com.java4us.commons.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ScheduledCacheServiceTest{

    @InjectMocks
    private ScheduledCacheService service;

    @Mock
    private CacheService cacheService;

    @Test
    public void shouldClearAllCache(){
        service.flushCacheEveryDay();
        verify(cacheService, times(1)).flushCache();
    }
}