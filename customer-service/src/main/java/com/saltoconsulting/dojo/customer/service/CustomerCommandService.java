package com.saltoconsulting.dojo.customer.service;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saltoconsulting.dojo.customer.domain.Customer;

@Service
@Transactional(rollbackOn = Exception.class)
public class CustomerCommandService {

	@Autowired
	private CustomerRepository repository;

	public Customer create(Customer customer) {
		if(customer.getId() != null) {
			throw new IllegalStateException("You must not set the field id in a customer creation.");
		}
		return this.save(customer);
	}
	
	public Iterable<Customer> create(Customer... customers) {
		for (Customer customer : customers) {
			if(customer.getId() != null) {
				throw new IllegalStateException("You must not set the field id in a customer creation.");
			}
		}
		return this.save(customers);
	}
	
	public Customer save(Customer customers) {
		return repository.save(customers);
	}
	
	public Iterable<Customer> save(Customer... customers) {
		return repository.save(Arrays.asList(customers));
	}
	
}
