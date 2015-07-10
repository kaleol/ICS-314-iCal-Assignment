import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Prompt {

	private static File file = new File("/users/SlyMongoose/314iCal.ics");
	private static Scanner userInput = new Scanner(System.in);
	
	public static void main(String [ ] args) {
		System.out.println("Welcome to your 314 iCal!");
		System.out.println("What would you like to do?");
		System.out.println("1. Create new Event");
		System.out.println("2. View calendar");
		System.out.println("3. Quit");
		
		int selection = userInput.nextInt();
		
		switch (selection) {
			case 1: makeEvent();
				break;
			case 2: viewCalendar();
				break;
			case 3: createICS();;
			default: 
		}
		
		userInput.close();
		
		System.out.println("Thank you for using iCal! Goodbye!");
	}

	private static void createICS() {
		
		
	}

	private static void viewCalendar() {
		
		
	}

	private static void makeEvent() {
		try {
			String eventName, timeStart, timeEnd, day, monthTemp, monthFinal = "NULL", year, eventLocation, geoLocation, classTemp, classFinal = "NULL";
			String content = "BEGIN:VCALENDAR\nPRODID:-//Google Inc//Google Calendar 70.9054//EN\nVERSION:2.0\n"
					+ "CALSCALE:GREGORIAN\nMETHOD:PUBLISH\nX-WR-CALNAME:hello\nX-WR-TIMEZONE:Pacific/Honolulu\nBEGIN:VTIMEZONE\n"
					+ "TZID:Pacific/Honolulu\nX-LIC-LOCATION:Pacific/Honolulu\nBEGIN:STANDARD\nTZOFFSETFROM:-1000\nTZOFFSETTO:-1000\n"
					+ "TZNAME:HST\nDTSTART:19700101T000000\nEND:STANDARD\nEND:VTIMEZONE\n";
	
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			
			System.out.println("What is the name of this event?");
			eventName = userInput.next();
			System.out.println("What year does this event occur? (eg. 1970)");
			year = userInput.next();
			System.out.println("What month? (eg. \"August\")");
			monthTemp = userInput.next();
			monthTemp = monthTemp.toLowerCase();
			System.out.println(monthTemp);
			switch (monthTemp) {
				case "january": monthFinal = "01";
					break;
				case "february": monthFinal = "02";
					break;
				case "march": monthFinal = "03";
					break;
				case "april": monthFinal = "04";
					break;
				case "may": monthFinal = "05";
					break;
				case "june": monthFinal = "06";
					break;
				case "july": monthFinal = "07";
					break;
				case "august": monthFinal = "08";
					break;
				case "september": monthFinal = "09";
					break;
				case "october": monthFinal = "10";
					break;
				case "november": monthFinal = "11";
					break;
				case "december": monthFinal = "12";
					break;
			}
			System.out.println("What day of the month? (eg. \"15\")");
			day = userInput.next();
			System.out.println("What time does the event start? (eg. 0600 (6am) or 1400 (2pm))");
			timeStart = userInput.next();
			System.out.println("What time does the event end?  (eg. 0600 (6am) or 1400 (2pm))");
			timeEnd = userInput.next();
			System.out.println("Where is the event located?");
			eventLocation = userInput.next();
			System.out.println("Event Classification (optional, but set to PUBLIC by default if left blank):\n1. Public\n2. Private\n3. Confidential\n4. Leave Blank");
			classTemp = userInput.next();
			System.out.println(classTemp);
			switch (classTemp) {
				case "1": classFinal = "PUBLIC";
					break;
				case "2": classFinal = "PRIVATE";
					break;
				case "3": classFinal = "CONFIDENTIAL";
					break;
				case "4": classFinal = "PUBLIC";
					break;
			}
			
			bw.write("BEGIN:VEVENT" + "\n");
			bw.write("DTSTART:" + year + monthFinal + day + "T" + timeStart +"00" + "\n");
			bw.write("DTEND:" + year + monthFinal + day + "T" + timeEnd +"00" + "\n");
			bw.write("DTSTAMP:20150709T115021" + "\n");
			bw.write("DESCRIPTION:" + "\n");
			bw.write("LOCATION:" + eventLocation + "\n");
			bw.write("CLASS:" + classFinal + "\n");
			bw.write("SEQUENCE:1" + "\n");
			bw.write("STATUS:TENTATIVE" + "\n");
			bw.write("SUMMARY:" + eventName + "\n");
			bw.write("TRANSP:OPAQUE" + "\n");
			bw.write("END:VEVENT" + "\n");
			bw.write("END:VCALENDAR");
			
			bw.close();
			
			System.out.println("Event created successfully!");

		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
