package com.example.VanHopper.contolloer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.VanHopper.model.Event;
import com.example.VanHopper.model.EventRepository;
import com.example.VanHopper.model.Registration;
import com.example.VanHopper.model.RegistrationRepository;
import com.example.VanHopper.model.User;
import com.example.VanHopper.model.UserRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RegistrationController {

	@Autowired
	RegistrationRepository registerRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	EventRepository eventRepo;

	/***********************************
	 * Get all the users registered for particular event/ 
	 * Read the list of  registered users
	 **********************************************************************************/
	@GetMapping("registrations/events/{eventId}/users")
	public ResponseEntity<List<User>> getAllRegisterUsers(@PathVariable Long eventId) {

		try {

			List<Registration> registerList = registerRepo.findByEventId(eventId);
			List<User> userList = new ArrayList<>();

			for (Registration registered : registerList) {
				userList.add(registered.getUser());

			}

			System.out.println("hi" + userList);
			if (userList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(userList, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*****************************************
	 * To create a new registration
	 **********************************************************************************/
	@PostMapping("/registrations")
	public ResponseEntity<Registration> registerUser(@RequestBody Registration reg) {
		try {

			System.out.println("registration id" + reg.getId());
			System.out.println("registration event id " + reg.getEvent().getId());
			System.out.println("registartion user id " + reg.getUser().getId());
			Optional<Event> event = eventRepo.findById(reg.getEvent().getId());
			Optional<User> user = userRepo.findById((reg.getUser().getId()));
			if (event.isPresent() && user.isPresent()) {
				Registration newReg = registerRepo.save(new Registration(user.get(), event.get()));
				return new ResponseEntity<>(newReg, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*****************************************
	 * To get specific registration by event id and user id
	 **********************************************************************************/
	@GetMapping("registrations/events/{eventId}/users/{userId}")
	public ResponseEntity<Map<String, Object>> getOneRegisteredUser(@PathVariable Long eventId,
			@PathVariable Long userId) {
		try {
			List<Registration> regData = registerRepo.findByEventIdAndUserId(eventId, userId);

			if (regData.isEmpty()) {
				System.out.println("not present" + regData);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			} else {
				Map<String, Object> responseData = new HashMap<>();
				for (Registration reg : regData) {
					System.out.println("present" + reg.getUser());
					responseData.put("user", reg.getUser());
					responseData.put("event", reg.getEvent());
				}

				return new ResponseEntity<>(responseData, HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/***************************************
	 * To delete registration by event id and user id
	 **********************************************************************************/
	@DeleteMapping("registrations/events/{eventId}/users/{userId}")
	public ResponseEntity<HttpStatus> deleteRegistration(@PathVariable long eventId, @PathVariable long userId) {
		try {

			System.out.println(eventId + " " + userId);
			List<Registration> reg = registerRepo.findByEventIdAndUserId(eventId, userId);
			if (reg.isEmpty()) {
				// System.out.println("no such id exists");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {

				for (Registration r : reg) {
					// System.out.println(r+"getting the registration id of that particular
					// record"+r.getId());
					registerRepo.delete(r);
				}

				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/********************************************
	 * To delete all the registrations
	 ************************************************************************/
	@DeleteMapping("/registrations")
	public ResponseEntity<HttpStatus> deleteAllRegistrations() {
		try {

			List<Registration> reg = registerRepo.findAll();
			if (reg.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				registerRepo.deleteAll();
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
