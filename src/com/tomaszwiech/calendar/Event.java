package com.tomaszwiech.calendar;

import java.time.LocalDate;

public class Event implements Comparable<Event> {
	private String description;
	private LocalDate date;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Event(String description, LocalDate date) {
		this.description = description;
		this.date = date;
	}
	
	public int compareTo(Event otherEvent) {
		return this.date.compareTo(otherEvent.date);
	}
	
	public void print() {
		System.out.println(date + " -> " + description);
	}
	

}
