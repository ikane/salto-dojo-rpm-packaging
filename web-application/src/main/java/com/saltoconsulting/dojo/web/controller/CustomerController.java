package com.saltoconsulting.dojo.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.saltoconsulting.dojo.customer.domain.Customer;
import com.saltoconsulting.dojo.customer.exception.NoResultException;
import com.saltoconsulting.dojo.customer.service.CustomerCommandService;
import com.saltoconsulting.dojo.customer.service.CustomerQueryService;
import com.saltoconsulting.dojo.web.json.CustomerJSON;

@RestController
@Transactional(rollbackOn = Exception.class)
@RequestMapping(value = "/customer", produces = { "application/json", "application/xml" })
public class CustomerController extends BaseRestController {

	@Autowired
	protected CustomerQueryService query;

	@Autowired
	protected CustomerCommandService command;

	@RequestMapping(method = RequestMethod.GET)
	public List<CustomerJSON> all() {
		Iterable<Customer> all = query.findAll();
		List<CustomerJSON> allDTO = new ArrayList<CustomerJSON>();
		if(all != null) {
			for (Customer customer : all) {
				CustomerJSON json = mapper.map(customer, CustomerJSON.class);
				allDTO.add(json);
			}
		}
		return allDTO;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CustomerJSON byId(@PathVariable Integer id) {
		Customer customer = query.findById(id);
		if (customer == null) {
			throw new NoResultException();
		}
		return mapper.map(customer, CustomerJSON.class);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public CustomerJSON update(@PathVariable Integer id, @RequestBody CustomerJSON customerJson) {
		Customer customer = query.findById(id);
		if (customer == null) {
			throw new NoResultException();
		}
		Customer customerUpdate = mapper.map(customerJson, Customer.class);
		customerUpdate.setId(customer.getId());
		Customer customerUpdated = command.save(customerUpdate);
		return mapper.map(customerUpdated, CustomerJSON.class);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public CustomerJSON create(@RequestBody CustomerJSON customerJson) {
		Customer customer = mapper.map(customerJson, Customer.class);
		Customer customerSaved = command.create(customer);
		return mapper.map(customerSaved, CustomerJSON.class);
	}

}
