package com.java4us.commons.decorator;

import com.java4us.domain.core.BaseEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.List;

/**
 * Created by turgaycan on 6/21/15.
 */
public class AbstractModelDecorator<T extends BaseEntity> implements IDecorator<T> {
    private AbstractModelDecorator next;
    private boolean condition;
    private String anyAlias;

    public AbstractModelDecorator(boolean condition, String anyAlias) {
        this.condition = condition;
        this.anyAlias = anyAlias;
    }

    @Override
    public void applyFilter(List<T> anyList) {
        if (condition) {
            CollectionUtils.filter(anyList, new Predicate() {
                @Override
                public boolean evaluate(Object cartCoupon) {
                    return true;
                }
            });
        }
        applyNextIfExists(anyList);
    }

    @Override
    public void apply(T entity) {
        if (condition) {
            //do anything
        }
    }

    @Override
    public List<T> filterBy(List<T> anyList) {
        return anyList;
    }

    private void applyNextIfExists(List<T> anyList) {
        if (next != null) {
            next.applyFilter(anyList);
        }
    }

    public void setNext(AbstractModelDecorator next) {
        this.next = next;
    }

    public AbstractModelDecorator getNext() {
        return next;
    }

    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    public String getAnyAlias() {
        return anyAlias;
    }

    public void setAnyAlias(String anyAlias) {
        this.anyAlias = anyAlias;
    }
}