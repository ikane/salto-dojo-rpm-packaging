package com.saltoconsulting.dojo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saltoconsulting.dojo.web.service.HelloWorldService;

@RestController
public class HelloWorldController {

	@Autowired
	private HelloWorldService helloWorldService;

	@RequestMapping("/")
	public String welcome(
			@RequestParam(required = false, value = "who") String who) {
		return this.helloWorldService.hello(who);
	}
	
}
