package com.example.demo;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
    @Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}
	/*@Bean
	CommandLineRunner run(UserService userService){
	return args -> {
	   userService.saveRole(new Role(null,"ROLE_USER"));
	   userService.saveRole(new Role(null,"ROLE_MANAGER"));
	   userService.saveRole(new Role(null,"ROLE_ADMIN"));
	   userService.saveRole(new Role(null,"ROLE_SUPE_ADMIN"));

	   userService.saveUser(new User(null,"farouk bouhaka", "bhk","1234", new ArrayList<>()));
		userService.saveUser(new User( null,"rahmouni bouhaka", "bhk1","12341", new ArrayList<>()));
		userService.saveUser(new User(null, "karim bouhaka", "bhk2","12342", new ArrayList<>()));
		userService.saveUser(new User( null,"houssam bouhaka", "bhk3","12343", new ArrayList<>()));

		userService.addRoleToUser("ROLE_USER","bhk1");
		userService.addRoleToUser("ROLE_ADMIN","bhk");
		userService.addRoleToUser("ROLE_USER","bhk2");
		userService.addRoleToUser("ROLE_USER","bhk3");
	};

	};*/


}
