package com.saltoconsulting.dojo.customer.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.saltoconsulting.dojo.customer.CustomerServiceConfig;
import com.saltoconsulting.dojo.customer.domain.Customer;
import com.saltoconsulting.dojo.customer.domain.CustomerType;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringApplicationConfiguration(classes = { CustomerServiceConfig.class })
public class CustomerServiceTest {

	@Autowired
	protected CustomerQueryService query;

	@Autowired
	protected CustomerCommandService command;

	@Autowired
	protected CustomerTypeCommandService typeCommand;
	
	protected CustomerType type1;
	
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	@Before
	public void setup() {
		type1 = new CustomerType();
		type1.setId(1);
		type1.setLabel("Particulier");
		
		typeCommand.save(type1);
	}
	
	@Test
	public void should_add_and_retrieve_customer() {
		Customer quentin = new Customer();
		quentin.setName("Vandekerckhove");
		quentin.setFirstName("Quentin");
		quentin.setType(type1);

		Customer saved = command.save(quentin);

		long count = query.countAll();
		assertEquals(1, count);

		Customer customer = query.findById(saved.getId());
		assertThat(customer.getName(), equalTo("Vandekerckhove"));
		assertThat(customer.getFirstName(), equalTo("Quentin"));

		CustomerType type = customer.getType();
		assertThat(type, is(notNullValue()));
		assertThat(type.getId(), equalTo(1));
		assertThat(type.getLabel(), equalTo("Particulier"));
	}

	@Test
	public void should_add_several_customers_at_one_time() {
		Customer roger = new Customer();
		roger.setName("Rabbit");
		roger.setFirstName("Roger");
		roger.setType(type1);

		Customer jessica = new Customer();
		jessica.setName("Rabbit");
		jessica.setFirstName("Jessica");
		jessica.setType(type1);

		command.save(roger, jessica);

		long count = query.countAll();
		assertEquals(2, count);

		Iterable<Customer> allTypes = query.findAll();
		assertThat(allTypes, hasItems(roger, jessica));

	}

	@Test(expected = IllegalStateException.class)
	public void should_failed_when_customer_id_is_provided_for_creation() {
		Customer roger = new Customer();
		roger.setId(1);
		roger.setName("Rabbit");
		roger.setFirstName("RogerBis");
		roger.setType(type1);

		command.create(roger);
	}

	@Test
	public void should_not_accept_unknown_customer_type() {
		CustomerType unknownCustomerType = new CustomerType();
		unknownCustomerType.setId(42);
		unknownCustomerType.setLabel("Unknown");
		
		Customer bishop = new Customer();
		bishop.setName("Bishop");
		bishop.setFirstName("Walter");
		bishop.setType(unknownCustomerType);
		
		thrown.expect(IllegalStateException.class);
		thrown.expectMessage("does not exist");
		command.create(bishop);
	}
	
	@Test
	public void should_not_accept_undefined_customer_type() {
		Customer bishop = new Customer();
		bishop.setName("Bishop");
		bishop.setFirstName("Walter");
		
		thrown.expect(IllegalStateException.class);
		thrown.expectMessage("is mandatory");
		command.create(bishop);
	}
	
}
