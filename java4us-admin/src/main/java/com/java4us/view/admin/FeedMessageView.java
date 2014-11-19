package com.java4us.view.admin;

import com.java4us.commons.service.feed.FeedMessageService;
import com.java4us.commons.utils.Clock;
import com.java4us.commons.utils.criteria.FeedMessageSearchCriteria;
import com.java4us.commons.utils.criteria.StatefulSearchCriteria;
import com.java4us.domain.FeedMessage;
import com.java4us.domain.common.enums.BaseStatus;
import com.java4us.domain.common.enums.Category;
import com.java4us.jsf.model.LazyDataModel;
import com.java4us.jsf.model.StatefulLazyDataProdiver;
import com.java4us.jsf.views.BaseView;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "feedMessageView")
@ViewScoped
public class FeedMessageView extends BaseView implements
		StatefulLazyDataProdiver<FeedMessage> {

	private static final long serialVersionUID = -964297412749823764L;

	public FeedMessageSearchCriteria filter;

	@ManagedProperty(value = "#{feedMessageService}")
	private transient FeedMessageService feedMessageService;

	private FeedMessage selectedFeedMessage;
	private List<FeedMessage> selectedFeedMessages;
	private LazyDataModel<FeedMessage> feedMessageList;
	private List<SelectItem> statusList;
	private List<SelectItem> categories;

	@PostConstruct
	public void init() {
		filter = new FeedMessageSearchCriteria();
		feedMessageList = new LazyDataModel<FeedMessage>(this);
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
	public List<FeedMessage> loadLazyData(int first, int pageSize, String sortField,
			String sortOrder) {
		return feedMessageService.findFeedMessageList(first, pageSize, sortField,
				sortOrder, filter);
	}

	@Override
	public int getRowCount() {
		return feedMessageService.getRowCount(filter);
	}

	private void update() {
		if (selectedFeedMessage == null) {
			addErrorMessage("error.nonselected.message");
			return;
		}
		selectedFeedMessage.setCreateDate(Clock.getTime());
		feedMessageService.update(selectedFeedMessage);
	}

	public void reset() {
		setFilter(new FeedMessageSearchCriteria());
		search();
	}

	public void search() {
		getSearchFilter().clear();
	}

	public void onRowEdit(RowEditEvent event) {
		selectedFeedMessage = (FeedMessage) event.getObject();
		if (selectedFeedMessage != null) {
			update();
		}
		FacesMessage msg = new FacesMessage("Edited",
				((FeedMessage) event.getObject()).getLink());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled",
				((FeedMessage) event.getObject()).getLink());
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

	public void setFeedMessageService(FeedMessageService feedMessageService) {
		this.feedMessageService = feedMessageService;
	}

	public FeedMessageSearchCriteria getFilter() {
		return filter;
	}

	public void setFilter(FeedMessageSearchCriteria filter) {
		this.filter = filter;
	}

	public FeedMessage getSelectedFeedMessage() {
		return selectedFeedMessage;
	}

	public void setSelectedFeedMessage(FeedMessage selectedFeedMessage) {
		this.selectedFeedMessage = selectedFeedMessage;
	}

	public List<FeedMessage> getSelectedFeedMessages() {
		return selectedFeedMessages;
	}

	public void setSelectedFeedMessages(List<FeedMessage> selectedFeedMessages) {
		this.selectedFeedMessages = selectedFeedMessages;
	}

	public LazyDataModel<FeedMessage> getFeedMessageList() {
		return feedMessageList;
	}

	public void setFeedMessageList(LazyDataModel<FeedMessage> feedMessageList) {
		this.feedMessageList = feedMessageList;
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
