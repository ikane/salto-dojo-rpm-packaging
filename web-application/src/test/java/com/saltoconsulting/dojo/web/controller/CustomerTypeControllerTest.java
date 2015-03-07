package com.saltoconsulting.dojo.web.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.saltoconsulting.dojo.customer.domain.CustomerType;
import com.saltoconsulting.dojo.customer.service.CustomerTypeQueryService;

@RunWith(MockitoJUnitRunner.class)
public class CustomerTypeControllerTest extends AbstractRestControllerTest {

	@InjectMocks
	protected CustomerTypeController controller;
	
	@Override
	public BaseRestController controller() {
		return controller;
	}
	
	@Mock
	protected CustomerTypeQueryService query;
	
	protected Iterable<CustomerType> customerTypes;
	protected CustomerType type1, type2;
	
	@Before
	@Override
	public void setup() {
		super.setup();
		type1 = new CustomerType();
		type1.setId(1);
		type1.setName("type1");
		
		type2 = new CustomerType();
		type2.setId(2);
		type2.setName("type2");
		
		customerTypes = Arrays.asList(type1, type2);
	}
	
	@Test
	public void should_find_customer_types() throws Exception {
		when(query.findAll()).thenReturn(customerTypes);
		
		mockMvc.perform(get("/customerType"))
			.andExpect(status().isOk())
			
			.andExpect(jsonPath("$", hasSize(2)))
			
			.andExpect(jsonPath("$[0].id", is(1)))
			.andExpect(jsonPath("$[0].name", is("type1")))
			
			.andExpect(jsonPath("$[1].id", is(2)))
			.andExpect(jsonPath("$[1].name", is("type2")));
	}
	
	@Test
	public void should_not_find_anything() throws Exception {
		when(query.findAll()).thenReturn(new ArrayList<CustomerType>());
		
		mockMvc.perform(get("/customerType"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void should_find_one_customer_type() throws Exception {
		when(query.findById(1)).thenReturn(type1);
		
		mockMvc.perform(get("/customerType/1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.name", is("type1")));
	}

	@Test
	public void should_not_find_customer_type() throws Exception {
		when(query.findById(0)).thenReturn(null);
		
		mockMvc.perform(get("/customerType/1"))
			.andDo(print())
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void should_have_code_500_on_uncatched_exceptions() throws Exception {
		when(query.findAll()).thenThrow(new RuntimeException());
		
		mockMvc.perform(get("/customerType"))
			.andExpect(status().is5xxServerError());	
	}
	
}
