package com.messages.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.messages.enums.Connections;
import com.messages.modals.CommunicationChannel;
import com.messages.modals.CommunicationChannelDTO;
import com.messages.modals.Contact;
import com.messages.modals.User;
import com.messages.repository.CommunicationChannelRepository;

@Service
public class CommunicationChannelService {

	@Autowired
	private CommunicationChannelRepository ccRepo;
	
	@Autowired
	private UserService userService;
	
	public CommunicationChannel findOne(String ccId){
		return ccRepo.findOne(ccId);
	}
	
	@Async
	public CommunicationChannel updateDateTime(String ccId){
		CommunicationChannel cc = findOne(ccId);
		cc.setUpdatedTime(new DateTime());
		return ccRepo.save(cc);
	}
	
	public CommunicationChannel createCommunicationChannelOneToOne(User fromUser,User toUser){
		
		CommunicationChannel cc = new CommunicationChannel();
		cc.getUserIds().add(fromUser.getId());
		cc.getUserIds().add(toUser.getId());
		
		return ccRepo.save(cc);
	}
	
	public CommunicationChannel createCommunicationChannelGroup(CommunicationChannel comChannel,String luserId){
		
		comChannel.getUserIds().add(luserId);
		
		return ccRepo.save(comChannel);
	}
	
	public List<CommunicationChannel> findAllCommunicationChannel(){
		return ccRepo.findAll();
	}
	
	public List<CommunicationChannel> allSortedCommunicationChannel(){
		return ccRepo.allSortedCommunicationChannel();
	}
	
	public List<CommunicationChannelDTO> usersSortedCommunicationChannel(String lognedInUserId){
		
		List<CommunicationChannel> ccList= ccRepo.usersSortedCommunicationChannel(lognedInUserId);
		
		List<CommunicationChannelDTO> ccDTOList= new ArrayList<CommunicationChannelDTO>();
		
		for(CommunicationChannel cc : ccList){
			
			CommunicationChannelDTO dto = new CommunicationChannelDTO();
			
			dto.setId(cc.getId());
			dto.setGroup(cc.isGroup());
			dto.setGroupName(cc.getGroupName());
			
			if(!cc.isGroup()){
				
				for(String userId : cc.getUserIds()){
					
					if(!userId.equals(lognedInUserId)){
						
						User usr = userService.findOne(userId);
						
						dto.setContact(new Contact(userId,usr.getName()));
					}
					
				}
				
			}
			
			ccDTOList.add(dto);
		}
		
		return ccDTOList;
	}
	
	public List<User> findAllConnectedUsers(String lognedInUserId){
		
		List<CommunicationChannel> ccList= ccRepo.usersSortedCommunicationChannel(lognedInUserId);
		
		List<User> users= new ArrayList<User>();
		
		for(CommunicationChannel cc : ccList){
			
			if(!cc.isGroup()){
				
				for(String userId : cc.getUserIds()){
					
					if(!userId.equals(lognedInUserId)){
						
						User usr = userService.findOne(userId);
						
						if(usr!=null){
							users.add(usr);
						}
						
					}
					
				}
				
			}
			
		}
		return users;
	}

	public List<User> findGroupMembers(String ccId) {
		
		CommunicationChannel cc = ccRepo.findOne(ccId);
		
		List<User> users = new ArrayList<User>(); 
		
		if(cc.isGroup()){
			
			for(String userId : cc.getUserIds()){
				
				User usr = userService.findOne(userId);
				
				if(usr!=null){
					users.add(usr);
				}
			}
			
		}
		
		return users;
	}
}
