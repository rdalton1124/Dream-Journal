package journal;

import java.io.*;
import java.util.*;

public class Journal implements Serializable {
	//I kept getting a warning about the fact that there was no serialVersionUID
	//I looked it up and found that Eclipse could randomly generate one, which is what I did for all of these classes
	//Solution was found here: https://stackoverflow.com/questions/5632065/what-does-the-declare-a-static-final-serialversionuid-warning-mean-and-how-to
	private static final long serialVersionUID = 1681346338598215335L;
	ArrayList<Entry> entries;
	public Journal() {
		try {
			FileInputStream fin = new FileInputStream("dreamjournal.dat");
			ObjectInputStream obin = new ObjectInputStream(fin);
			entries = (ArrayList<Entry>) obin.readObject();
			obin.close();
			fin.close();
		}
		catch (Exception e) {
			entries = new ArrayList<Entry>();
		}
	}
	public void closeJournal() {
		try {
			FileOutputStream fout = new FileOutputStream("dreamjournal.dat");
			ObjectOutputStream obout = new ObjectOutputStream(fout);
			obout.writeObject(entries);
			fout.close();
			obout.close();
		}
		catch (Exception e) {
			System.out.println("Error: "  + e.getMessage());
		}
	}
	//getters
	public String viewEntry(int d, int m, int y) {
		Date targetDate = new Date(d, m, y);
		int index = -1;
		for (int i = 0; i < entries.size(); i++) {
			if (entries.get(i).getDate().equals(targetDate)) {
				index = i;
				break;
			}
			else if (entries.get(i).getDate().compareTo(targetDate) > 0) {
				break;
			}
		}
		if (index == -1) {
			return "Entry not Found" ;
		}
		else {
			return ("Date of Entry: " + entries.get(index).getDate().toString() + 
					"Time Fallen Asleep: " + entries.get(index).getFallenAsleep().toString() + 
					"Time Woken Up: " + entries.get(index).getWokenUp().toString() + 
					"Total Hours Slept: " + entries.get(index).getHoursSlept() + "\n\n" +
					entries.get(index).getDreams());
		}
	}
	public String viewEntry (int index) {
		if (index >= entries.size()) {
			return "Entry not Found";
		}
		else {
			return ("Date of Entry: " + entries.get(index).getDate().toString() + 
					"\nTime Fallen Asleep: " + entries.get(index).getFallenAsleep().toString() + 
					"\nTime Woken Up: " + entries.get(index).getWokenUp().toString() + 
					"\nTotal Hours Slept: " + entries.get(index).getHoursSlept() + "\n\n" +
					entries.get(index).getDreams());
		}
	}
	public String viewEntrySummary(int index) {
		if (index >= entries.size()) {
			return "Entry not Found";
		}
		else {
			return entries.get(index).getDate().toString();
		}
	}
 	public void viewStats() {
		System.out.println("Total Entries: " + entries.size());
		int dreams = 0;
		double hours = 0;
		for (int i = 0; i < entries.size(); i ++) {
			dreams += entries.get(i).getNumberOfDreams();
			hours += entries.get(i).getHoursSlept();
		}
		if (entries.size() != 0) {
			System.out.println("Total Dreams Recorded: " + dreams);
			System.out.println("Average Dreams per Night: " + (dreams / entries.size()));
			System.out.println("Total Hours Slept: " + hours);
			System.out.println("Average Hours Slept: " + (hours) / entries.size());
		}
		else {
			System.out.println("No Entries, lol");
		}
	}
	public String getStats() {
		if (entries.size() == 0) 
			return "No Stats to View";
		int totDream = 0, avgDream, numEntries, maxDreams;
		double totHours = 0, avgHours;
		maxDreams = 0;
		numEntries = entries.size();
		for (int i = 0; i < numEntries; i++) {
			totDream += entries.get(i).getNumberOfDreams();
			totHours += entries.get(i).getHoursSlept();
			if (entries.get(i).getNumberOfDreams() > maxDreams) 
				maxDreams = entries.get(i).getNumberOfDreams();
		}
		avgHours = totHours / numEntries;
		avgDream = totDream / numEntries;
		return ("Total Entries: " + numEntries + 
				"\n Total Dreams Record: " + totDream + 
				"\n Average Dreams per Night: " + avgDream + 
				"\n Most Dreams in a single night: " + maxDreams +
				"\n Total Hours Slept: " + totHours + 
				"\n Average Hours per Night: " + avgHours);
	}
	public int getNumEntries() {
		return entries.size();
	}
	//add a new entry.
	//make and sort entries
	public void makeEntry(Entry entry) {
		entries.add(entry);
		//always sort entries after adding one.
		Collections.sort(entries);
	}

	//testing method
	//testing method
	public static void main(String [] args) {
		Journal j = new Journal();
		j.viewStats();
		Scanner scan = new Scanner(System.in);
		System.out.println("What is the year of the entry?" );
		int year = scan.nextInt();
		int month;
		do {
			System.out.println("What is the month?");
			month = scan.nextInt();
		} while (month < 1 || month > 12);
		int day;
		boolean keepGoing = true;
		do {
			System.out.println("What is the date?");
			day = scan.nextInt();
			if (month == 1 || month == 3 || month == 5 || month == 7
					|| month == 8 || month == 10 || month == 12) {
				if (day >= 1 && day <= 31) 
					keepGoing = false;
				else 
					System.out.println("Invalid date");
			}
			else if (month == 4 || month == 6 || month == 9 || month == 11) {
				if (day >= 1 && day <= 30)
					keepGoing = false;
				else
					System.out.println("Invalid Date");
			}
			else {
				if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
					if (day >= 1 && day <= 29)
						keepGoing = false;
					else
						System.out.println("Invalid date.");
				}
				else {
					if (day >= 1 && day <= 28)
						keepGoing = false;
					else
						System.out.println("Invalid date. " + year + " is not a leap year.");
				}
			}
		} while (keepGoing);
		int sleepMinutes, sleepHours, wakeMinutes, wakeHours; 
		do {
			System.out.println("What is the hour that you fell asleep?");
			sleepHours = scan.nextInt();
		} while(sleepHours < 0 || sleepHours > 23);
		do {
			System.out.println("What minute did you fall asleep?");
			sleepMinutes = scan.nextInt();
		}while (sleepMinutes < 0 || sleepMinutes > 59);
		do {
			System.out.println("What hour did you wake up ?");
			wakeHours = scan.nextInt();
		}while (wakeHours < 0 || wakeHours > 59);
		do {
			System.out.println("What minute did you wake up?");
			wakeMinutes = scan.nextInt();
		} while(wakeMinutes < 0 || wakeMinutes > 59);
		Entry e = new Entry(new Date(day, month, year), new Time(sleepHours, sleepMinutes), new Time(wakeHours, wakeMinutes));
		keepGoing = true;
		int sel;
		do {
			System.out.println("What do you Want to do?");
			System.out.println("1. Add a dream to entry");
			System.out.println("2. Exit");
			sel = scan.nextInt();
			scan.nextLine();
			switch (sel) {
			case 1:
				System.out.println("Enter the details of your dream and then press enter");
				String str = "";
				str = scan.nextLine();
				e.addDream(new Dream(str));
				break;
			case 2: 
				keepGoing = false;
				break;
			default:
				System.out.println("Input could not be understood");
			}
		} while(keepGoing);
		j.makeEntry(e);
		j.closeJournal();
		scan.close();
	}
}
