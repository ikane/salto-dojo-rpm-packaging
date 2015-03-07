package com.saltoconsulting.dojo.customer.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.saltoconsulting.dojo.customer.CustomerServiceConfig;
import com.saltoconsulting.dojo.customer.domain.CustomerType;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringApplicationConfiguration(classes = { CustomerServiceConfig.class })
public class CustomerTypeServiceTest {

	@Autowired
	private CustomerTypeQueryService query;
	
	@Autowired
	private CustomerTypeCommandService command;

	@Test
	public void should_find_all_customer_types() {
		Collection<CustomerType> allTypes = (Collection<CustomerType>) query.findAll();
		assertThat(allTypes, hasSize(2));
	}
	
	@Test
	public void should_add_customer_type() {
		CustomerType alien = new CustomerType();
		alien.setId(3);
		alien.setName("Alien");
		
		command.save(alien);
		
		long count = query.countAll();
		assertEquals(3, count); // The first two ids are inserted by liquibase
	}
	
	@Test
	public void should_update_customer_types() {
		CustomerType particulier = new CustomerType();
		particulier.setId(1);
		particulier.setName("ParticulierUP");
		
		CustomerType professional = new CustomerType();
		professional.setId(2);
		professional.setName("ProfessionnelUP");
		
		command.save(particulier, professional);
		
		long count = query.countAll();
		assertEquals(2, count);
		
		Iterable<CustomerType> allTypes = query.findAll();
		assertThat(allTypes, hasItems(particulier, professional));
		
	}
	
}
