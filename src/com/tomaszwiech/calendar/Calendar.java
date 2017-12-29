package com.tomaszwiech.calendar;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;

public class Calendar {
	
	private LinkedList<Event> events = new LinkedList<>();

	public Calendar() {
		
	}
	
	public void addEvent(Event event) {
		events.add(event);
	}
	
	public Event[] dayEvents(LocalDate date) {
		LinkedList<Event> tempContener = new LinkedList<>();
		for(Event event : events) {
			if(event.getDate().equals(date)) {
				tempContener.add(event);
			}
		}
		Event[] array = tempContener.toArray(new Event[tempContener.size()]);
		return array;		
	}
	
	public LinkedList<Event>weekEvent(LocalDate date) {
		return null;
	}

	public Event[] monthEvents(int year, int month) {
		LinkedList<Event> tempContener = new LinkedList<>();
		for(Event event : events) {
			if(event.getDate().getMonthValue() == month && event.getDate().getYear() == year) {
				tempContener.add(event);
			}
		}
		Event[] array = tempContener.toArray(new Event[tempContener.size()]);
		Arrays.sort(array);
		return array;
	}

    public Event[] forNext7DaysEvents(LocalDate date) {
		LinkedList<Event> tempContener = new LinkedList<>();
    	for(int i = 0; i < 7; i++) {
    	    LocalDate tempDate = date.plusDays((long) i);
    		for(Event event : events) {
    			if(event.getDate().equals(tempDate)) {
    				tempContener.add(event);
				}
			}
		}
		Event[] array = tempContener.toArray(new Event[tempContener.size()]);
    	Arrays.sort(array);
    	return array;
	}


    public void printEvents(LinkedList<Event> events) {
		for(Event event : events) {
			System.out.println(event.getDate() + " -> " + event.getDescription());
		}
	}
}
