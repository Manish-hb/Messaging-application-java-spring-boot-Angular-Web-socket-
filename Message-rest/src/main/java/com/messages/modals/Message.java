package com.messages.modals;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="messages")
public class Message extends BaseEntity{
	
	Contact fromUser;
	String toUserId;
	String ccId;
	String message;
	
	public Message(){
		
	}
	
	public Message(Contact fromUser, String toUserId, String ccId, String message) {
		super();
		this.fromUser = fromUser;
		this.toUserId = toUserId;
		this.ccId = ccId;
		this.message = message;
	}
	
	public Contact getFromUser() {
		return fromUser;
	}

	public void setFromUser(Contact fromUser) {
		this.fromUser = fromUser;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getCcId() {
		return ccId;
	}
	public void setCcId(String ccId) {
		this.ccId = ccId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
