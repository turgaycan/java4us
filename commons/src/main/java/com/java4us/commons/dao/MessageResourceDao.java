/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.dao;

import com.java4us.commons.dao.core.BaseDao;
import com.java4us.domain.MessageResource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author turgay
 */
@Repository
public class MessageResourceDao extends BaseDao<MessageResource> {

    public MessageResourceDao() {
        super(MessageResource.class);
    }

}
