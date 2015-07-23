package iCal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Prompt {

  private static File file = new File("/Storage/School/ICS 314/314iCal.ics");
  private static Scanner userInput = new Scanner(System.in);
  private static boolean go = false;
  private static boolean validTime = false;
  private static boolean validMonth = false;
  private static boolean validYear = false;
  private static boolean validDay = false;
  private static boolean validClass = false;


  public static void main(String[] args) {

    while (go != true) {
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
        break;
      default:
        System.err.println("Please choose a valid option");
      }
    }

    userInput.close();

    System.out.println("Thank you for using iCal! Goodbye!");
  }


  private static void createICS() {
    System.out.println("Work in progress...");
  }


  private static void viewCalendar() {
    System.out.println("Work in progress...");
  }


  private static void makeEvent() {
    try {
      String eventName, timeStart = "NULL", timeEnd = "NULL", day = "NULL", monthTemp, monthFinal = "NULL", year = "NULL", eventLocation, geoLocation, classTemp = "NULL", classFinal = "NULL";
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
        System.out.println(monthTemp);
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
        System.out
            .println("What time does the event start? (eg. 0600 (6am) or 1400 (2pm))");
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
          System.err
              .println("Please enter a valid time eg. 0600 (6am) or 1400 (2 pm)");
        }
        else {
          validTime = true;
        }
      }

      validTime = false;

      while (validTime != true) {
        System.out
            .println("What time does the event end?  (eg. 0600 (6am) or 1400 (2pm))");
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
          System.err
              .println("Please enter a valid time eg. 0600 (6am) or 1400 (2 pm)");
        }
        else {
          validTime = true;
        }
      }

      System.out.println("Where is the event located?");
      eventLocation = userInput.nextLine();

      while (validClass != true) {
        System.out
            .println("Event Classification (optional, but set to PUBLIC by default if left blank):\n1. Public\n2. Private\n3. Confidential\n4. Leave Blank");
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

      System.out.println(classTemp);

      bw.write("BEGIN:VEVENT" + "\n");
      bw.write("DTSTART:" + year + monthFinal + day + "T" + timeStart + "00"
          + "\n");
      bw.write("DTEND:" + year + monthFinal + day + "T" + timeEnd + "00" + "\n");
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

    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
