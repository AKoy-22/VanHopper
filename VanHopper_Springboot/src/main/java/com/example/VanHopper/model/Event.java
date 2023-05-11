package com.example.VanHopper.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="events")
public class Event {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Location")
	private String location;
	
	@Column(name="Area")  //added 
	private String area;
	
	@Column(name="Date")
	private String date;

	@Column(name="Category")
	private String category;
	
	@Column(name="Host")
	@Value("${hosts}")
	private List<String> hosts; 
	
	@Column(name="Link")
	private String link; 
	
	@OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Registration> registrations = new HashSet<>();
	
	public Event() {}


	public Event(String name, String location, String date, String category, List<String> hosts, String link, String area) {
		
		this.name = name;
		this.location = location;
		this.date = date;
		this.category = category;
		this.hosts = hosts;
		this.link = link;
		this.area=area;
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getHosts() {
		return hosts;
	}

	public void setHosts(List<String> hosts) {
		this.hosts = hosts;
	}
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setRegistrations(Set<Registration> registrations) {
		this.registrations = registrations;
	}
	
	public Set<Registration> getRegistrations() {
		return registrations;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}
	
	
//	public String toString() {		
//		return "printing in event to string "+this.id+" ";
//		
//	}
	
}
