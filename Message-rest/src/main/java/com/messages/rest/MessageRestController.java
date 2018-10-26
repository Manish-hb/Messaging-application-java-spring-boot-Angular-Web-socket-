package com.messages.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.messages.modals.Message;
import com.messages.service.MessageService;

@CrossOrigin
@RestController
public class MessageRestController {

	@Autowired
	MessageService messageService;
	
	@RequestMapping(value="/messages/send",method=RequestMethod.POST)
	public Message sendMessage(@RequestBody Message message){
		//System.out.println();
		return messageService.saveMessage(message);
	}
	
	@RequestMapping(value="/messages/{ccId:.+}",method=RequestMethod.GET)
	public List<Message> getMessages(@PathVariable("ccId") String ccId){
		return messageService.getMessages(ccId);
	}
	
}
