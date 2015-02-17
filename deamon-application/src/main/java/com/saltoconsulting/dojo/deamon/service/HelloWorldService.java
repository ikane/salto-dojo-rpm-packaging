package com.saltoconsulting.dojo.deamon.service;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

	public String hello() {
		return hello("World");
	}
	
	public String hello(String who) {
		return "Hello " + who;
	}
	
}
