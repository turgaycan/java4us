/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.domain.builder;

import com.java4us.domain.core.BaseEntity;
import org.apache.commons.lang.math.RandomUtils;
import org.hibernate.Session;

/**
 *
 * @author turgay
 * @param <T>
 * @param <B>
 */
public abstract class BaseBuilder<T extends BaseEntity, B extends BaseBuilder<T, B>> {

    private Long id;
    private boolean deleted = false;

    @SuppressWarnings("unchecked")
    public B id(Long id) {
        this.id = id;
        return (B) this;
    }

    @SuppressWarnings("unchecked")
    public B deleted(boolean deleted) {
        this.deleted = deleted;
        return (B) this;
    }

    public Long getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @SuppressWarnings("unchecked")
    public T persist(Session session) {
        T toPersist = build();
        return (T) session.merge(toPersist);
    }

    public T build() {
        T baseEntity = doBuild();
        baseEntity.setId(id);
        baseEntity.setDeleted(deleted);
        return baseEntity;
    }

    public T buildWithId() {
        T baseEntity = build();
        baseEntity.setId(RandomUtils.nextLong());
        return baseEntity;
    }

    protected abstract T doBuild();

    protected void flushAndClear(Session session) {
        session.flush();
        session.clear();
    }

}
