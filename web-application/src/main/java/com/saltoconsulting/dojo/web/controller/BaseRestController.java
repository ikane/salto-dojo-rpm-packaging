package com.saltoconsulting.dojo.web.controller;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.saltoconsulting.dojo.customer.exception.NoResultException;

public class BaseRestController {

	@Autowired
	protected DozerBeanMapper mapper;
	
	@ExceptionHandler(NoResultException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handleNoResultException(Throwable ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleThrowable(Throwable ex) {
		return ex.getMessage();
	}
	
}
