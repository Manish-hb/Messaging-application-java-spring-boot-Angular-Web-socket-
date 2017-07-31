package com.messages.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.messages.modals.CommunicationChannel;
import com.messages.modals.CommunicationChannelDTO;
import com.messages.modals.User;
import com.messages.service.CommunicationChannelService;

@CrossOrigin
@RestController
public class CommunicationChannelRestController {

	@Autowired
	private CommunicationChannelService ccService;
	
	@RequestMapping(value="/comunicationChannel",method=RequestMethod.GET)
	public List<CommunicationChannel> findAll(){
		return ccService.findAllCommunicationChannel();
	}

	@RequestMapping(value="/comunicationChannelSorted",method=RequestMethod.GET)
	public List<CommunicationChannel> findSortedCommunicationChannel(){
		return ccService.allSortedCommunicationChannel();
	}
	
	@RequestMapping(value="/comunicationChannelSorted/{userId:.+}",method=RequestMethod.GET)
	public List<CommunicationChannelDTO> usersSortedCommunicationChannel(@PathVariable("userId") String userId){
		return ccService.usersSortedCommunicationChannel(userId);
	}
	
	@RequestMapping(value="/comunicationChannel/{userId:.+}/allConnected",method=RequestMethod.GET)
	public List<User> findAllConnectedUsers(@PathVariable("userId") String userId){
		return ccService.findAllConnectedUsers(userId);
	}
	
	@RequestMapping(value="/comunicationChannel/{userId:.+}/createGroup",method=RequestMethod.POST)
	public CommunicationChannel createGroupCommunicationChannel(@RequestBody CommunicationChannel cc,@PathVariable("userId") String luserId){
		
		return ccService.createCommunicationChannelGroup(cc,luserId);
	}
	
	@RequestMapping(value="/comunicationChannel/{userId:.+}/groupMembers/{ccId:.+}",method=RequestMethod.GET)
	public List<User> findGroupMembers(@PathVariable("userId") String userId,@PathVariable("ccId") String ccId){
		
		return ccService.findGroupMembers(ccId);
	}
	
}
