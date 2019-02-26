package edu.mum.component.impl;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import edu.mum.component.MessageOrigin;

@Component
public class HelloWorldMessageOrigin implements MessageOrigin, MessageSourceAware {
 
private MessageSource messageSource;
	
 	public String getMessage() {
 		  String msg = messageSource.getMessage("messageText",null, Locale.getDefault());
		  return msg;
		  // shouldn't we call setM..S.. before this return?
 	}
 	

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
		
	}
 }
