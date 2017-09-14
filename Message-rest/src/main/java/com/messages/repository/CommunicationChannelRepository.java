package com.messages.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.messages.customRepository.CommunicationChannelRepositoryCustom;
import com.messages.modals.CommunicationChannel;

public interface CommunicationChannelRepository extends MongoRepository<CommunicationChannel, String>, CommunicationChannelRepositoryCustom {
	
}
