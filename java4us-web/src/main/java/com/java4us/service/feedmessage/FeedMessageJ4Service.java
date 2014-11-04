package com.java4us.service.feedmessage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.java4us.commons.cache.CacheService;
import com.java4us.commons.service.feed.FeedMessageService;
import com.java4us.commons.utils.criteria.FeedMessageSearchCriteria;
import com.java4us.domain.FeedMessage;
import com.java4us.domain.common.enums.BaseStatus;
import com.java4us.domain.common.enums.Category;
import com.java4us.web.model.FeedMessagesModel;

@Service
public class FeedMessageJ4Service {

	private static final String FEEDMESSAGESMODEL = "feedMessagesModel_";

	@Autowired
	private FeedMessageService feedMessageService;

	@Qualifier("java4UsCacheService")
	@Autowired
	private CacheService cacheService;

	public FeedMessagesModel getFeedMessages(int currentPage, int pageSize)
			throws Exception {

		FeedMessagesModel model = cacheService.get(getCacheKey(currentPage));
		if (model != null) {
			return model;
		}
		FeedMessagesModel newModel = prepareFeedMessagesModel(currentPage,
				pageSize);
		putModelInCache(currentPage, newModel);
		return newModel;
	}

	private FeedMessagesModel prepareFeedMessagesModel(int currentPage,
			int pageSize) {
		FeedMessagesModel newModel = new FeedMessagesModel();
		FeedMessageSearchCriteria filter = new FeedMessageSearchCriteria();
		filter.setProceed(true);
		filter.setStatus(BaseStatus.Active);
		prepareJavaFeedMessages(currentPage, pageSize, newModel, filter);
		prepareAndroidFeedMessages(currentPage, pageSize, newModel, filter);
		return newModel;
	}

	private void prepareJavaFeedMessages(int currentPage, int pageSize,
			FeedMessagesModel newModel, FeedMessageSearchCriteria filter) {
		filter.setCategory(Category.JAVA);
		List<FeedMessage> feedMessages = feedMessageService
				.findFeedMessageList(currentPage, pageSize, "", "", filter);
		newModel.setJavaFeedMessages(feedMessages);
	}

	private void prepareAndroidFeedMessages(int currentPage, int pageSize,
			FeedMessagesModel newModel, FeedMessageSearchCriteria filter) {
		filter.setCategory(Category.ANDROID);
		List<FeedMessage> feedMessages = feedMessageService
				.findFeedMessageList(currentPage, pageSize, "", "", filter);
		newModel.setAndroidFeedMessages(feedMessages);
	}

	private void putModelInCache(int currentPage, FeedMessagesModel newModel) {
		cacheService.put(getCacheKey(currentPage), newModel,
				CacheService.ONE_MINUTE);
	}

	private String getCacheKey(int currentPage) {
		return FEEDMESSAGESMODEL + currentPage;
	}

}
