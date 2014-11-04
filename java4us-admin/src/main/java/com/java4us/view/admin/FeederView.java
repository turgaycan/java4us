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

import com.java4us.commons.service.feed.FeederService;
import com.java4us.commons.utils.Clock;
import com.java4us.commons.utils.criteria.FeederSearchCriteria;
import com.java4us.commons.utils.criteria.StatefulSearchCriteria;
import com.java4us.domain.Feeder;
import com.java4us.domain.common.enums.FeederStatus;
import com.java4us.jsf.model.LazyDataModel;
import com.java4us.jsf.model.StatefulLazyDataProdiver;
import com.java4us.jsf.views.BaseView;

@ManagedBean(name = "feederView")
@ViewScoped
public class FeederView extends BaseView implements
		StatefulLazyDataProdiver<Feeder> {

	private static final long serialVersionUID = -1241106579567525519L;
	public FeederSearchCriteria filter;

	@ManagedProperty(value = "#{feederService}")
	private transient FeederService feederService;

	private Feeder selectedFeeder;
	private List<Feeder> selectedFeeders;
	private LazyDataModel<Feeder> feederList;
	private List<SelectItem> statusList;

	@PostConstruct
	public void init() {
		filter = new FeederSearchCriteria();
		feederList = new LazyDataModel<Feeder>(this);
		statusList = new ArrayList<>();
		for (FeederStatus statu : FeederStatus.values()) {
			statusList.add(new SelectItem(statu));
		}
	}

	@Override
	public List<Feeder> loadLazyData(int first, int pageSize, String sortField,
			String sortOrder) {
		return feederService.findFeederList(first, pageSize, sortField,
				sortOrder, filter);
	}

	@Override
	public int getRowCount() {
		return feederService.getRowCount(filter);
	}

	private void update() {
		if (selectedFeeder == null) {
			addErrorMessage("error.nonselected.message");
			return;
		}
		selectedFeeder.setCreateDate(Clock.getTime());
		feederService.update(selectedFeeder);
	}
	
	public void reset(){
		setFilter(new FeederSearchCriteria());
	}
	
	public void search(){
		getSearchFilter().clear();
	}

	public void onRowEdit(RowEditEvent event) {
		selectedFeeder = (Feeder) event.getObject();
		if (selectedFeeder != null) {
			update();
		}
		FacesMessage msg = new FacesMessage("Edited",
				((Feeder) event.getObject()).getDomain());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled",
				((Feeder) event.getObject()).getDomain());
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

	public void setFeederService(FeederService feederService) {
		this.feederService = feederService;
	}

	public FeederSearchCriteria getFilter() {
		return filter;
	}

	public void setFilter(FeederSearchCriteria filter) {
		this.filter = filter;
	}

	public Feeder getSelectedFeeder() {
		return selectedFeeder;
	}

	public void setSelectedFeeder(Feeder selectedFeeder) {
		this.selectedFeeder = selectedFeeder;
	}

	public List<Feeder> getSelectedFeeders() {
		return selectedFeeders;
	}

	public void setSelectedFeeders(List<Feeder> selectedFeeders) {
		this.selectedFeeders = selectedFeeders;
	}

	public LazyDataModel<Feeder> getFeederList() {
		return feederList;
	}

	public void setFeederList(LazyDataModel<Feeder> feederList) {
		this.feederList = feederList;
	}

	public List<SelectItem> getStatusList() {
		return statusList;
	}

	@Override
	public StatefulSearchCriteria getSearchFilter() {
		return getFilter();
	}

}
