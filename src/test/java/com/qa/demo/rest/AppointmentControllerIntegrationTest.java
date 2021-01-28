package com.qa.demo.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
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
import com.qa.persistence.domain.Room;
import com.qa.persistence.domain.Person;
import com.qa.persistence.dto.RoomDTO;
import com.qa.persistence.dto.PersonDTO;

@SpringBootTest(classes = SpringStarterApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles(profiles = "reg")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AppointmentControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;	// maps from domain to DTO
	
	@Autowired
	private ObjectMapper jsonifier;	// Translates request from string of json format to a java class
	
	private RoomDTO mapToDTO(Room model) {
		return this.mapper.map(model, RoomDTO.class);
	}
	
	private final int TEST_ID = 1;
	
	//=================================== TESTS ===================================
	
	// CREATE
	
	@Test
	public void createApp() throws Exception{
		Room TEST_APP_DOMAIN = new Room(1L, "Haircut", "2000-01-01 00:00:00");
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/appointment/create/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(this.jsonifier.writeValueAsString(TEST_APP_DOMAIN))
					.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDTO(TEST_APP_DOMAIN)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		
		// Perform & Assert
		this.mock.perform(mockRequest)
			.andExpect(matchStatus)
			.andExpect(matchContent);
	}
	
	// READ
	
	@Test
	public void readApp() throws Exception{

		RoomDTO TEST_APP = new RoomDTO(5L, "Haircut", "2000-01-01 00:00:00");
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/appointment/get/"+TEST_ID)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_APP));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		// Perform & Assert
		this.mock.perform(mockRequest)
			.andExpect(matchStatus)
			.andExpect(matchContent);
			
		
	}
	
	@Test
	public void readAllApp() throws Exception{
		
		List<RoomDTO> TEST_LIST_OF_APP = new ArrayList<>();
//		TEST_LIST_OF_APP.add(new AppointmentDTO(1L, ))
		
		
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/appointment/getAll/")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_APP));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		// Perform & Assert
		this.mock.perform(mockRequest)
			.andExpect(matchStatus)
			.andExpect(matchContent);
	}

//	@Test
//	public void updateApp() throws Exception{
//		AppointmentDTO TEST_APP = new AppointmentDTO(5L, "Haircut", "2000-01-01 00:00:00");
//		
//		AppointmentDTO NEW_APP = new AppointmentDTO(1L, "Dry Cut", "2000-01-01 00:00:00");
//		Appointment UPDATED_APP = new Appointment(TEST_APP.getId(), TEST_APP.getAppointmentType(), TEST_APP.getDatePlaced());
//		
//		// Prepared REST request
//		MockHttpServletRequestBuilder mockRequest = 
//				MockMvcRequestBuilders.request(HttpMethod.PUT, "/appointment/update/"+TEST_ID)
//					.contentType(MediaType.APPLICATION_JSON)
//					.content(this.jsonifier.writeValueAsString(TEST_APP_DOMAIN))
//					.accept(MediaType.APPLICATION_JSON);
//		
//		// Assertion checks
//		
//		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDTO(TEST_APP_DOMAIN)));
//		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
//		
//		// Perform & Assert
//		this.mock.perform(mockRequest)
//			.andExpect(matchStatus)
//			.andExpect(matchContent);
//	}
	
	// DELETE
	
//	@Test
//	public void deleteApp() {
//		
//	}
}
