package com.example.demo.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.models.UserRole;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//Create User
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user) throws Exception{
		Role role = new Role();
		role.setId(2L);
		role.setRoleName("USER");
		
		Set<UserRole> userRoleSet = new HashSet<>();
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		userRoleSet.add(userRole);
		
		User user1 = this.userService.createUser(user, userRoleSet);
		return new ResponseEntity<>(user1,HttpStatus.OK);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username){
		User user = this.userService.getUser(username);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id){
		this.userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User user){
		User user1 = this.userService.editUser(id, user);
		return new ResponseEntity<>(user1,HttpStatus.OK);
	}
	
}
