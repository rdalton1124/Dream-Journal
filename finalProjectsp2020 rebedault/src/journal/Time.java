package journal;

import java.io.*; 
public class Time implements Serializable{
	private static final long serialVersionUID = 2967540107467568186L;
	private int hours, minutes;
	//constructor
	public Time(int hrs, int min) {
		hours = hrs;
		minutes = min;
	}
	//getters
	public int getHours() {
		return hours;
	}
	public int getMinutes() {
		return minutes;
	}
	public double hoursPassed(Time laterTime) {
		double timePassed;
		if (laterTime.getMinutes() >= minutes) {
			if (laterTime.getHours() >= hours) {
				timePassed = laterTime.getHours() - hours;
				timePassed += (double) (laterTime.getMinutes() - minutes) / 60;
			}
			else {
				timePassed = 24 - hours + laterTime.getHours();
				timePassed += (double) (laterTime.getMinutes() - minutes) / 60;
			}
		}
		else {
			if (laterTime.getHours() > hours) {
				timePassed = laterTime.getHours() - hours;
				timePassed += (double) (60 - minutes + laterTime.getMinutes()) / 60;
				timePassed --;
			}
			else {
				timePassed = 24 - hours + laterTime.getHours();
				timePassed += (double) (60 - minutes + laterTime.getMinutes()) / 60;
				timePassed --;
			}
		}
		return timePassed;
	}
	public Time laterTime(double hoursPassed) {
		int hoursLater = (int) hoursPassed; 
		int minutesLater = (int) ((hoursPassed - hoursLater) * 60);
		int newHours = hours + hoursLater; 
		int newMinutes = minutes + minutesLater;
		if (newHours > 23)
			newHours -= 24;
		if (newMinutes > 59)
			newMinutes -= 60;
		return new Time(newHours, newMinutes);
		
	}
	public Time beforeTime(double hoursBefore) {
		int hoursPrior = (int)hoursBefore;
		int minutesPrior = (int) ((hoursBefore - hoursPrior) * 60);
		int newHours = hours - hoursPrior;
		int newMinutes = minutes - minutesPrior;
		if (newHours < 0)
			newHours += 24;
		if (newMinutes < 0)
			newMinutes += 60;
		
		return new Time(newHours, newMinutes);
		}

	public String toString() {
		if (minutes == 0)
			return hours + ":00";
		else
			return hours + ":" + minutes;
	}
	//testing method
	public static void main (String [] args) {
		Time time = new Time(12, 00);
		System.out.println(time.beforeTime(1.5).toString());
		System.out.println(time.beforeTime(13).toString());
		System.out.println(time.laterTime(12).toString());
		System.out.println(time.laterTime(.25).toString());
		System.out.println(time.hoursPassed(new Time(14, 45)));
		System.out.println(new Time(12, 45).hoursPassed(new Time(13, 00)));
		System.out.println(new Time (12, 45).hoursPassed(new Time(12, 00)));
		
		
	}
}
