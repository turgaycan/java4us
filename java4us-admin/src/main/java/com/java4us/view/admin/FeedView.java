package com.java4us.view.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.java4us.commons.service.feed.FeedService;
import com.java4us.commons.utils.Clock;
import com.java4us.commons.utils.criteria.FeedSearchCriteria;
import com.java4us.commons.utils.criteria.StatefulSearchCriteria;
import com.java4us.domain.Feed;
import com.java4us.domain.common.enums.BaseStatus;
import com.java4us.domain.common.enums.Category;
import com.java4us.jsf.model.LazyDataModel;
import com.java4us.jsf.model.StatefulLazyDataProdiver;
import com.java4us.jsf.views.BaseView;

@ManagedBean(name = "feedView")
@ViewScoped
public class FeedView extends BaseView implements
		StatefulLazyDataProdiver<Feed> {

	private static final long serialVersionUID = -964297412749823764L;

	public FeedSearchCriteria filter;

	@ManagedProperty(value = "#{feedService}")
	private transient FeedService feedService;

	private Feed selectedFeed;
	private List<Feed> selectedFeeds;
	private LazyDataModel<Feed> feedList;
	private List<SelectItem> statusList;
	private List<SelectItem> categories;

	@PostConstruct
	public void init() {
		filter = new FeedSearchCriteria();
		feedList = new LazyDataModel<Feed>(this);
		statusList = new ArrayList<>();
		for (BaseStatus statu : BaseStatus.values()) {
			statusList.add(new SelectItem(statu));
		}
		categories = new ArrayList<>();
		for (Category category : Category.values()) {
			categories.add(new SelectItem(category));
		}
	}

	@Override
	public List<Feed> loadLazyData(int first, int pageSize, String sortField,
			String sortOrder) {
		return feedService.findFeedList(first, pageSize, sortField,
				sortOrder, filter);
	}

	@Override
	public int getRowCount() {
		return feedService.getRowCount(filter);
	}

	private void update() {
		if (selectedFeed == null) {
			addErrorMessage("error.nonselected.message");
			return;
		}
		selectedFeed.setCreateDate(Clock.getTime());
		feedService.update(selectedFeed);
	}

	public void reset() {
		setFilter(new FeedSearchCriteria());
		search();
	}

	public void search() {
		getSearchFilter().clear();
	}

	public void onRowEdit(RowEditEvent event) {
		selectedFeed = (Feed) event.getObject();
		if (selectedFeed != null) {
			update();
		}
		FacesMessage msg = new FacesMessage("Edited",
				((Feed) event.getObject()).getLink());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled",
				((Feed) event.getObject()).getLink());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Cell Changed", "Old : " + oldValue + ", New :" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void setFeedService(FeedService feedService) {
		this.feedService = feedService;
	}

	public FeedSearchCriteria getFilter() {
		return filter;
	}

	public void setFilter(FeedSearchCriteria filter) {
		this.filter = filter;
	}

	public Feed getSelectedFeed() {
		return selectedFeed;
	}

	public void setSelectedFeed(Feed selectedFeed) {
		this.selectedFeed = selectedFeed;
	}

	public List<Feed> getSelectedFeeds() {
		return selectedFeeds;
	}

	public void setSelectedFeeds(List<Feed> selectedFeeds) {
		this.selectedFeeds = selectedFeeds;
	}

	public LazyDataModel<Feed> getFeedList() {
		return feedList;
	}

	public void setFeedList(LazyDataModel<Feed> feedList) {
		this.feedList = feedList;
	}

	public List<SelectItem> getStatusList() {
		return statusList;
	}

	@Override
	public StatefulSearchCriteria getSearchFilter() {
		return getFilter();
	}

	public List<SelectItem> getCategories() {
		return categories;
	}

	public void setCategories(List<SelectItem> categories) {
		this.categories = categories;
	}

}
