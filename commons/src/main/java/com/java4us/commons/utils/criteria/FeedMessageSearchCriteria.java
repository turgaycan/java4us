package com.java4us.commons.utils.criteria;

public class FeedMessageSearchCriteria extends CommonSearchCriteria {

	private static final long serialVersionUID = -166312976212661447L;
	private String link;
	private String title;
	private String feedLink;
    private boolean viewCount;
    private boolean gotoLink;
	private boolean proceed = false;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFeedLink() {
		return feedLink;
	}

	public void setFeedLink(String feedLink) {
		this.feedLink = feedLink;
	}

    public boolean isViewCount() {
        return viewCount;
    }

    public void setViewCount(boolean viewCount) {
        this.viewCount = viewCount;
    }

    public boolean isGotoLink() {
        return gotoLink;
    }

    public void setGotoLink(boolean gotoLink) {
        this.gotoLink = gotoLink;
    }

    public boolean isProceed() {
		return proceed;
	}

	public void setProceed(boolean proceed) {
		this.proceed = proceed;
	}

}
