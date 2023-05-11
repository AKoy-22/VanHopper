package com.example.VanHopper.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
	
	Optional<Registration> findById(Long id);
	List <Registration> findByEventId(Long id);
	List<Registration> findByUserId(Long id);
	
	
	//creating this method to get a specific user associated with particular event id
    List<Registration> findByEventIdAndUserId(Long uid, Long eid);
}
