package edu.mum.main;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import edu.mum.service.ItemService;

@Component
public class Main {

	@Autowired
	TestItemCategory testItemCategory;
	
	@Autowired
	TestUsers testUsers;

	@Autowired
	TestItems testItems;
 
	@Autowired
	TestFindItemsBySellOrBuy testFindItemsBySellOrBuy;
	
//	@Autowired
//	ItemService itemService;
	
  public static void main(String[] args) {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
    												"context/applicationContext.xml");
    applicationContext.getBean(Main.class).mainInternal(applicationContext);
  }
  
  private void mainInternal(ApplicationContext applicationContext) {
	  
    testUsers.setupUsers();
    testItems.setupItems();
    
    // Qn1. Named Query 
    testItemCategory.testItemCategory();
    
    // Qn2. JPQL Query
    testFindItemsBySellOrBuy.test();
    
//    itemService.findAll();   //check why invoking this is giving Error creating bean?

 }
  
  
  }