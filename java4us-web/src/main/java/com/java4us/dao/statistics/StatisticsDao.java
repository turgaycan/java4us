package com.java4us.dao.statistics;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.java4us.domain.common.enums.Category;
import com.java4us.web.model.StatisticModel;

@Repository
public class StatisticsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Cacheable("default")
	public StatisticModel getJavaStatisticsModel() {
		SQLQuery query = (SQLQuery) getSessionFactory().getCurrentSession()
				.getNamedQuery("statistics")
				.setParameter("category", Category.JAVA.name());
		query.setResultTransformer(Transformers
				.aliasToBean(StatisticModel.class));
		return (StatisticModel) query.uniqueResult();
	}

	@Cacheable("default")
	public StatisticModel getAndroidStatisticsModel() {
		SQLQuery query = (SQLQuery) getSessionFactory().getCurrentSession()
				.getNamedQuery("statistics")
				.setParameter("category", Category.ANDROID.name());
		query.setResultTransformer(Transformers
				.aliasToBean(StatisticModel.class));
		return (StatisticModel) query.uniqueResult();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
