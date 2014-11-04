/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.utils.criteria;

import org.hibernate.Criteria;

/**
 *
 * @author turgay
 */
public class LazySearchCriteria extends SearchCriteria implements StatefulSearchCriteria {

    private Criteria list;
    private Criteria count;

    @Override
    public Criteria popList() {
        return list;
    }

    @Override
    public Criteria popCount() {
        return count;
    }

    @Override
    public void pushList(Criteria criteria) {
        this.list = criteria;
    }

    @Override
    public void pushCount(Criteria criteria) {
        this.count = criteria;
    }

    @Override
    public void clear() {
        this.list = null;
        this.count = null;
    }

}
