package com.saltoconsulting.dojo.web.controller;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.dozer.DozerBeanMapper;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractRestControllerTest {
	
	protected MockMvc mockMvc;
	
	protected ObjectMapper jsonSerializer = new ObjectMapper();
	
	public void setup() {
		mockMvc = standaloneSetup(controller()).build();
		controller().mapper = new DozerBeanMapper();
	}

	public abstract BaseRestController controller() ;
	
}
