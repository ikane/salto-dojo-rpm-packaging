package com.saltoconsulting.dojo.customer.service;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saltoconsulting.dojo.customer.domain.CustomerType;

@Service
@Transactional(rollbackOn = Exception.class)
public class CustomerTypeCommandService {

	@Autowired
	private CustomerTypeRepository repository;

	public Iterable<CustomerType> save(CustomerType... types) {
		return repository.save(Arrays.asList(types));
	}
	
}
