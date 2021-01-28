package com.qa.demo.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.SpringStarterApplication;
import com.qa.persistence.domain.Room;
import com.qa.persistence.dto.RoomDTO;
import com.qa.persistence.repo.RoomRepo;
import com.qa.services.RoomService;

@SpringBootTest(classes = SpringStarterApplication.class)
public class AppointmentServiceUnitTest {

	@Autowired
	private RoomService service;
	
	@MockBean
	private RoomRepo repoMock;
	
	@Test
	public void createTest() {
		// Rules
		Mockito.when(this.repoMock.save(Mockito.any(Room.class))).thenReturn(new Room());
		// Action
		RoomDTO result = this.service.create(new Room());
		// Assertions & Verify
		Assertions.assertThat(result).isInstanceOf(RoomDTO.class);
		Mockito.verify(this.repoMock, Mockito.times(1)).save(new Room());
	}
	
}
