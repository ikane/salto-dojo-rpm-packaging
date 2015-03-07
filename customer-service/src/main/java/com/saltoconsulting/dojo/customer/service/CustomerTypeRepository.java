package com.saltoconsulting.dojo.customer.service;

import org.springframework.data.repository.CrudRepository;

import com.saltoconsulting.dojo.customer.domain.CustomerType;

public interface CustomerTypeRepository extends CrudRepository<CustomerType, Integer> {

}
