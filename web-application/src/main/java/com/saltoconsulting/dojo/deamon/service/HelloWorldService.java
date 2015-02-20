package com.saltoconsulting.dojo.deamon.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldService.class);
	
	public String hello() {
		return hello("World");
	}
	
	public String hello(String who) {
		String message = "Hello " + who;
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(message);
		}
		return message;
	}
	
}
