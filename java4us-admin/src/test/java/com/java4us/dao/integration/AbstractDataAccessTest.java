/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.dao.integration;

import com.java4us.domain.core.BaseEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author turgay
 */
@ContextConfiguration({"/applicationContext-test.xml"})
@Transactional
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public abstract class AbstractDataAccessTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    @Qualifier(value = "sessionFactory")
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void flushAndClear() {
        getSession().flush();
        getSession().clear();
    }

    public <T extends BaseEntity> T reget(T entity) {
        return (T) getSession().get(Hibernate.getClass(entity), entity.getId());
    }
}
