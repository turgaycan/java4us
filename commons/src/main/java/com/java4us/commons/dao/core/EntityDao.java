/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.dao.core;

import com.java4us.domain.core.BaseEntity;
import org.springframework.stereotype.Repository;

/**
 *
 * @author turgay
 */
@Repository
public class EntityDao extends BaseDao<BaseEntity> {

    public EntityDao() {
        super(BaseEntity.class);
    }

}
