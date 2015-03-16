package com.saltoconsulting.dojo.customer.service;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saltoconsulting.dojo.customer.domain.Customer;
import com.saltoconsulting.dojo.customer.domain.CustomerType;

@Service
@Transactional(rollbackOn = Exception.class)
public class CustomerCommandService {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private CustomerQueryService query;
	
	@Autowired
	private CustomerTypeQueryService customerTypeQueryService;
	
	public Customer create(Customer customer) {
		assertNullId(customer);
		assertCustomerTypeIsValid(customer.getType());
		return this.save(customer);
	}
	
	public Iterable<Customer> create(Customer... customers) {
		for (Customer customer : customers) {
			assertNullId(customer);
			assertCustomerTypeIsValid(customer.getType());
		}
		return this.save(customers);
	}
	
	public Customer save(Customer customers) {
		return repository.save(customers);
	}
	
	public Iterable<Customer> save(Customer... customers) {
		return repository.save(Arrays.asList(customers));
	}
	
	protected void assertNullId(Customer customer) {
		if(customer.getId() != null) {
			throw new IllegalStateException("You must not set the field id in a customer creation.");
		}
	}
	
	protected void assertCustomerTypeIsValid(CustomerType customerType) {
		if(customerType != null && customerType.getId() != null) {
			Integer customerTypeId = customerType.getId();
			if(!customerTypeQueryService.exist(customerTypeId)) {
				throw new IllegalStateException("Customer type " + customerTypeId + " does not exist.");
			}
			// OK
			return ;
		}
		throw new IllegalStateException("Customer type is mandatory.");
	}
}
