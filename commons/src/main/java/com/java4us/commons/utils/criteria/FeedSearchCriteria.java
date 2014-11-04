package com.java4us.commons.utils.criteria;

public class FeedSearchCriteria extends CommonSearchCriteria {

	private static final long serialVersionUID = -4727747124842038211L;
	private String link;
	private String feederEmail;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getFeederEmail() {
		return feederEmail;
	}

	public void setFeederEmail(String feederEmail) {
		this.feederEmail = feederEmail;
	}

}
