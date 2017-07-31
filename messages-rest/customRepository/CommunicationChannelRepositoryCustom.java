package com.messages.customRepository;

import java.util.List;

import com.messages.modals.CommunicationChannel;

public interface CommunicationChannelRepositoryCustom {

	
	public List<CommunicationChannel> allSortedCommunicationChannel() ;
	
	public List<CommunicationChannel> usersSortedCommunicationChannel(String userId);
	
}
