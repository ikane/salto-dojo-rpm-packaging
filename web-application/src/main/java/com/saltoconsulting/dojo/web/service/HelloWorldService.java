package com.saltoconsulting.dojo.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HelloWorldService {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldService.class);
	
	@Value("${who:World}")
	protected String defaultWho;
	
	public String hello() {
		return hello(defaultWho);
	}
	
	public String hello(String who) {
		if(StringUtils.isEmpty(who)) {
			return hello();
		}
		String message = "Hello " + who;
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(message);
		}
		return message;
	}
	
}
