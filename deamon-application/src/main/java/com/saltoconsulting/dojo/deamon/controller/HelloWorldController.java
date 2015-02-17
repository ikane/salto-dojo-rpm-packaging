package com.saltoconsulting.dojo.deamon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saltoconsulting.dojo.deamon.service.HelloWorldService;

@Controller
public class HelloWorldController {

	@Autowired
	private HelloWorldService helloWorldService;

	@RequestMapping("hello")
	@ResponseBody
	public String helloWorld(
			@RequestParam(defaultValue = "World", required = false, value = "who") String who) {
		return this.helloWorldService.hello(who);
	}

}
