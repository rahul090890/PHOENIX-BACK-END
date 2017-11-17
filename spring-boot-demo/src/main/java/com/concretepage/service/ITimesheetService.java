package com.concretepage.service;

import java.util.List;

import com.concretepage.dto.TimesheetDTO;
import com.concretepage.entity.Timesheet;
import com.concretepage.entity.TimesheetSummary;
import com.concretepage.exception.HRException;

public interface ITimesheetService {
	
	void saveTimesheet(List<Timesheet> timesheets) throws HRException;
	
	void updateTimesheet(List<Timesheet> timesheets) throws HRException ;
	
	List<TimesheetSummary> getTimesheetSummary(Integer employeeId, String startDate, String endDate);
	
	TimesheetDTO getTimesheetDetails(Integer employeeId, String weekStartDate, String weekEndDate);
	
	List<TimesheetSummary> getTimesheetsForApproval(Integer managerId);
	
	void approveTimesheet(Integer employeeId, String weekStartDate, String weekEndDate);
	
	void rejectTimesheet(Integer employeeId, String weekStartDate, String weekEndDate);
	
	TimesheetDTO getTimesheetDetails(long timesheetSequence);
	
	List<TimesheetSummary> getTimesheetsLessByHours(String startDate, String endate, int lessByHours, String departmentCode, String employeeType);

}
