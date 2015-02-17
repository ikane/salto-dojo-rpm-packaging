package com.saltoconsulting.dojo.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWorldApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldApplication.class); 

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}

	@Value("${who:World}")
	protected String user;
	
	public void run(String... args) throws Exception {
		LOGGER.info("Hello {}", user);
	}

}
