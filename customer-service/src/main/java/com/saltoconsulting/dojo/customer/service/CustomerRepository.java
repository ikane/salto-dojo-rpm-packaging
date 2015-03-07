package com.saltoconsulting.dojo.customer.service;

import org.springframework.data.repository.CrudRepository;

import com.saltoconsulting.dojo.customer.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{
	
}
