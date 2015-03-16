package com.saltoconsulting.dojo.customer.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.saltoconsulting.dojo.customer.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{
	
	@Query("select c, t from Customer c join fetch c.type t where c.id = :id")
	public Customer findById(@Param("id") Integer id);
	
}
