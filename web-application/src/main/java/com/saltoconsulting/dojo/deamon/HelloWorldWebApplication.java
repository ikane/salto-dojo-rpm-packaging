package com.saltoconsulting.dojo.deamon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class HelloWorldWebApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {
		SpringApplication.run(HelloWorldWebApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HelloWorldWebApplication.class);
	}

}
