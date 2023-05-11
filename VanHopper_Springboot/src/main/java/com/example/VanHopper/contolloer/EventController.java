package com.example.VanHopper.contolloer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.VanHopper.model.Event;
import com.example.VanHopper.model.EventRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EventController {

	@Autowired
	EventRepository eventRepo;

	@GetMapping("/events")
	public ResponseEntity<List<Event>> getAllEvents(@RequestParam(required = false) String name) {
		try {
			List<Event> events = new ArrayList<Event>();

			if (name == null) {
				eventRepo.findAll().forEach(events::add);
			} else {
				eventRepo.findByName(name).forEach(events::add);
			}
			if (events.isEmpty()) {
				// corrected to passed events
				return new ResponseEntity<>(events, HttpStatus.NO_CONTENT);
			} // corrected to pass events
			return new ResponseEntity<>(events, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/events")
	public ResponseEntity<Event> createEvent(@RequestBody Event event) { 
		try {
			Event _event = eventRepo.save(new Event(event.getName(), event.getLocation(), event.getDate(),
					event.getCategory(), event.getHosts(), event.getLink(), event.getArea()));
			
			return new ResponseEntity<>(_event, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/events/{id}")
	public ResponseEntity<Event> updateEvent(@PathVariable("id") long id, @RequestBody Event event) {
		Optional<Event> eventsData = eventRepo.findById(id);
		if (eventsData.isPresent()) {
			Event _event = eventsData.get();
			_event.setName(event.getName());
			_event.setLocation(event.getLocation());
			_event.setDate(event.getDate());
			_event.setCategory(event.getCategory());
			_event.setHosts(event.getHosts());
			_event.setLink(event.getLink());
			_event.setArea(event.getArea());
			return new ResponseEntity<>(eventRepo.save(_event), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/events/{id}")
	public ResponseEntity<HttpStatus> deleteEvent(@PathVariable("id") long id) {
		try {
			eventRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/events")
	public ResponseEntity<HttpStatus> deleteAllEvents() {
		try {
			eventRepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
