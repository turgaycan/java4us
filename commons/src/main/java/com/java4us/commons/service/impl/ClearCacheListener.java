/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.service.impl;

import com.java4us.commons.enums.CacheTypes;

/**
 *
 * @author turgay
 */
public interface ClearCacheListener {

    void refresh(CacheTypes cacheType);
}
