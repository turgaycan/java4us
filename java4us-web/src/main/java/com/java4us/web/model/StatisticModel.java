package com.java4us.web.model;

import java.io.Serializable;

public class StatisticModel implements Serializable {

	private static final long serialVersionUID = 7917398268319872340L;

	private Long feedersCount;
	private Long feedsCount;
	private Long feedMessagesCount;
	private Long subscribersCount;

	public Long getFeedersCount() {
		return feedersCount;
	}

	public void setFeedersCount(Long feedersCount) {
		this.feedersCount = feedersCount;
	}

	public Long getFeedsCount() {
		return feedsCount;
	}

	public void setFeedsCount(Long feedsCount) {
		this.feedsCount = feedsCount;
	}

	public Long getFeedMessagesCount() {
		return feedMessagesCount;
	}

	public void setFeedMessagesCount(Long feedMessagesCount) {
		this.feedMessagesCount = feedMessagesCount;
	}

	public Long getSubscribersCount() {
		return subscribersCount;
	}

	public void setSubscribersCount(Long subscribersCount) {
		this.subscribersCount = subscribersCount;
	}

}
