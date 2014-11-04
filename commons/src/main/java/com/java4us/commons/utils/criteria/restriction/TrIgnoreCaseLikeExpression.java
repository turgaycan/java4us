/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.utils.criteria.restriction;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.TypedValue;

/**
 *
 * @author turgay
 */
public class TrIgnoreCaseLikeExpression implements Criterion {

    private static final long serialVersionUID = -8631897240447486420L;

    private final String propertyName;
    private final Object value;

    protected TrIgnoreCaseLikeExpression(String propertyName, String value) {
        this.propertyName = propertyName;
        this.value = value;
    }

    protected TrIgnoreCaseLikeExpression(String propertyName, String value, MatchMode matchMode) {
        this(propertyName, matchMode.toMatchString(value));
    }

    public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) {
        Dialect dialect = criteriaQuery.getFactory().getDialect();
        String[] columns = criteriaQuery.findColumns(propertyName, criteria);
        if (columns.length != 1) {
            throw new IllegalArgumentException("Like may only be used with single-column properties");
        }
        String column = columns[0];

        return '(' + dialect.getLowercaseFunction() + '(' + column + ')' + " like ? or " + "upper" + '(' + column + ')' + " like ?)";
    }

    public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery) {
        return new TypedValue[]{
            criteriaQuery.getTypedValue(criteria, propertyName, value.toString().toLowerCase()),
            criteriaQuery.getTypedValue(criteria, propertyName, value.toString().toUpperCase())
        };
    }

}
