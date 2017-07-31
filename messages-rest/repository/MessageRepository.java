package com.messages.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.messages.modals.Message;

public interface MessageRepository extends MongoRepository<Message, String> {

	List<Message> findByCcId(String ccid);
}
