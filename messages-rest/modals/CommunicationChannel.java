package com.messages.modals;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="communicationChannel")
public class CommunicationChannel extends BaseEntity{
	
	List<String> userIds = new ArrayList<String>();
	
	boolean isGroup;
	String groupName;

	public List<String> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}
	public boolean isGroup() {
		return isGroup;
	}
	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
