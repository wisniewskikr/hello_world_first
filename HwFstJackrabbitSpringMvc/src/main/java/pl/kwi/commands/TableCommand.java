package pl.kwi.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.kwi.entities.UserEntity;

public class TableCommand implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private List<UserEntity> users;
	private String submit;
	private String id;
	private List<String> selectedUsersIds;

	
	public List<UserEntity> getUsers() {
		return users;
	}
	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
	
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public List<String> getSelectedUsersIds() {
		if(selectedUsersIds == null) {
			selectedUsersIds = new ArrayList<String>();
		}
		return selectedUsersIds;
	}
	public void setSelectedUsersIds(List<String> selectedUsersIds) {
		this.selectedUsersIds = selectedUsersIds;
	}
		
	
}
