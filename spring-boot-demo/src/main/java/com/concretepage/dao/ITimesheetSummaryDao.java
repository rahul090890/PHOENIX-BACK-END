package com.concretepage.dao;

import java.util.List;

import com.concretepage.entity.TimesheetSummary;

public interface ITimesheetSummaryDao {
	
	List<TimesheetSummary> getTimesheetSummary(Integer employeeId, String startDate , String endDate);
	
	List<TimesheetSummary> getPendingTimesheetSummary(List<Integer> employeeIds);
	
	public List<TimesheetSummary> getTimesheetsLessByHours(String startDate, String endDate, int lessByHours,
			String departmentCode, String employeeType);
		

}
