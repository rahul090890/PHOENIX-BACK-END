package com.concretepage.dao;

import java.util.List;

import com.concretepage.entity.Timesheet;

public interface ITimesheetDao {
	
	void createTimesheet(Timesheet timesheet);
	
	List<Timesheet> getTimesheetsBeforeDate(Integer employeeId, String timesheetStartDate, String timesheetEndDate);
	
	boolean isTimesheetAlreadySubmitted(Integer employeeId, String timesheetStartDate, String timesheetEndDate);
	
	void createTimesheets(List<Timesheet> timesheets);
	
	List<Timesheet> getTimesheetsForAWeek(Integer employeeId, String weekStartDate, String weekEndDate);
	
	void approveTimesheet(Integer employeeId, String weekStartDate, String weekEndDate);
	
	void rejectTimesheet(Integer employeeId, String weekStartDate, String weekEndDate);
	
	void deleteTimesheet(Integer employeeId, String weekStartDate, String weekEndDate);
	
	List<Timesheet> getTimesheetsBySequence(long timesheetSequence);
	
	
}
