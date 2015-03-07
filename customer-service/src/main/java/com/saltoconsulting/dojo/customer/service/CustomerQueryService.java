package com.saltoconsulting.dojo.customer.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saltoconsulting.dojo.customer.domain.Customer;

@Service
@Transactional(rollbackOn = Exception.class)
public class CustomerQueryService {

	@Autowired
	private CustomerRepository repository;

	public Customer findById(int id) {
		return repository.findOne(id);
	}

	public Iterable<Customer> findAll() {
		return repository.findAll();
	}

	public long countAll() {
		return repository.count();
	}

}
