/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.dao.core;

import com.java4us.domain.common.enums.BaseStatus;
import com.java4us.domain.core.BaseEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author turgay
 * @param <T>
 */
public class BaseDao<T extends BaseEntity> {

    @Autowired
    @Qualifier(value = "sessionFactory")
    private SessionFactory sessionFactory;

    public static final int SESSION_CLEAR_COUNT = 1000;

    @SuppressWarnings("rawtypes")
	protected Class getClazz() {
        return clazz;
    }

    private final Class<T> clazz;

    public BaseDao(final Class<T> clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    public T findBy(Long id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public T findByUnique(String propertyName, Object value) {
        return (T) getCriteria().add(Restrictions.eq(propertyName, value)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<T> findBy(String propertyName, Object value) {
        return getCriteria().add(Restrictions.eq(propertyName, value)).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getCriteria().list();
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll(int first, int maxSize) {
        return getCriteria().setFirstResult(first).setMaxResults(maxSize).list();
    }

    public Integer count(String propertyName, Object value) {
        return Integer.parseInt(getCriteria().add(Restrictions.eq(propertyName, value)).setProjection(Projections.rowCount()).uniqueResult().toString());
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public T merge(T target) {
        return (T) getCurrentSession().merge(target);
    }

    @Transactional
    public void persist(T target) {
        getCurrentSession().persist(target);
    }

    @SuppressWarnings("unchecked")
	@Transactional
    public T saveEntity(T target) {
        if (target.isPersisted()) {
            return (T) getCurrentSession().merge(target);
        } else {
            getCurrentSession().persist(target);
            return target;
        }
    }

    @Transactional
    public void purgeDelete(T target) {
        getCurrentSession().delete(target);
    }

    @Transactional
    public void delete(T target) {
        target.setDeleted(true);
        getCurrentSession().merge(target);
    }

    @Transactional
    public void evict(T target) {
        getCurrentSession().evict(target);
    }

    protected Criteria getCriteria() {
        Criteria criteria = createEmptyCriteria();
        appendNotDeletedRestriction(criteria);
        return criteria;
    }

    protected Criteria getStatusActiveCriteria() {
        return getCriteria().add(Restrictions.eq("status", BaseStatus.Active));
    }
    
    protected Criteria getStatusDeactiveCriteria() {
        return getCriteria().add(Restrictions.eq("status", BaseStatus.Deactive));
    }

    protected Query getQuery(String queryStr) {
        return getCurrentSession().createQuery(queryStr);
    }

    public void bulkUpdate(String name, Object value, List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            getCurrentSession().createQuery("update " + clazz.getName() + " set " + name + " = :value where id in (:ids)").setParameter("value", value).setParameterList("ids", ids).executeUpdate();
        }
    }

    public Criteria getPartialCriteria(int first, int pageSize) {
        return applyPartialCriteria(getCriteria(), first, pageSize);
    }

    public Criteria applyPartialCriteria(Criteria criteria, int first, int pageSize) {
        setFirstAndMaxResultForCriteria(criteria, first, pageSize);
        return criteria;
    }

    public void setFirstAndMaxResultForCriteria(Criteria criteria, int first, int pageSize) {
        criteria.setFirstResult(first).setMaxResults(pageSize);
    }

    public Criteria getLazyCriteria(int first, int pageSize, String sortField, String sortOrder) {
        Criteria criteria = getPartialCriteria(first, pageSize);
        setupSortCriteria(criteria, sortField, sortOrder);
        return criteria;
    }

    public Criteria applyLazyCriteria(Criteria criteria, int first, int pageSize, String sortField, String sortOrder) {
        applyPartialCriteria(criteria, first, pageSize);
        setupSortCriteria(criteria, sortField, sortOrder);
        return criteria;
    }

    /**
     * @return Returns null if no ID's found !!!
     */
    public Criteria getCriteriaWithDistinctId(Criteria criteria, int first, int pageSize, String sortField, String sortOrder, Boolean useNLSSort) {
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.id(), "id");
        Criteria newCriteria;
        if (sortField != null) {
            newCriteria = prepareCriteriaWithSortField(criteria, first, pageSize, sortField, sortOrder, projectionList, useNLSSort);
        } else {
            newCriteria = prepareCriteriaWithoutSortField(criteria, first, pageSize, projectionList);
        }

        return newCriteria;
    }

    @SuppressWarnings("unchecked")
    private Criteria prepareCriteriaWithoutSortField(Criteria criteria, int first, int pageSize, ProjectionList projectionList) {
        criteria.setProjection(Projections.distinct(projectionList));
        setFirstAndMaxResultForCriteria(criteria, first, pageSize);

        List<Long> idList = criteria.list();

        if (CollectionUtils.isNotEmpty(idList)) {
            Criteria newCriteria = getCriteria();
            newCriteria.add(Restrictions.in("id", idList));
            return newCriteria;
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    private Criteria prepareCriteriaWithSortField(Criteria criteria, int first, int pageSize, String sortField, String sortOrder, ProjectionList projectionList, Boolean useNLSSort) {
        Criteria newCriteria;
        projectionList.add(Projections.property(sortField));

        criteria.setProjection(Projections.distinct(projectionList));
        setFirstAndMaxResultForCriteria(criteria, first, pageSize);
        setupSortCriteria(criteria, sortField, sortOrder);
        List<Object[]> rowList = criteria.list();

        List<Long> idList = new ArrayList<Long>();
        for (Object[] row : rowList) {
            idList.add((Long) row[0]);
        }

        if (CollectionUtils.isNotEmpty(idList)) {
            newCriteria = getCriteria();
            setupSortCriteria(newCriteria, sortField, sortOrder);
            newCriteria.add(Restrictions.in("id", idList));
            return newCriteria;
        }
        return null;
    }

    public void setupSortCriteria(Criteria criteria, String sortField, String sortOrder) {
        if (StringUtils.isNotBlank(sortOrder) && StringUtils.isNotBlank(sortField)) {
            if ("ascending".equalsIgnoreCase(sortOrder) || "asc".equalsIgnoreCase(sortOrder)) {
                criteria.addOrder(Order.asc(sortField).ignoreCase());
            } else if ("descending".equalsIgnoreCase(sortOrder) || "desc".equalsIgnoreCase(sortOrder)) {
                criteria.addOrder(Order.desc(sortField).ignoreCase());
            }
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public int getRowCount(Criteria criteria) {
        return ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    public int getRowCountDistinctId(Criteria criteria) {
        Long count = (Long) criteria.setProjection(Projections.countDistinct("id")).uniqueResult();

        if (count == null) {
            return 0;
        }

        return count.intValue();
    }

    public void flushAndClearCurrentSession() {
        flushAndClearSession(getCurrentSession());
    }

    protected Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    protected void flushAndClearSession(Session session) {
        session.flush();
        session.clear();
    }

    protected Criteria addPriorityAscOrder(Criteria criteria) {
        criteria.addOrder(Order.asc("priority"));
        return criteria;
    }

    protected void appendDeleteRestriction(Criteria criteria, boolean deleteFlag) {
        criteria.add(Restrictions.eq("deleted", deleteFlag));
    }

    protected void appendNotDeletedRestriction(Criteria criteria) {
        appendDeleteRestriction(criteria, false);
    }

    protected Criteria createEmptyCriteria() {
        return getCurrentSession().createCriteria(clazz);
    }

    protected Criteria createEmptyCriteria(String alias) {
        return getCurrentSession().createCriteria(clazz, alias);
    }

    protected Criteria getCriteria(String alias) {
        Criteria criteria = createEmptyCriteria(alias);
        appendNotDeletedRestriction(criteria);
        return criteria;
    }

}
