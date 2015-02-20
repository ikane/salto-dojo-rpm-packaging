package com.saltoconsulting.dojo.deamon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saltoconsulting.dojo.deamon.service.HelloWorldService;

@RestController
public class HelloWorldController {

	@Autowired
	private HelloWorldService helloWorldService;

	@RequestMapping("/")
	public String welcome(
			@RequestParam(defaultValue = "man", required = false, value = "who") String who) {
		return this.helloWorldService.hello(who);
	}
	
	@RequestMapping("/hello")
	public String helloWorld(
			@RequestParam(defaultValue = "World", required = false, value = "who") String who) {
		return this.helloWorldService.hello(who);
	}

}
