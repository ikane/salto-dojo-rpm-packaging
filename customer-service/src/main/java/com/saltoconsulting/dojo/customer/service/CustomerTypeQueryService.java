package com.saltoconsulting.dojo.customer.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saltoconsulting.dojo.customer.domain.CustomerType;

@Service
@Transactional(rollbackOn = Exception.class)
public class CustomerTypeQueryService {

	@Autowired
	private CustomerTypeRepository repository;

	public CustomerType findById(int id) {
		return repository.findOne(id);
	}
	
	public Iterable<CustomerType> findAll() {
		return repository.findAll();
	}

	public long countAll() {
		return repository.count();
	}
	
}
