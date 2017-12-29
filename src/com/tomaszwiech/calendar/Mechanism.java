package com.tomaszwiech.calendar;

import java.time.LocalDate; 
import java.util.InputMismatchException;
import java.util.Scanner;

public class Mechanism {

	private Calendar calendar;
	private Scanner scannerInt = new Scanner(System.in);
	private Scanner scannerString = new Scanner(System.in);

	public Mechanism(Calendar calendar) {
		this.calendar = calendar;
	}

	private void mainMenu() {
		int choice = 0;
		try {
				System.out.println("\nCo chcesz zrobic: ");
				System.out.println("1) dodaj notatke");
				System.out.println("2) szukaj notatek");
				System.out.println("3) Zakoncz\n");

				choice = scannerInt.nextInt();
				switch (choice) {
				    case 1:
					    Event event = setEvent();
					    calendar.addEvent(event);
					    mainMenu();
					    break;
				    case 2:
					    searchEventMenu();
					    break;
                    case 3:
                        break;
				    default:
					    System.out.println("\n****************");
					    System.out.println("Wybor spoza menu");
					    System.out.println("****************\n");
				}
		} catch (InputMismatchException e) {
			System.out.println("\n*******************");
			System.out.println("Tylko dane liczbowe");
			System.out.println("*******************\n");
			scannerInt.nextLine();
			mainMenu();
		}   catch (java.time.DateTimeException e) {
			System.out.println("\n*************");
			System.out.println("Zle dane daty");
			System.out.println("*************\n");
			scannerInt.nextLine();
			mainMenu();
		}
			

	}
	
	private void searchEventMenu() {
		int choice = 0;
		while(choice != 1 && choice != 2) {
			System.out.println("\nJak chcesz szukac: ");
			System.out.println("1) szukaj po dacie");
			System.out.println("2) szukaj po miesiacu");
			System.out.println("3) szukaj na nastepne 7 dni\n");
			choice = scannerInt.nextInt();
			switch (choice) {
			    case 1:
				    searchByDayMenu();
				    break;
			    case 2:
				    searchByMonthMenu();
				    break;
                case 3:
                    searchForNext7Days();
                    break;
			    default:
				    System.out.println("\n****************");
				    System.out.println("Wybor spoza menu");
				    System.out.println("****************\n");
			}
		}		
	}

	private void searchByDayMenu() {
			System.out.println("\nPodaj rok: ");
			int year = scannerInt.nextInt();
			System.out.println("Podaj miesiac: ");
			int month = scannerInt.nextInt();
			System.out.println("Podaj dzien: ");
			int  day= scannerInt.nextInt();
			LocalDate date = LocalDate.of(year, month, day);
			Event[] events = calendar.dayEvents(date);
			printEvents(events);;
		mainMenu();
	}

	private void searchByMonthMenu() {
		System.out.println("\nPodaj rok: ");
		int year = scannerInt.nextInt();
		System.out.println("Podaj miesiac:");
		int month = scannerInt.nextInt();
		Event[] events = calendar.monthEvents(year, month);
		printEvents(events);
		mainMenu();
	}

	private void searchForNext7Days() {
	    LocalDate date = LocalDate.now();
	    Event[] events = calendar.forNext7DaysEvents(date);
	    printEvents(events);
	    mainMenu();
    }

	private Event setEvent() {
		System.out.println("\nPodaj rok: ");
		int year = scannerInt.nextInt();
		System.out.println("Podaj miesiac: ");
		int month = scannerInt.nextInt();
		System.out.println("Podaj dzien: ");
		int  day= scannerInt.nextInt();
		System.out.println("Podaj tresc: ");
		String description = scannerString.nextLine();
		return new Event(description, LocalDate.of(year, month, day));
	}
	
	private void printEvents(Event[] events) {
		System.out.println("\nZnalezione notatki:");
		if(events.length == 0) {
			System.out.println("Brak notatek pod ta data.");
		} else {
			for(Event event : events) {
				event.print();
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Mechanism mech = new Mechanism(new Calendar());
		mech.mainMenu();
	}
}
