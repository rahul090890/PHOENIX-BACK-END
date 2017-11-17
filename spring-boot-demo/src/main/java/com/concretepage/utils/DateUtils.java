package com.concretepage.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public final static String  DATE_FORMAT = "yyyy-MM-dd";
	public final static SimpleDateFormat sdf =
	          new SimpleDateFormat(DATE_FORMAT);
	
	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public static String getDateinYYYYMMDDAsLong() {
		 Calendar today = Calendar.getInstance();
		 return sdf.format(today.getTime());
	}
	
	public static long getDateinYYYYMMDD() {
		 Calendar today = Calendar.getInstance();
		 return (today.getTimeInMillis());
	}
	
	public static Date startDateOfCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
	}
	
	public static String dateAsString(Date date) {
		return sdf.format(date);
	}
	
	public static Date String_YYYY_MM_DD_ToDate(String dateString) {
		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String startDateOfCurrentMonthAsString() {
		return dateAsString(startDateOfCurrentMonth());
	}
	
	public static Date lastDateOfCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
	}
	
	public static String lastDateOfCurrentMonthAsString() {
		return dateAsString(lastDateOfCurrentMonth());
	}
	
	public static int daysBetweenDates(Date fromDate, Date toDate) {
		return (int)((toDate.getTime() - fromDate.getTime())/(24 * 60 * 60 * 1000));
	}
	
	public static String firstDayOfYear(int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.DAY_OF_YEAR, 1);    
		Date start = cal.getTime();
		
		return dateAsString(start);
	}
	
	public static void main(String[] arg) {
		System.out.println("Start date of the current Month" + startDateOfCurrentMonthAsString());
		System.out.println("End date of the current Month" + lastDateOfCurrentMonthAsString());
		
	}


}
