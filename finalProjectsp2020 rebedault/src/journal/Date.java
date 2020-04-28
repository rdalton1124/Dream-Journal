package journal;

import java.io.*; 

public class Date implements Comparable<Date>, Serializable{
	private static final long serialVersionUID = 4179108777500508616L;
	private int day, month, year;
	public Date(int d, int m, int y) {
		day = d;
		month = m;
		year = y;
	}
	//return the date
	public Date getDate() {
		return this;
	}
	//express the date as a string
	public String toString() {
		String monthStr;
		switch(month) {
			case 1:
				monthStr = "January";
				break;
			case 2:
				monthStr = "February";
				break;
			case 3: 
				monthStr = "March";
				break;
			case 4:
				monthStr = "April";
				break;
			case 5:
				monthStr = "May";
				break;
			case 6:
				monthStr = "June";
				break;
			case 7:
				monthStr = "July";
				break;
			case 8:
				monthStr = "August";
				break;
			case 9:
				monthStr = "September";
				break;
			case 10:
				monthStr = "October";
				break;
			case 11:
				monthStr = "Novemeber";
				break;
			case 12:
				monthStr = "December";
				break;
			default:
				monthStr = "Error";
				break;
		}
		return monthStr + " " + day + ", " + year;
	}
	@Override 
	public int compareTo(Date date) {
		if(year != date.year) 
			return year - date.year;
		else {
			if (month != date.month)
				return month - date.month;
			else 
				return day - date.day;
		}
	}
	//testing method 
	public static void main(String [] args) {
			for (int i = 1; i <= 12; i++) {
				Date d = new Date(3, i, 1998);
				System.out.println(d.toString());
			}		
	}
}
