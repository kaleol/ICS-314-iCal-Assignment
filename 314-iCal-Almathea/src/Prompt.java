
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class Prompt {

	 //here's your directory Nick - /Storage/School/ICS 314/314iCal.ics - copy and paste back in when you demo
	static String home = System.getProperty("user.home");
  private static File file = new File(home, "secondDeliverable.ics");
  private static Vector<iCalEvent> calendar = new Vector<iCalEvent>();
  private static Scanner userInput = new Scanner(System.in);
  private static boolean go = false;
  private static boolean validTime = false;
  private static boolean validMonth = false;
  private static boolean validYear = false;
  private static boolean validDay = false;
  private static boolean validClass = false;
  private static boolean validGeo = false;
  private static boolean finished = false;

  public static void main(String[] args) {

    while (go != true || finished != true) {
      System.out.println("Welcome to your 314 iCal!");
      System.out.println("What would you like to do?");
      System.out.println("1. Create new Event");
      System.out.println("2. View calendar");
      System.out.println("3. Quit");
      String selection = userInput.nextLine();
      
      switch (selection) {
      case "1":
        makeEvent();
        go = true;
        break;
      case "2":
        viewCalendar();
        go = true;
        break;
      case "3":
        createICS();
        go = true;
        finished = true;
        break;
      default:
        System.err.println("Please choose a valid option");
      }
    }

    userInput.close();

    System.out.println("Thank you for using iCal! Goodbye!");
  }

  private static void createICS() {
    String content = "BEGIN:VCALENDAR\nPRODID:-//Google Inc//Google Calendar 70.9054//EN\nVERSION:2.0\n"
            + "CALSCALE:GREGORIAN\nMETHOD:PUBLISH\nX-WR-CALNAME:hello\nX-WR-TIMEZONE:Pacific/Honolulu\nBEGIN:VTIMEZONE\n"
            + "TZID:Pacific/Honolulu\nX-LIC-LOCATION:Pacific/Honolulu\nBEGIN:STANDARD\nTZOFFSETFROM:-1000\nTZOFFSETTO:-1000\n"
            + "TZNAME:HST\nDTSTART:19700101T000000\nEND:STANDARD\nEND:VTIMEZONE\n";

    Iterator<iCalEvent> schedule = calendar.iterator();
    
    // if file doesnt exists, then create it
    try {
	    if (!file.exists()) {
	      file.createNewFile();
	    }
	    FileWriter fw = new FileWriter(file.getAbsoluteFile());
	    BufferedWriter bw = new BufferedWriter(fw);
	    bw.write(content);
    
    	while (schedule.hasNext()) {
	    	iCalEvent temp = schedule.next();
	    	
		    bw.write("BEGIN:VEVENT" + "\n");
		    bw.write("DTSTART:" + temp.getYear() + temp.getMonth() + temp.getDay() + "T" + temp.getStartTime() + "00" + "\n");
		    bw.write("DTEND:"  + temp.getYear() + temp.getMonth() + temp.getDay() + "T" + temp.getEndTime() + "00" + "\n");
		    bw.write("DTSTAMP:20150709T115021" + "\n");
		    bw.write("DESCRIPTION:" + "\n");
		    bw.write("LOCATION:" + temp.getLocation() + "\n");
		    bw.write("GEO:" + temp.getGeo() + "\n");
		    bw.write("COMMENT:" + temp.getComment() + "\n");
		    bw.write("CLASS:" + temp.getEventClass() + "\n");
		    bw.write("SEQUENCE:1" + "\n");
		    bw.write("STATUS:TENTATIVE" + "\n");
		    bw.write("SUMMARY:" + temp.getName() + "\n");
		    bw.write("TRANSP:OPAQUE" + "\n");
		    bw.write("END:VEVENT" + "\n");
    	}
    	bw.write("END:VCALENDAR");
		   
		bw.close();
	} catch (IOException e) {
			e.printStackTrace();
	}
  }

  private static void viewCalendar() {
    Iterator<iCalEvent> schedule = calendar.iterator();
    String lastDate = null;
    String lastComment = null;
    
    if (calendar.isEmpty()) {
    	System.out.println("Calendar is empty!  You do not have any events planned.");
    }
    else {
    	System.out.println("Here is your calendar of events!");
    }
    while (schedule.hasNext()) {
    	iCalEvent temp = schedule.next();
    	if (temp.getTotalDate().equals(lastDate)) {
    		System.out.println("\t" + temp.getStartTime() + " - " + temp.getEndTime() + " : " + temp.getName());
    		System.out.println("\t\t" + "Location: " + temp.getLocation());
    		if (lastComment != null) {
    			System.out.println("\t\t" + "Comment: " + lastComment);
    		}
    	}
    	else {
    		System.out.println();
	    	System.out.println(temp.getMonth() + "/" + temp.getDay() + "/" + temp.getYear());
	    	System.out.println("\t" + temp.getStartTime() + " - " + temp.getEndTime() + " : " + temp.getName());
	    	System.out.println("\t\t" + "Location: " + temp.getLocation());
    	}
    	
    	lastDate = temp.getTotalDate();
    	lastComment = temp.getComment();
    }
    System.out.println();
  }


  private static void makeEvent() {
      String eventName = null, timeStart = null, timeEnd = null, day = null, monthTemp = null, monthFinal = null, year = null, eventLocation = null, geoLocation = null, classTemp  = null, classFinal = null;

      System.out.println("What is the name of this event?");
      eventName = userInput.nextLine();

      while (validYear != true) {
        System.out.println("What year does this event occur? (eg. 1970)");
        year = userInput.nextLine();
        if (year.length() < 4 || year.length() > 4 || !year.matches("[0-9]+")) {
          System.err.println("Please enter a valid year");
        }
        else {
          validYear = true;
        }
      }

      while (validMonth != true) {
        System.out.println("What month? (eg. \"August\")");
        monthTemp = userInput.nextLine();
        monthTemp = monthTemp.toLowerCase();
        switch (monthTemp) {
        case "january":
          monthFinal = "01";
          validMonth = true;
          break;
        case "february":
          monthFinal = "02";
          validMonth = true;
          break;
        case "march":
          monthFinal = "03";
          validMonth = true;
          break;
        case "april":
          monthFinal = "04";
          validMonth = true;
          break;
        case "may":
          monthFinal = "05";
          validMonth = true;
          break;
        case "june":
          monthFinal = "06";
          validMonth = true;
          break;
        case "july":
          monthFinal = "07";
          validMonth = true;
          break;
        case "august":
          monthFinal = "08";
          validMonth = true;
          break;
        case "september":
          monthFinal = "09";
          validMonth = true;
          break;
        case "october":
          monthFinal = "10";
          validMonth = true;
          break;
        case "november":
          monthFinal = "11";
          validMonth = true;
          break;
        case "december":
          monthFinal = "12";
          validMonth = true;
          break;
        default:
          System.err.println("Please enter a valid month");
        }
      }

      while (validDay != true) {
        System.out.println("What day of the month? (eg. \"15\")");
        day = userInput.nextLine();
        if (day.length() < 2 || day.length() > 2 || !day.matches("[0-9]+")) {
          System.err.println("Please enter 2 digits");
        }
        else {
          validDay = true;
        }
      }

      while (validTime != true) {
        System.out.println("What time does the event start? (eg. 0600 (6am) or 1400 (2pm))");
        timeStart = userInput.nextLine();
        if (timeStart.length() < 4 || timeStart.length() > 4

        // First digit must start with 0, 1, or 2
            /*
             * || !timeStart.startsWith("0") || !timeStart.startsWith("1") ||
             * !timeStart.startsWith("2")
             * 
             * // Third digit must be 0 - 5 || timeStart.startsWith("6", 2) ||
             * timeStart.startsWith("7", 2) || timeStart.startsWith("8", 2) ||
             * timeStart.startsWith("9", 2)
             */

            || !timeStart.matches("[0-9]+")) {
          System.err.println("Please enter a valid time eg. 0600 (6am) or 1400 (2 pm)");
        }
        else {
          validTime = true;
        }
      }

      validTime = false;

      while (validTime != true) {
        System.out.println("What time does the event end?  (eg. 0600 (6am) or 1400 (2pm))");
        timeEnd = userInput.nextLine();

        if (timeEnd.length() < 4 || timeEnd.length() > 4
        // First digit must start with 0, 1, or 2
            /*
             * || !timeStart.startsWith("0") || !timeStart.startsWith("1") ||
             * !timeStart.startsWith("2")
             * 
             * // Third digit must be 0 - 5 || timeStart.startsWith("6", 2) ||
             * timeStart.startsWith("7", 2) || timeStart.startsWith("8", 2) ||
             * timeStart.startsWith("9", 2)
             */

            || !timeEnd.matches("[0-9]+")) {
          System.err.println("Please enter a valid time eg. 0600 (6am) or 1400 (2 pm)");
        }
        else {
          validTime = true;
        }
      }

      System.out.println("Where is the event located?");
      eventLocation = userInput.nextLine();

      while (validClass != true) {
        System.out.println("Event Classification (optional, but set to PUBLIC by default if left blank):\n1. Public\n2. Private\n3. Confidential\n4. Leave Blank");
        classTemp = userInput.nextLine();

        switch (classTemp) {
        case "1":
          classFinal = "PUBLIC";
          validClass = true;
          break;
        case "2":
          classFinal = "PRIVATE";
          validClass = true;
          break;
        case "3":
          classFinal = "CONFIDENTIAL";
          validClass = true;
          break;
        case "4":
          classFinal = "PUBLIC";
          validClass = true;
          break;
        default:
          System.err.println("Please choose a valid option");
        }
      }
      
    //while(!validGeo) {
      System.out.println("Do you have exact coordinates of the event? (Latitude;Longitude (in degrees specific to 6 decimal places))");
      System.out.println("eg. 21.300000;157.816700");
      System.out.println("If not, leave blank and hit enter.");
      geoLocation = userInput.nextLine();
      
      //create a new iCalEvent object to save the event and store it in the event vector
      iCalEvent tempEvent = new iCalEvent();
      
      tempEvent.setDay(day);
      tempEvent.setMonth(monthFinal);
      tempEvent.setYear(year);
      tempEvent.setStartTime(timeStart);
      tempEvent.setEndTime(timeEnd);
      tempEvent.setLocation(eventLocation);
      tempEvent.setEventClass(classFinal);
      tempEvent.setGeo(geoLocation);
      tempEvent.setName(eventName);
      
      String totalS = tempEvent.getYear() + tempEvent.getMonth() + tempEvent.getDay();
      tempEvent.setTotalDate(totalS);
      
      //insert the event in to the calendar vector
      insertNewEvent(tempEvent);
      
      validDay = false;
      validMonth = false;
      validYear = false;
      validClass = false;
      validTime = false;

      System.out.println("Event created successfully!\n");
  }
  
  //method sorts events, first by date, then by time
  public static void insertNewEvent(iCalEvent newEvent) {
	  Iterator<iCalEvent> schedule = calendar.iterator();
	  int index = 0;
	  boolean added = false;
	  iCalEvent temp = null;
	  iCalEvent previous = null;
	  
	  
	  while (schedule.hasNext() && added == false) {
		  previous = temp;
		  temp = schedule.next();
		  while (newEvent.getTotalDate().compareTo(temp.getTotalDate()) > 0 && index < calendar.size()){
			  if (schedule.hasNext()) {
				  temp = schedule.next();
			  }
			  index++;
		  }
		  
		  while(newEvent.getTotalDate().equals(temp.getTotalDate()) && added == false) {
			  int newTime = Integer.parseInt(newEvent.getStartTime());
			  int tempTime = Integer.parseInt(temp.getStartTime());
			  
			  if (newTime > tempTime && schedule.hasNext()) {
				  previous = temp;
				  temp = schedule.next();
				  index++;
			  }
			  else if (newTime > tempTime && !schedule.hasNext()) {
				  //newEvent is the latest event of the day
				  if (index + 1 > calendar.size()) {
					  calendar.add(newEvent);
					  added = true;
				  }
				  //newEvent is inserted in the correct order
				  else {
					  if (newEvent.getGeo() != null && temp.getGeo() != null) {
						  String[] firstPoint = newEvent.getGeo().split(";");
						  String[] secondPoint = temp.getGeo().split(";");
						  double distance = distFrom(Double.parseDouble(firstPoint[0]), Double.parseDouble(firstPoint[1]), Double.parseDouble(secondPoint[0]), Double.parseDouble(secondPoint[1]));
						  String tempDistance = Double.toString(distance);
						  temp.setComment("Distance between this event and the last is " + tempDistance + " kilometers!");
						  
						  System.out.println(tempDistance);
					  }
					  calendar.add(index + 1, newEvent);
					  added = true;
				  }
			  }
			  //newEvent is at the beginning		  
			  else {
				  String[] firstPoint = newEvent.getGeo().split(";");
				  String[] secondPoint = temp.getGeo().split(";");
				  double distance = distFrom(Double.parseDouble(firstPoint[0]), Double.parseDouble(firstPoint[1]), Double.parseDouble(secondPoint[0]), Double.parseDouble(secondPoint[1]));
				  String tempDistance = Double.toString(distance);
				  newEvent.setComment("Distance between this event and the last is " + tempDistance + " kilometers!");
				  
				  System.out.println(tempDistance);
					  
				  calendar.add(index, newEvent);
				  added = true;
			  }
			  
		  }
	  }
	  
	  if (added == false) {
		  newEvent.setComment("");
		  calendar.add(index, newEvent);
	  }
  }
  
  public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
	    double earthRadius = 6371.0; // kilometers (or 3958.75 miles)
	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);
	    double sindLat = Math.sin(dLat / 2);
	    double sindLng = Math.sin(dLng / 2);
	    double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
	            * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double dist = earthRadius * c;

	    return dist;
	    }
  
}
