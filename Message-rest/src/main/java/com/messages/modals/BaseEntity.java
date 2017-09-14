package com.messages.modals;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public abstract class BaseEntity {

	@Id
	String id;
	
	@DateTimeFormat(iso = ISO.DATE)
	private DateTime createdTime;

	@DateTimeFormat(iso = ISO.DATE)
	private DateTime updatedTime;
	
	public BaseEntity(){
		this.createdTime = new DateTime();
		this.updatedTime = new DateTime();
	}
	

	public String getId() {
		return id;
	}
	
	public DateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(DateTime createdTime) {
		this.createdTime = createdTime;
	}

	public DateTime getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(DateTime updatedTime) {
		this.updatedTime = updatedTime;
	}
	
}
