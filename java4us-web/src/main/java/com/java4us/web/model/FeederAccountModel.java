package com.java4us.web.model;

import java.io.Serializable;
import java.util.List;

import com.java4us.domain.Feed;
import com.java4us.domain.Feeder;
import com.java4us.domain.User;

public class FeederAccountModel implements Serializable {

	private static final long serialVersionUID = 8776673646756986363L;

	private User user;
	private Feeder feeder;
	private List<Feed> feedList;
	
	public static FeederAccountModel createEmptyModel(){
		return new FeederAccountModel();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Feeder getFeeder() {
		return feeder;
	}

	public void setFeeder(Feeder feeder) {
		this.feeder = feeder;
	}

	public List<Feed> getFeedList() {
		return feedList;
	}

	public void setFeedList(List<Feed> feedList) {
		this.feedList = feedList;
	}

}
