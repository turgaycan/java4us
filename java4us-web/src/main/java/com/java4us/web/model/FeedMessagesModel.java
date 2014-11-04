package com.java4us.web.model;

import java.io.Serializable;
import java.util.List;

import com.java4us.domain.FeedMessage;

public class FeedMessagesModel implements Serializable {

	private static final long serialVersionUID = 956373438352236241L;
	private List<FeedMessage> javaFeedMessages;
	private List<FeedMessage> androidFeedMessages;

	public List<FeedMessage> getJavaFeedMessages() {
		return javaFeedMessages;
	}

	public void setJavaFeedMessages(List<FeedMessage> javaFeedMessages) {
		this.javaFeedMessages = javaFeedMessages;
	}

	public List<FeedMessage> getAndroidFeedMessages() {
		return androidFeedMessages;
	}

	public void setAndroidFeedMessages(List<FeedMessage> androidFeedMessages) {
		this.androidFeedMessages = androidFeedMessages;
	}

}
