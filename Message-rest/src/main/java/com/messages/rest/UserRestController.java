package com.messages.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.messages.modals.User;
import com.messages.service.UserService;

@CrossOrigin
@RestController
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.POST,value="/users/create")
	public User createUser(@Valid @RequestBody User user){
		return userService.createUser(user);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/users")
	public List<User> getUsers(){
		
		return userService.getUsers();
	}
	
	@RequestMapping(value="/user/{name:.+}",method=RequestMethod.GET)
	public User findUserByName(@PathVariable("name") String name){
		
		User usr = userService.findUserByName(name);
		
		if(usr!=null){
			return usr;
		}
		usr = new User(name);
		return userService.createUser(usr);
	}
	
}
