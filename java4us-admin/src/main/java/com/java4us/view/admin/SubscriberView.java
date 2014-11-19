package com.java4us.view.admin;

import com.java4us.commons.service.member.SubscriberService;
import com.java4us.commons.utils.Clock;
import com.java4us.commons.utils.criteria.StatefulSearchCriteria;
import com.java4us.commons.utils.criteria.SubscriberSearchCriteria;
import com.java4us.domain.Subscriber;
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
import java.util.List;

@ManagedBean(name = "subscriberView")
@ViewScoped
public class SubscriberView extends BaseView implements
		StatefulLazyDataProdiver<Subscriber> {

	private static final long serialVersionUID = 5610311164033046449L;

	public SubscriberSearchCriteria filter;

	@ManagedProperty(value = "#{subscriberService}")
	private transient SubscriberService subscriberService;

	private Subscriber selectedSubscriber;
	private LazyDataModel<Subscriber> subscriberList;

	@PostConstruct
	public void init() {
		filter = new SubscriberSearchCriteria();
		subscriberList = new LazyDataModel<>(this);
	}

	@Override
	public List<Subscriber> loadLazyData(int first, int pageSize,
			String sortField, String sortOrder) {
		return subscriberService.findSubscriberList(first, pageSize, sortField,
				sortOrder, filter);
	}

	@Override
	public int getRowCount() {
		return subscriberService.getRowCount(filter);
	}

	private void update() {
		if (selectedSubscriber == null) {
			addErrorMessage("error.nonselected.message");
			return;
		}
		selectedSubscriber.setCreateDate(Clock.getTime());
		subscriberService.update(selectedSubscriber);
	}

	public void reset() {
		setFilter(new SubscriberSearchCriteria());
	}

	public void search() {
		getSearchFilter().clear();
	}

	public void onRowEdit(RowEditEvent event) {
		selectedSubscriber = (Subscriber) event.getObject();
		if (selectedSubscriber != null) {
			update();
		}
		FacesMessage msg = new FacesMessage("Edited",
				((Subscriber) event.getObject()).getEmail());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled",
				((Subscriber) event.getObject()).getEmail());
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

	public SubscriberSearchCriteria getFilter() {
		return filter;
	}

	public void setFilter(SubscriberSearchCriteria filter) {
		this.filter = filter;
	}

	public Subscriber getSelectedSubscriber() {
		return selectedSubscriber;
	}

	public void setSelectedSubscriber(Subscriber selectedSubscriber) {
		this.selectedSubscriber = selectedSubscriber;
	}

	public LazyDataModel<Subscriber> getSubscriberList() {
		return subscriberList;
	}

	public void setSubscriberList(LazyDataModel<Subscriber> subscriberList) {
		this.subscriberList = subscriberList;
	}

	public void setSubscriberService(SubscriberService subscriberService) {
		this.subscriberService = subscriberService;
	}

	@Override
	public StatefulSearchCriteria getSearchFilter() {
		return getFilter();
	}

}
