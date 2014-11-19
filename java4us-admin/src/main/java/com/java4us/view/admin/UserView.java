package com.java4us.view.admin;

import com.java4us.commons.service.member.UserService;
import com.java4us.commons.utils.Clock;
import com.java4us.commons.utils.criteria.StatefulSearchCriteria;
import com.java4us.commons.utils.criteria.UserSearchCriteria;
import com.java4us.domain.User;
import com.java4us.domain.common.enums.BaseStatus;
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

@ManagedBean(name = "userView")
@ViewScoped
public class UserView extends BaseView implements
		StatefulLazyDataProdiver<User> {

	private static final long serialVersionUID = -3175922333891856768L;

	public UserSearchCriteria filter;

	@ManagedProperty(value = "#{userService}")
	private transient UserService userService;

	private User selectedUser;
	private List<User> selectedUsers;
	private LazyDataModel<User> userList;
	private List<SelectItem> statusList;

	@PostConstruct
	public void init() {
		filter = new UserSearchCriteria();
		userList = new LazyDataModel<User>(this);
		statusList = new ArrayList<>();
		for (BaseStatus statu : BaseStatus.values()) {
			statusList.add(new SelectItem(statu));
		}
	}

	@Override
	public List<User> loadLazyData(int first, int pageSize, String sortField,
			String sortOrder) {
		return userService.findUserList(first, pageSize, sortField, sortOrder,
				filter);
	}

	@Override
	public int getRowCount() {
		return userService.getRowCount(filter);
	}

	@Override
	public StatefulSearchCriteria getSearchFilter() {
		return getFilter();
	}

	private void update() {
		if (selectedUser == null) {
			addErrorMessage("error.nonselected.message");
			return;
		}
		selectedUser.setCreateDate(Clock.getTime());
		try {
			userService.saveOrUpdate(selectedUser);
		} catch (Exception e) {
			addUpdateFailedMessage(selectedUser);
			return;
		}
	}

	public void reset() {
		setFilter(new UserSearchCriteria());
	}

	public void search() {
		getSearchFilter().clear();
	}

	public void onRowEdit(RowEditEvent event) {
		selectedUser = (User) event.getObject();
		if (selectedUser != null) {
			update();
		}
		FacesMessage msg = new FacesMessage("Edited",
				((User) event.getObject()).getEmail());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled",
				((User) event.getObject()).getEmail());
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

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserSearchCriteria getFilter() {
		return filter;
	}

	public void setFilter(UserSearchCriteria filter) {
		this.filter = filter;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<User> getSelectedUsers() {
		return selectedUsers;
	}

	public void setSelectedUsers(List<User> selectedUsers) {
		this.selectedUsers = selectedUsers;
	}

	public LazyDataModel<User> getFeederList() {
		return userList;
	}

	public void setUserList(LazyDataModel<User> userList) {
		this.userList = userList;
	}

	public List<SelectItem> getStatusList() {
		return statusList;
	}

}
