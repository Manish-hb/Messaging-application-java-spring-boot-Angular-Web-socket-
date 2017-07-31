package com.messages.modals;

public class Contact extends TimeEntity{

	String userId;
	
	String name;
	
	String connectionStatus;
	
	public Contact(){
		
	}
	
	public Contact(String userId, String name) {
		this.userId = userId;
		this.name = name;
	}

	public Contact(String userId, String name, String connectionStatus) {
		super();
		this.userId = userId;
		this.name = name;
		this.connectionStatus = connectionStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(String connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
	
}
