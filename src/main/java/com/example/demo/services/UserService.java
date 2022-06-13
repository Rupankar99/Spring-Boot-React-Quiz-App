package com.example.demo.services;

import java.util.Set;

import com.example.demo.models.User;
import com.example.demo.models.UserRole;


public interface UserService {
	
	//Create a user
	public User createUser(User user,Set<UserRole> roles) throws Exception;
	
	//Get details of a user
	public User getUser(String username);
	
	//Delete a user
	public void deleteUser(Long id);
	
	//Update a user
	public User editUser(Long id , User user);
}
