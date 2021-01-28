package com.qa.rest;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.persistence.domain.Room;
import com.qa.persistence.dto.RoomDTO;
import com.qa.services.RoomService;

@RestController
@CrossOrigin
@RequestMapping("/room")
public class RoomController {

	private RoomService service;
	
	@Autowired
	public RoomController(RoomService service) {
		super();
		this.service = service;
	}
	
    @PostMapping("/create")
    public ResponseEntity<RoomDTO> createRoom(@RequestBody Room model) {
        return new ResponseEntity<RoomDTO>(this.service.create(model), HttpStatus.CREATED);
    }
    
    @GetMapping("/readAll")
    public ResponseEntity<List<RoomDTO>> readAllRooms(){
    	return ResponseEntity.ok(this.service.readAll());
    }
    
    @GetMapping("/read/{id}")
    public ResponseEntity<RoomDTO> readRoom(@PathVariable("id") Long id) {
    	return ResponseEntity.ok(this.service.read(id));
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable("id") Long id, @RequestBody Room model) {
    	return new ResponseEntity<>(this.service.update(id, model), HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RoomDTO> deleteRoom(@PathVariable("id") Long id) {
    	return this.service.delete(id) ?
    			new ResponseEntity<>(HttpStatus.NO_CONTENT) :
    			new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
