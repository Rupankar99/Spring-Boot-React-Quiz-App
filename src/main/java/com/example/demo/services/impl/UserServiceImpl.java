package com.example.demo.services.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.User;
import com.example.demo.models.UserRole;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	
	@Override
	public User createUser(User user, Set<UserRole> roles) throws Exception {
		User localUser = userRepo.findByUsername(user.getUsername());
		
		if(localUser != null) {
			System.out.println("User already present");
			throw new Exception("User already present");
		}
		else {
			for(UserRole userrole : roles) {
				roleRepo.save(userrole.getRole());
			}
			
			user.getRoles().addAll(roles);
			localUser = this.userRepo.save(user);
		}
		
		return localUser;
	}


	@Override
	public User getUser(String username) {
		return this.userRepo.findByUsername(username);
	}


	@Override
	public void deleteUser(Long id) {
		this.userRepo.deleteById(id);
	}


	@Override
	public User editUser(Long id, User user) {
		User localUser = userRepo.findById(id)
		.orElseThrow(()-> new ResourceNotFoundException("User not found !!"));
		
		localUser.setUsername(user.getUsername());
		localUser.setFirstName(user.getFirstName());
		localUser.setLastName(user.getLastName());
		localUser.setEmail(user.getEmail());
		localUser.setMobno(user.getMobno());
		
		this.userRepo.save(localUser);
		return localUser;
	}
	
}
