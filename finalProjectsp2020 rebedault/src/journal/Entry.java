package journal;

import java.io.*;
import java.util.*;

public class Entry implements Comparable<Entry>, Serializable{

	private static final long serialVersionUID = -8918215654117226800L;
	private Date date;
	private Time fallenAsleep, wokenUp;
	private double hoursSlept;
	private ArrayList<Dream> dreams = new ArrayList<Dream>();
	
	public Entry(Date date, Time fellAsleep, Time wokeUp) {
		this.date = date;
		fallenAsleep = fellAsleep;
		wokenUp = wokeUp;
		hoursSlept = fallenAsleep.hoursPassed(wokeUp);
	}
	
	//getters 
	public int getNumberOfDreams() {
		return dreams.size();
	}
	public double getHoursSlept() {
		return hoursSlept;
	}
	public Time getFallenAsleep() {
		return fallenAsleep;
	}
	public Time getWokenUp() {
		return wokenUp;
	}
	public Date getDate() {
		return date;
	}
	public String getDreams() {
		if (dreams.size() == 0) 
			return "No Dreams Recorded";
		else {
			String drms = ""; 
			for (int i = 0; i < dreams.size(); i++) {
				int dreamNum = i + 1;
				drms = drms  + "Dream " + dreamNum + "\n" +  dreams.get(i).viewDream() + "\n \n";
			}
			return drms;
		}
	}

	//Add a new dream
	public void addDream(Dream dream) {
		dreams.add(dream);
	}
	@Override
	public int compareTo(Entry entry) {
		return date.compareTo(entry.date);
	}
}
