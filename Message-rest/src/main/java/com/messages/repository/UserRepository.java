package com.messages.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.messages.modals.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	User findByName(String name);
	
}
