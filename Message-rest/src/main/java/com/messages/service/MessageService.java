package com.messages.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.messages.modals.CommunicationChannel;
import com.messages.modals.Message;
import com.messages.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepo;
	
	@Autowired
    private SimpMessagingTemplate template;
	
	@Autowired
	private CommunicationChannelService ccService;
	
	public Message saveMessage(Message message){
		messageRepo.save(message);
		
		ccService.updateDateTime(message.getCcId());
		
		 webScoket(message);
		
		return message;
	}
	
	@Async
	public void webScoket(Message message){
		
		template.convertAndSend("/topic/user/"+message.getToUserId(), message);
		template.convertAndSend("/topic/ccId/"+message.getCcId(), message);
		
		CommunicationChannel cc = ccService.findOne(message.getCcId());
		
		if(cc.isGroup()){
			for(String userId : cc.getUserIds()){
				template.convertAndSend("/topic/user/"+userId, message);
			}
		}
		
	}

	public List<Message> getMessages(String ccId) {
		
		return messageRepo.findByCcId(ccId);
	}
	
}
