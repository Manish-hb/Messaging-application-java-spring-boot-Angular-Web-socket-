package com.messages.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messages.modals.Message;
import com.messages.modals.User;
import com.messages.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CommunicationChannelService ccService;

	public User findOne(String id) {
		return userRepo.findOne(id);
	}
	
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	public User createUser(User user) {
		userRepo.save(user);
		createChannelwithAllUsers(user);
		return user;
	}
	
	public void createChannelwithAllUsers(User user){
		
		List<User> allUsers = userRepo.findAll();
		
		for(User eachUser : allUsers){
			if(!eachUser.getId().equals(user.getId())){
				ccService.createCommunicationChannelOneToOne(eachUser,user);
			}
			
		}
	}

	public User findUserByName(String name) {
		
		return userRepo.findByName(name);
	}

		
}
