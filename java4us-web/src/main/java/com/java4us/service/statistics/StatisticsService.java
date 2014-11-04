package com.java4us.service.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java4us.dao.statistics.StatisticsDao;
import com.java4us.web.model.StatisticModel;

@Service
public class StatisticsService {

	@Autowired
	private StatisticsDao statisticsDao; 
	
	@Transactional(readOnly = true)
	public StatisticModel getJavaStatisticModel() {
		return statisticsDao.getJavaStatisticsModel();
	}

	@Transactional(readOnly = true)
	public StatisticModel getAndroidStatisticModel() {
		return statisticsDao.getAndroidStatisticsModel();
	}
}
