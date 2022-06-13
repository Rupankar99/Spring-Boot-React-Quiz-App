package com.example.demo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.models.UserRole;
import com.example.demo.services.UserService;

@SpringBootApplication
public class OnlineQuizApplication implements CommandLineRunner{

	@Autowired
	UserService userservice;
	
	public static void main(String[] args) {
		SpringApplication.run(OnlineQuizApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*System.out.println("Starting...");
		
		User user = new User();
		
		user.setFirstName("admin");
		user.setPassword("admin");
		user.setLastName("admin");
		user.setUsername("admin");
		user.setEmail("admin@gmail.com");
		user.setMobno("1234567890");
		
		Role role = new Role();
		role.setId(1L);
		role.setRoleName("ADMIN");
		
		Set<UserRole> userRoleSet = new HashSet<>();
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		userRoleSet.add(userRole);
		
		User user1 = this.userservice.createUser(user, userRoleSet);
		System.out.println(user1.getUsername());*/
	}
}
