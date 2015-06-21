package com.java4us.commons.decorator;

import com.java4us.domain.core.BaseEntity;

import java.util.List;

/**
 * Created by turgaycan on 6/21/15.
 */
public interface IDecorator<T extends BaseEntity> {

    void applyFilter(List<T> anyList);
    void apply(T entity);
    List<T> filterBy(List<T> anyList);

}
