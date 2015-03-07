package com.saltoconsulting.dojo.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saltoconsulting.dojo.customer.domain.CustomerType;
import com.saltoconsulting.dojo.customer.exception.NoResultException;
import com.saltoconsulting.dojo.customer.service.CustomerTypeQueryService;
import com.saltoconsulting.dojo.web.json.CustomerTypeJSON;

@RestController
@Transactional
@RequestMapping(value = "/customerType", produces = { "application/json", "application/xml" })
public class CustomerTypeController extends BaseRestController {

	@Autowired
	protected CustomerTypeQueryService query;
	
	@RequestMapping
	public List<CustomerTypeJSON> all() {
		Iterable<CustomerType> all = query.findAll();
		List<CustomerTypeJSON> allDTO = new ArrayList<CustomerTypeJSON>();
		for (CustomerType customerType : all) {
			CustomerTypeJSON dto = new CustomerTypeJSON();
			mapper.map(customerType, dto);
			allDTO.add(dto);
		}
		return allDTO;
	}
	
	@RequestMapping("/{id}")
	public CustomerTypeJSON byId(@PathVariable Integer id) {
		CustomerType customerType = query.findById(id);
		if(customerType == null) {
			throw new NoResultException();
		}
		CustomerTypeJSON jsonCustomerType = new CustomerTypeJSON(); 
		mapper.map(customerType, jsonCustomerType);
		return jsonCustomerType;
	}
	
}
