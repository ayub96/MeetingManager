package com.qa.demo.rest;

import java.util.ArrayList;
import java.util.List;

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

@SpringBootTest(classes = SpringStarterApplication.class)	// run our application
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)	// use data-test.sql before out test methods
@ActiveProfiles(profiles = "reg")
public class PersonControllerIntegrationTest {

	@Autowired											// autowired (dependency injection) instead of creating instances
	private MockMvc mock;								// we never use "new" keyword in spring
	
	@Autowired
	private ModelMapper mapper;							// maps from domain to DTO, lives in service class
														// this was declared in application config
		
	@Autowired
	private ObjectMapper jsonifier;						// Translates request from string of json format to a java class, helps construct requests
														// inherently known in spring
	
	private PersonDTO mapToDTO(Person model) {			// takes in person domain and returns person DTO
		return this.mapper.map(model, PersonDTO.class);
	}
	
	private final int TEST_ID = 1;
	
//	private final Person testPerson = new Person(1L, "first", "sur", "email");
	
	
	//=================================== TESTS ===================================
	
	// CREATE
	
	@Test
	public void deletePerson() throws Exception{
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.DELETE, "/person/delete/" + 1L);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();
		
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
	
	@Test
	public void createPerson() throws Exception{
	
		Person TEST_PERSON = new Person("John", "Smith", "john@gmail.com");
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "/person/create/")
				.contentType(MediaType.APPLICATION_JSON)	// the type of test like in postman
				.content(this.jsonifier.writeValueAsString(TEST_PERSON))	// translates a value into JSON, we dont need a body for read therefore comment out
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks

//		final Person TEST_PERSON_SAVED = new Person(2L, TEST_PERSON.getFirstname(), TEST_PERSON.getSurname(), TEST_PERSON.getEmail());
		
		TEST_PERSON.setId(13L);
		
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(this.mapToDTO(TEST_PERSON)));	// content we get back
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		
		// Perform & Assert
		this.mock.perform(mockRequest)	// perform out API request we set up
			.andExpect(matchStatus)
			.andExpect(matchContent);
	}
	
	// READ
	
	@Test
	public void readPerson() throws Exception{
		
		PersonDTO TEST_PERSON = new PersonDTO(1L, "John", "Smith", "john@gmail.com", 1L);	// needs to match the response of out API request
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/person/read/"+TEST_ID)
					.contentType(MediaType.APPLICATION_JSON)	// the type of test like in postman
//					.content(this.jsonifier.writeValueAsString(value))	// translates a value into JSON, we dont need a body for read therefore comment out
					.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_PERSON));	// content we get back
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		// Perform & Assert
		this.mock.perform(mockRequest)	// perform out API request we set up
			.andExpect(matchStatus)
			.andExpect(matchContent);
			
		
	}
	
	@Test
	public void readAllPeople() throws Exception{
		List<PersonDTO> personList = new ArrayList<>();
		personList.add(new PersonDTO(1L, "fname", "sname", "email"));
		personList.add(new PersonDTO(2L, "fname2", "sname2", "sname2"));
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.GET, "/person/readAll")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchContent = MockMvcResultMatchers.content()
				.json(this.jsonifier.writeValueAsString(personList));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	// UPDATE
	
	@Test
	public void updatePerson() throws Exception {
		PersonDTO person = new PersonDTO(1L, "peter", "jackson", "peter@gmail.com");
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "/person/update/" + TEST_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(person))
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchContent = MockMvcResultMatchers.content()
				.json(this.jsonifier.writeValueAsString(person));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);	
	}
	
	// DELETE
	

}
