package com.example.VanHopper.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface EventRepository extends JpaRepository<Event, Long> {
	Optional<Event> findById(Long id);
	
	List<Event> findByName(String name);  
	List<Event> findByLocation(String location);
	List<Event> findByDate(int date); 
	List<Event> findByCategory(String category); 
	//List<Event> findByLink(String link); 
	List<Event> findByHosts(String hosts); 
	

}
