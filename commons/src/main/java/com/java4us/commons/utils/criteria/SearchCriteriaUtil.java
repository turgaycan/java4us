/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.utils.criteria;

/**
 *
 * @author turgay
 */
import com.java4us.commons.enums.DateBefore;
import com.java4us.domain.core.BaseEntity;
import com.java4us.commons.utils.DateRange;
import com.java4us.commons.utils.DateUtils;
import com.java4us.commons.utils.criteria.restriction.Java4UsRestrictions;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.internal.CriteriaImpl;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public final class SearchCriteriaUtil {

    private SearchCriteriaUtil() {
    }

    public static void dateTypeForDateRange(Criteria criteria, String propertyName, DateRange dateRange) {
        if (dateRange == null) {
            return;
        }

        if (dateRange.getStartDate() == null && dateRange.getFinishDate() == null) {
            return;
        }

        if (dateRange.getStartDate() == null && dateRange.getFinishDate() != null) {
            dateTypeForLe(criteria, propertyName, dateRange.getFinishDate());
        } else if (dateRange.getStartDate() != null && dateRange.getFinishDate() == null) {
            dateTypeForGe(criteria, propertyName, dateRange.getStartDate());
        } else {
            dateTypeForBetween(criteria, propertyName, dateRange);
        }
    }

    public static void dateTypeForBetween(Criteria criteria, String propertyName, DateRange dateRange) {
        if (dateRange != null && dateRange.isNotNullStartAndFinishDate()) {
            Date startDate = DateUtils.toStartOfDay(dateRange.getStartDate());
            Date endDate = DateUtils.toEndOfDay(dateRange.getFinishDate());
            criteria.add(Restrictions.between(propertyName, startDate, endDate));
        }
    }

    public static void dateTypeForGe(Criteria criteria, String propertyName, Date date) {
        if (date != null) {
            Date startDate = DateUtils.toStartOfDay(date);
            criteria.add(Restrictions.ge(propertyName, startDate));
        }
    }

    public static void dateTypeForGe(Criteria criteria, String createDate, DateBefore beforeDate) {
        if (beforeDate != null) {
            dateTypeForGe(criteria, createDate, beforeDate.getDate());
        }
    }

    public static void dateTypeForLe(Criteria criteria, String propertyName, Date date) {
        if (date != null) {
            Date endDate = DateUtils.toEndOfDay(date);
            criteria.add(Restrictions.le(propertyName, endDate));
        }
    }

    public static void anyObjectEq(Criteria criteria, String propertyName, Object object) {
        if (object != null) {
            criteria.add(Restrictions.eq(propertyName, object));
        }
    }

    public static void anyObjectNotEq(Criteria criteria, String propertyName, Object object) {
        if (object != null) {
            criteria.add(Restrictions.not(Restrictions.eq(propertyName, object)));
        }
    }

    public static void listIn(Criteria criteria, String propertyName, List<?> list) {
        if (list != null && !list.isEmpty()) {
            criteria.add(Restrictions.in(propertyName, list));
        }
    }

    public static void arrayIn(Criteria criteria, String propertyName, Object[] array) {
        if (array.length > 0) {
            criteria.add(Restrictions.in(propertyName, array));
        }
    }

    public static void listNotIn(Criteria criteria, String propertyName, List<?> list) {
        if (list != null && !list.isEmpty()) {
            criteria.add(Restrictions.not(Restrictions.in(propertyName, list)));
        }
    }

    public static void arrayNotIn(Criteria criteria, String propertyName, Object[] array) {
        if (array.length > 0) {
            criteria.add(Restrictions.not(Restrictions.in(propertyName, array)));
        }
    }

    public static void stringIlikeAnywhere(Criteria criteria, String propertyName, String searchedString) {
        if (StringUtils.isNotBlank(searchedString)) {
            criteria.add(Java4UsRestrictions.ilike(propertyName, searchedString.trim(), MatchMode.ANYWHERE));
        }
    }

    public static void stringIlikeExact(Criteria criteria, String propertyName, String searchedString) {
        if (StringUtils.isNotBlank(searchedString)) {
            criteria.add(Java4UsRestrictions.ilike(propertyName, searchedString.trim(), MatchMode.EXACT));
        }
    }

    public static void stringEqualsIfNotBlank(Criteria criteria, String propertyName, String searchedString) {
        if (StringUtils.isNotBlank(searchedString)) {
            criteria.add(Restrictions.eq(propertyName, searchedString));
        }
    }

    public static void idEq(Criteria criteria, Long id) {
        if (id != null) {
            criteria.add(Restrictions.eq("id", id));
        }
    }

    public static Criteria restoreStateBy(Criteria criteria, Session currentSession) {
        if (criteria != null) {
            Java4UsRestrictions.clearAllOrders(criteria);
            Java4UsRestrictions.clearAllAliases(criteria);
            ((CriteriaImpl) criteria).setSession((SessionImplementor) currentSession);
        }

        return criteria;
    }

    public static Criteria restoreListStateBy(StatefulSearchCriteria filter, Session currentSession) {
        return restoreStateBy(filter.popList(), currentSession);
    }

    public static Criteria restoreCountStateBy(StatefulSearchCriteria filter, Session currentSession) {
        return restoreStateBy(filter.popCount(), currentSession);
    }

    public static void neAndNull(Criteria criteria, String field, Object parameter) {
        if (parameter != null) {
            criteria.add(Restrictions.ne(field, parameter));
        } else {
            criteria.add(Restrictions.isNotNull(field));
        }
    }

    public static void anyEntityEqOrNull(Criteria criteria, String propertyName, BaseEntity entity) {
        if (entity != null && entity.isPersisted()) {
            criteria.add(Restrictions.eq(propertyName, entity));
        } else {
            criteria.add(Restrictions.isNull(propertyName));
        }
    }

    public static void appendDateCriteria(Criteria criteria, DateRange dateRange, String startDatePropertyName, String endDatePropertyName) {
        if (dateRange != null) {
            if (dateRange.getStartDate() != null && dateRange.getFinishDate() != null) {
                addActiveDateCriteria(startDatePropertyName, endDatePropertyName, dateRange.getStartDate(), dateRange.getFinishDate(), criteria);
            } else if (dateRange.getStartDate() != null && dateRange.getFinishDate() == null) {
                appendPropertyBetween(criteria, startDatePropertyName, endDatePropertyName, dateRange.getStartDate());
            } else if (dateRange.getStartDate() == null && dateRange.getFinishDate() != null) {
                appendPropertyBetween(criteria, startDatePropertyName, endDatePropertyName, dateRange.getFinishDate());
            }
        }
    }

    public static void appendPropertyBetween(Criteria criteria, String startDatePropertyName, String endDatePropertyName, Date dateParam) {
        criteria.add(Restrictions.le(startDatePropertyName, dateParam));
        criteria.add(Restrictions.ge(endDatePropertyName, dateParam));
    }

    public static void addActiveDateCriteria(String startDateProperty, String endDateProperty, Date startDate, Date endDate, Criteria criteria) {
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.between(startDateProperty, startDate, endDate));
        disjunction.add(Restrictions.between(endDateProperty, startDate, endDate));

        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.ge(startDateProperty, startDate));
        conjunction.add(Restrictions.le(endDateProperty, endDate));

        Conjunction conjunctionBetween = Restrictions.conjunction();
        conjunctionBetween.add(Restrictions.le(startDateProperty, startDate));
        conjunctionBetween.add(Restrictions.ge(endDateProperty, endDate));

        disjunction.add(conjunction);
        disjunction.add(conjunctionBetween);

        criteria.add(disjunction);
    }

}
