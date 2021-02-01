package com.qa.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
public class RoomServiceUnitTest {

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
	
	@Test
	public void readTest() {
		Room RoomTest = new Room(1L, "Test", "Test");
		Mockito.when(this.repoMock.findById(1L)).thenReturn(Optional.of(RoomTest));
		RoomDTO result = this.service.read(1L);
		RoomDTO roomDTO = new RoomDTO(1L, "Test", "Test", null);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(roomDTO);
		Mockito.verify(this.repoMock, Mockito.times(1)).findById(1L);
		
	}
	
	@Test
	public void readAllTest() {
		Mockito.when(this.repoMock.findAll()).thenReturn(new ArrayList<>());
		List<RoomDTO> result = this.service.readAll();
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(new ArrayList<>());
		Mockito.verify(this.repoMock, Mockito.times(1)).findAll();
	}
	
	@Test
	public void updateTest() {
		Room roomTest = new Room(1L, "Room Number Test", "Room Type Test");
		Room updatedRoom = new Room(1L, "Room Number Two Test", "Room Type Two Test");
		
		RoomDTO updatedDTO = new RoomDTO(1L, "Room Number Two Test", "Room Type Two Test", null);
		Mockito.when(this.repoMock.findById(1L)).thenReturn(Optional.of(roomTest));
		Mockito.when(this.repoMock.save(Mockito.any(Room.class))).thenReturn(updatedRoom);
		RoomDTO result = this.service.update(1L, updatedRoom);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(updatedDTO);
		Mockito.verify(this.repoMock, Mockito.times(1)).findById(1L);
		
	}
	
	@Test
	public void deleteTest() {
		Mockito.when(this.repoMock.existsById(1L)).thenReturn(false);
		Assertions.assertThat(this.service.delete(1L)).isTrue();
		Mockito.verify(this.repoMock, Mockito.times(1)).existsById(1L);
	}
	
	@Test
	public void deleteTestFAIL() {
		Mockito.when(this.repoMock.existsById(1L)).thenReturn(true);
		Assertions.assertThat(this.service.delete(1L)).isFalse();
		Mockito.verify(this.repoMock, Mockito.times(1)).existsById(1L);
	}
	
}
