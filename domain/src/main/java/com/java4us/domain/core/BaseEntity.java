/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.domain.core;

/**
 *
 * @author turgay
 */
import org.hibernate.Hibernate;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements IdBaseDomainObject, Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    @Audited
    private boolean deleted;

    public boolean isPersisted() {
        return getId() != null;
    }

    public boolean isNotPersisted() {
        return !isPersisted();

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }

        if (Hibernate.getClass(this) != Hibernate.getClass(other)) {
            return false;
        }

        final BaseEntity otherEntity = (BaseEntity) other;
        if (getId() == null) {
            if (otherEntity.getId() != null) {
                return false;
            }
        } else if (!getId().equals(otherEntity.getId())) {
            return false;
        }
        return true;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isNotDeleted() {
        return !isDeleted();
    }

}
