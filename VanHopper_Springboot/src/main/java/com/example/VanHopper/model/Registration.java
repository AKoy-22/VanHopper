package com.example.VanHopper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Registration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "event_id", nullable = false)
	// @JsonIgnore
	////@JsonProperty("event") 
	@JsonUnwrapped(prefix = "event.")
	private Event event;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	// @JsonIgnore  // Important this doesn't allow the post mapping to happen because it ignores the setting into json format
	////?@JsonProperty("user") 
	@JsonUnwrapped(prefix = "user.")
	private User user;

	public Registration() {

	}

	public Registration(User user, Event event) {
		this.user = user;
        this.event = event;
		user.getRegistrations().add(this);
	    event.getRegistrations().add(this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	@Override
//	public String toString() {
//		//return this.id + " " + this.event + " " + this.user;
//		return  this.event + " " + this.user.getId()+"printing in regiter class";
//	}
}
