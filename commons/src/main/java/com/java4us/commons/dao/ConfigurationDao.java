/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.dao;

import com.java4us.commons.dao.core.BaseDao;
import com.java4us.domain.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author turgay
 */
@Repository
public class ConfigurationDao extends BaseDao<Configuration> {

    public ConfigurationDao() {
        super(Configuration.class);
    }

    @SuppressWarnings("unchecked")
	public List<Configuration> findActiveConfigsAll() {
        return getStatusActiveCriteria().list();
    }

}
