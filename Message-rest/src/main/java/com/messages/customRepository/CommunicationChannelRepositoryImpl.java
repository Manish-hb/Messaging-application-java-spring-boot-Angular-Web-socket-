package com.messages.customRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;

import com.messages.modals.CommunicationChannel;

public class CommunicationChannelRepositoryImpl implements CommunicationChannelRepositoryCustom{

	@Autowired
    MongoTemplate mongoTemplate;
	
	public List<CommunicationChannel> allSortedCommunicationChannel() {
		
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC,"updtedTime"));
		
		List<CommunicationChannel> result = mongoTemplate.find(query, CommunicationChannel.class);
		return result;
		
	}
	
	
	public List<CommunicationChannel> usersSortedCommunicationChannel(String userId) {

		Query query = new Query(Criteria.where("userIds").is(userId))
				.with(new Sort(new Order(Direction.DESC,"updatedTime")));
		
		List<CommunicationChannel> result = mongoTemplate.find(query, CommunicationChannel.class);
		return result;
		
	}
}
