package com.qa.demo.rest;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.SpringStarterApplication;
import com.qa.persistence.domain.Person;
import com.qa.persistence.dto.PersonDTO;

@SpringBootTest(classes = SpringStarterApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "reg")
public class CustomerControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;	// maps from domain to DTO
	
	@Autowired
	private ObjectMapper jsonifier;	// Translates request from string of json format to a java class
	
	private PersonDTO mapToDTO(Person model) {
		return this.mapper.map(model, PersonDTO.class);
	}
	
	private final int TEST_ID = 1;
	
	//=================================== TESTS ===================================
	
	// CREATE
	
	public void createCust() {
		
	}
	
	// READ
	
	@Test
	public void readCust() throws Exception{
		
		PersonDTO TEST_CUST = new PersonDTO(1L, "John", "Smith", "john@gmail.com");
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/customer/get/"+TEST_ID)
					.contentType(MediaType.APPLICATION_JSON)
//					.content(this.jsonifier.writeValueAsString(value))
					.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_CUST));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		// Perform & Assert
		this.mock.perform(mockRequest)
			.andExpect(matchStatus)
			.andExpect(matchContent);
			
		
	}
	
	@Test
	public void readAllCust() {
		
	}
	
	// UPDATE
	
	@Test
	public void updateCust() {
		
	}
	
	// DELETE
	
	@Test
	public void deleteCust() {
		
	}
}
