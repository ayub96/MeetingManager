package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.Utils.MyBeanUtils;
import com.qa.persistence.domain.Room;
import com.qa.persistence.dto.RoomDTO;
import com.qa.persistence.repo.RoomRepo;

@Service
public class RoomService {

	private RoomRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public RoomService(RoomRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private RoomDTO mapToDTO(Room model) {
		return this.mapper.map(model, RoomDTO.class);
	}
	
	public RoomDTO create(Room model) {
		return mapToDTO(this.repo.save(model));
	}
	
	public RoomDTO read(Long id) {
		return mapToDTO(this.repo.findById(id).orElseThrow());
	}
	
	public List<RoomDTO> readAll(){
		List<Room> rooms = repo.findAll();
		List<RoomDTO> roomsDTO = rooms.stream().map(this::mapToDTO).collect(Collectors.toList());
		return roomsDTO;
	}
	
	public RoomDTO update(Long id, Room room) {		
		Room updatedRoom = this.repo.findById(id).orElseThrow();
		MyBeanUtils.mergeNotNull(room, updatedRoom);
		return this.mapToDTO(this.repo.save(updatedRoom));
	}
	
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
}
