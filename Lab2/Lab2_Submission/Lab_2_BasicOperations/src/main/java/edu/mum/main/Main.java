package edu.mum.main;


import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.domain.User;
import edu.mum.service.UserService;

public class Main {
  public static void main(String[] args) {
	  
	  ApplicationContext context = new ClassPathXmlApplicationContext("context/applicationContext.xml");
	  
	  UserService userService = (UserService)context.getBean("userServiceImpl");
	  
	  User newUser = new User();
	  newUser.setFirstName("John");
	  newUser.setLastName("Doe");
	  newUser.setEmail("john@mum.edu");
	  newUser.setLastLogin(new Date(10/12/1992));
	  newUser.setAdmin(true);
	  
	  userService.save(newUser);
	  System.out.println("New user inserted to db!\n");
	  
	  
	  User u = userService.findByEmail("john@mum.edu");
	  System.out.println("\n******** USER ********");
	  System.out.println("Details obtained from the database: \n" + "User Name: " + u.getFirstName() + " " + u.getLastName());
     
  }  
  
 }