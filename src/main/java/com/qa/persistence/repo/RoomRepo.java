package com.qa.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.persistence.domain.Room;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long>	{


	
}
