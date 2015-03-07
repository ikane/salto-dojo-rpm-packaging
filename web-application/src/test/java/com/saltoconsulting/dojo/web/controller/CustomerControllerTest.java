package com.saltoconsulting.dojo.web.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;

import com.saltoconsulting.dojo.customer.domain.Customer;
import com.saltoconsulting.dojo.customer.service.CustomerCommandService;
import com.saltoconsulting.dojo.customer.service.CustomerQueryService;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest extends AbstractRestControllerTest {

	@InjectMocks
	protected CustomerController controller;
	
	@Override
	public BaseRestController controller() {
		return controller;
	}
	
	@Mock
	protected CustomerQueryService query;

	@Mock
	protected CustomerCommandService command;
	
	protected Iterable<Customer> customers;
	protected Customer customer1, customer2;
	
	@Before
	public void setup() {
		super.setup();
		
		customer1 = new Customer();
		customer1.setId(1);
		customer1.setName("Rabbit");
		customer1.setFirstName("Roger");
		
		customer2 = new Customer();
		customer2.setId(2);
		customer2.setName("Bros");
		customer2.setFirstName("Mario");
		
		customers = Arrays.asList(customer1, customer2);
	}
	
	@Test
	public void should_find_all_customers() throws Exception {
		when(query.findAll()).thenReturn(customers);
		
		mockMvc.perform(get("/customer"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(2)))
			
			.andExpect(jsonPath("$[0].id", is(1)))
			.andExpect(jsonPath("$[0].name", is("Rabbit")))
			.andExpect(jsonPath("$[0].firstName", is("Roger")))
			
			.andExpect(jsonPath("$[1].id", is(2)))
			.andExpect(jsonPath("$[1].name", is("Bros")))
			.andExpect(jsonPath("$[1].firstName", is("Mario")));
	}
	
	@Test
	public void should_find_customer_by_id() throws Exception {
		when(query.findById(1)).thenReturn(customer1);
		
		mockMvc.perform(get("/customer/1"))
			.andExpect(status().isOk())
			
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.name", is("Rabbit")))
			.andExpect(jsonPath("$.firstName", is("Roger")));
	}

	@Test
	public void should_create_customer() throws Exception {
		when(command.create((Customer) any())).thenReturn(customer1);
		
		String jsonContent = jsonSerializer.writeValueAsString(customer1);
		
		mockMvc.perform(post("/customer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonContent))
			
			.andExpect(status().isOk())
			
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.name", is("Rabbit")))
			.andExpect(jsonPath("$.firstName", is("Roger")));
		
	}
	
	@Test
	public void should_return_error_when_customer_creation_failed() throws Exception {
		when(command.save((Customer) any())).thenThrow(new RuntimeException());
		
		String jsonContent = jsonSerializer.writeValueAsString(customer1);
		
		mockMvc.perform(post("/customer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonContent))
			
			.andExpect(status().is5xxServerError());
	}
	
	@Test
	public void should_return_customer_updated() throws Exception {
		when(query.findById(1)).thenReturn(customer1);
		customer2.setId(customer1.getId());
		when(command.save((Customer) any())).thenReturn(customer2);
		
		String jsonContent = jsonSerializer.writeValueAsString(customer2);
		
		mockMvc.perform(post("/customer/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonContent))
		
		.andExpect(status().isOk())
		
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.name", is(customer2.getName())))
		.andExpect(jsonPath("$.firstName", is(customer2.getFirstName())));
	}
}
