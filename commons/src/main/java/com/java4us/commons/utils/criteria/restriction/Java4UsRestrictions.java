/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.commons.utils.criteria.restriction;

import com.java4us.commons.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.hibernate.engine.spi.LoadQueryInfluencers;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.CriteriaImpl.CriterionEntry;
import org.hibernate.internal.SessionImpl;
import org.hibernate.loader.OuterJoinLoader;
import org.hibernate.loader.criteria.CriteriaLoader;
import org.hibernate.persister.entity.OuterJoinLoadable;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author turgay
 */
public class Java4UsRestrictions {

	private Java4UsRestrictions() {
	}

	public static Criterion ilike(String propertyName, String value,
			MatchMode matchMode) {
		return new TrIgnoreCaseLikeExpression(propertyName, value, matchMode);
	}

	@SuppressWarnings("unchecked")
	public static void clearOrder(Criteria criteria, Order property) {
		Iterator<CriteriaImpl.OrderEntry> iterator = ((CriteriaImpl) criteria)
				.iterateOrderings();
		while (iterator.hasNext()) {
			Object e = iterator.next();
			if (e instanceof Order) {
				Order order = (Order) e;
				if (order.equals(property)) {
					iterator.remove();
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void clearAllOrders(Criteria criteria) {
		Iterator<CriteriaImpl.OrderEntry> iterator = ((CriteriaImpl) criteria)
				.iterateOrderings();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
	}

	@SuppressWarnings("unchecked")
	public static void clearAllAliases(Criteria criteria) {		
		Iterator<CriteriaImpl.Subcriteria> iterator = ((CriteriaImpl) criteria)
				.iterateSubcriteria();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
	}

	@SuppressWarnings("unchecked")
	public static void clearRestriction(Criteria criteria, String property) {
		Iterator<CriterionEntry> iterator = ((CriteriaImpl) criteria)
				.iterateExpressionEntries();
		while (iterator.hasNext()) {
			CriterionEntry each = iterator.next();
			if (StringUtils.contains(each.getCriterion().toString(), property)) {
				iterator.remove();
			}
		}
	}

	public static String getToDateString(Date date) {
		return "TO_DATE('" + DateUtils.toString(date) + "','DD/MM/YYYY')"; // works
																			// both
																			// on
																			// oracle
																			// and
																			// hsql
	}

	public static String toSQL(Criteria criteria) {
		try {
			CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;
			SessionImpl session = (SessionImpl) criteriaImpl.getSession();
			SessionFactoryImplementor factory = (SessionFactoryImplementor) session
					.getSessionFactory();
			String[] implementors = factory.getImplementors(criteriaImpl
					.getEntityOrClassName());
			LoadQueryInfluencers lqis = new LoadQueryInfluencers();
			CriteriaLoader loader = new CriteriaLoader(
					(OuterJoinLoadable) factory
							.getEntityPersister(implementors[0]),
					factory, criteriaImpl, implementors[0], lqis);
			Field field = OuterJoinLoader.class.getDeclaredField("sql");
			field.setAccessible(true);
			return (String) field.get(loader);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void addCriteriaIn(String propertyName, List<?> list,
			Criteria criteria) {
		Disjunction or = Restrictions.disjunction();
		if (list.size() > 900) {
			while (list.size() > 900) {
				List<?> subList = list.subList(0, 900);
				or.add(Restrictions.in(propertyName, subList));
				list.subList(0, 900).clear();
			}
		}
		or.add(Restrictions.in(propertyName, list));
		criteria.add(or);
	}
}
